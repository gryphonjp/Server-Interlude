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
package com.l2jbr.gameserver.handler.skillhandlers;

import com.l2jbr.gameserver.handler.ISkillHandler;
import com.l2jbr.gameserver.instancemanager.CastleManager;
import com.l2jbr.gameserver.model.L2Character;
import com.l2jbr.gameserver.model.L2Object;
import com.l2jbr.gameserver.model.L2Skill;
import com.l2jbr.gameserver.model.L2Skill.SkillType;
import com.l2jbr.gameserver.model.actor.instance.L2ArtefactInstance;
import com.l2jbr.gameserver.model.actor.instance.L2PcInstance;
import com.l2jbr.gameserver.model.entity.Castle;
import com.l2jbr.gameserver.network.SystemMessageId;
import com.l2jbr.gameserver.serverpackets.SystemMessage;
import com.l2jbr.gameserver.util.Util;


/**
 * @author _drunk_ TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class TakeCastle implements ISkillHandler
{
	// private static Logger _log = LoggerFactory.getLogger(TakeCastle.class.getName());
	private static final SkillType[] SKILL_IDS =
	{
		SkillType.TAKECASTLE
	};
	
	@Override
	public void useSkill(L2Character activeChar, L2Skill skill, L2Object[] targets)
	{
		if ((activeChar == null) || !(activeChar instanceof L2PcInstance))
		{
			return;
		}
		
		L2PcInstance player = (L2PcInstance) activeChar;
		
		if ((player.getClan() == null) || (player.getClan().getLeaderId() != player.getObjectId()))
		{
			return;
		}
		
		Castle castle = CastleManager.getInstance().getCastle(player);
		if ((castle == null) || !checkIfOkToCastSealOfRule(player, castle, true))
		{
			return;
		}
		
		try
		{
			if (targets[0] instanceof L2ArtefactInstance)
			{
				castle.Engrave(player.getClan(), targets[0].getObjectId());
			}
		}
		catch (Exception e)
		{
		}
	}
	
	@Override
	public SkillType[] getSkillIds()
	{
		return SKILL_IDS;
	}
	
	/**
	 * Return true if character clan place a flag<BR>
	 * <BR>
	 * @param activeChar The L2Character of the character placing the flag
	 * @param isCheckOnly
	 * @return
	 */
	public static boolean checkIfOkToCastSealOfRule(L2Character activeChar, boolean isCheckOnly)
	{
		return checkIfOkToCastSealOfRule(activeChar, CastleManager.getInstance().getCastle(activeChar), isCheckOnly);
	}
	
	public static boolean checkIfOkToCastSealOfRule(L2Character activeChar, Castle castle, boolean isCheckOnly)
	{
		if ((activeChar == null) || !(activeChar instanceof L2PcInstance))
		{
			return false;
		}
		
		SystemMessage sm = new SystemMessage(SystemMessageId.S1_S2);
		L2PcInstance player = (L2PcInstance) activeChar;
		
		if ((castle == null) || (castle.getCastleId() <= 0))
		{
			sm.addString("You must be on castle ground to use this skill");
		}
		else if ((player.getTarget() == null) && !(player.getTarget() instanceof L2ArtefactInstance))
		{
			sm.addString("You can only use this skill on an artifact");
		}
		else if (!castle.getSiege().getIsInProgress())
		{
			sm.addString("You can only use this skill during a siege.");
		}
		else if (!Util.checkIfInRange(200, player, player.getTarget(), true))
		{
			sm.addString("You are not in range of the artifact.");
		}
		else if (castle.getSiege().getAttackerClan(player.getClan()) == null)
		{
			sm.addString("You must be an attacker to use this skill");
		}
		else
		{
			if (!isCheckOnly)
			{
				castle.getSiege().announceToPlayer("Clan " + player.getClan().getName() + " has begun to engrave the ruler.", true);
			}
			return true;
		}
		
		if (!isCheckOnly)
		{
			player.sendPacket(sm);
		}
		return false;
	}
}
