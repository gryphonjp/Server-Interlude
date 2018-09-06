/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.l2jbr.gameserver.serverpackets;

import com.l2jbr.gameserver.model.L2Clan;
import com.l2jbr.gameserver.model.L2ClanMember;
import com.l2jbr.gameserver.model.actor.instance.L2PcInstance;


/**
 * @author -Wooden-
 */
public class PledgeShowMemberListUpdate extends L2GameServerPacket
{
	private static final String _S__54_PLEDGESHOWMEMBERLISTUPDATE = "[S] 54 PledgeShowMemberListUpdate";
	private final L2PcInstance _activeChar;
	private final int _pledgeType;
	private int _hasSponsor;
	private final String _name;
	private final int _level;
	private final int _classId;
	private final int _objectId;
	private int _isOnline;
	
	public PledgeShowMemberListUpdate(L2PcInstance player)
	{
		_activeChar = player;
		_pledgeType = player.getPledgeType();
		if (_pledgeType == L2Clan.SUBUNIT_ACADEMY)
		{
			_hasSponsor = _activeChar.getSponsor() != 0 ? 1 : 0;
		}
		else
		{
			_hasSponsor = 0;
		}
		_name = _activeChar.getName();
		_level = _activeChar.getLevel();
		_classId = _activeChar.getPlayerClass().getId();
		_objectId = _activeChar.getObjectId();
		_isOnline = _activeChar.isOnline();
	}
	
	public PledgeShowMemberListUpdate(L2ClanMember player)
	{
		_activeChar = player.getPlayerInstance();
		_name = player.getName();
		_level = player.getLevel();
		_classId = player.getClassId();
		_objectId = player.getObjectId();
		if (player.isOnline())
		{
			_isOnline = 1;
		}
		else
		{
			_isOnline = 0;
		}
		_pledgeType = player.getPledgeType();
		if (_pledgeType == L2Clan.SUBUNIT_ACADEMY)
		{
			_hasSponsor = _activeChar.getSponsor() != 0 ? 1 : 0;
		}
		else
		{
			_hasSponsor = 0;
		}
	}
	
	@Override
	protected final void writeImpl()
	{
		writeByte(0x54);
		writeString(_name);
		writeInt(_level);
		writeInt(_classId);
		writeInt(0);
		writeInt(_objectId);
		writeInt(_isOnline); // 1=online 0=offline
		writeInt(_pledgeType);
		writeInt(_hasSponsor);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.l2jbr.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__54_PLEDGESHOWMEMBERLISTUPDATE;
	}
	
}
