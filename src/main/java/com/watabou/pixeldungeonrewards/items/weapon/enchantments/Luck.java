/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeonrewards.items.weapon.enchantments;

import com.watabou.pixeldungeonrewards.actors.Char;
import com.watabou.pixeldungeonrewards.items.weapon.Weapon;
import com.watabou.pixeldungeonrewards.sprites.ItemSprite;
import com.watabou.pixeldungeonrewards.sprites.ItemSprite.Glowing;

public class Luck extends Weapon.Enchantment {

	private static final String TXT_LUCKY	= "lucky %s";
	
	private static ItemSprite.Glowing GREEN = new ItemSprite.Glowing( 0x00FF00 );
	
	@Override
	public boolean proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		int level = Math.max( 0, weapon.effectiveLevel() );
		
		int dmg = damage;
		for (int i=1; i <= level+1; i++) {
			dmg = Math.max( dmg, attacker.damageRoll() - i );
		}
		
		if (dmg > damage) {
			defender.damage( dmg - damage, this );
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String name( String weaponName) {
		return String.format( TXT_LUCKY, weaponName );
	}

	@Override
	public Glowing glowing() {
		return GREEN;
	}
}
