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
import com.watabou.pixeldungeonrewards.actors.buffs.Buff;
import com.watabou.pixeldungeonrewards.actors.buffs.Burning;
import com.watabou.pixeldungeonrewards.effects.particles.FlameParticle;
import com.watabou.pixeldungeonrewards.items.weapon.Weapon;
import com.watabou.pixeldungeonrewards.sprites.ItemSprite;
import com.watabou.pixeldungeonrewards.sprites.ItemSprite.Glowing;
import com.watabou.utils.Random;

public class Fire extends Weapon.Enchantment {

	private static final String TXT_BLAZING	= "blazing %s";
	
	private static ItemSprite.Glowing ORANGE = new ItemSprite.Glowing( 0xFF4400 );
	
	@Override
	public boolean proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		// lvl 0 - 33%
		// lvl 1 - 50%
		// lvl 2 - 60%
		int level = Math.max( 0, weapon.effectiveLevel() );
		
		if (Random.Int( level + 3 ) >= 2) {
			
			if (Random.Int( 2 ) == 0) {
				Buff.affect( defender, Burning.class ).reignite( defender );
			}
			defender.damage( Random.Int( 1, level + 2 ), this );
			
			defender.sprite.emitter().burst( FlameParticle.FACTORY, level + 1 );
			
			return true;
			
		} else {
			
			return false;
			
		}
	}
	
	@Override
	public Glowing glowing() {
		return ORANGE;
	}
	
	@Override
	public String name( String weaponName ) {
		return String.format( TXT_BLAZING, weaponName );
	}

}
