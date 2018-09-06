/* This program is free software; you can redistribute it and/or modify
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


/**
 * Format: (ch)ddd
 */
public class ExVariationResult extends L2GameServerPacket
{
	private static final String _S__FE_55_EXVARIATIONRESULT = "[S] FE:55 ExVariationResult";
	
	private final int _stat12;
	private final int _stat34;
	private final int _unk3;
	
	public ExVariationResult(int unk1, int unk2, int unk3)
	{
		_stat12 = unk1;
		_stat34 = unk2;
		_unk3 = unk3;
	}
	
	@Override
	protected void writeImpl()
	{
		writeByte(0xfe);
		writeShort(0x55);
		writeInt(_stat12);
		writeInt(_stat34);
		writeInt(_unk3);
	}
	
	@Override
	public String getType()
	{
		return _S__FE_55_EXVARIATIONRESULT;
	}
}