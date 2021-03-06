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
package com.l2jbr.gameserver.ai2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author -Wooden-
 */
public abstract class EventHandler
{
	protected static final Logger _log = LoggerFactory.getLogger(EventHandler.class.getName());
	
	abstract AiEventType getEvenType();
	
	/**
	 * @return the priority of this EventHandler INSIDE the EventHandlerSet
	 */
	abstract int getPriority();
	
	abstract void runImpl(AiParameters aiParams, AiEvent event);
	
	abstract AiPlugingParameters getPlugingParameters();
	
	@Override
	public String toString()
	{
		return "EventHandler: " + getEvenType().name() + " Priority:" + getPriority();
	}
}
