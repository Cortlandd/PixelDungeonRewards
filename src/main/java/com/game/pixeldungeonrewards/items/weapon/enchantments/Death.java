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
package com.game.pixeldungeonrewards.items.weapon.enchantments;

import com.game.pixeldungeonrewards.Badges;
import com.game.pixeldungeonrewards.actors.Char;
import com.game.pixeldungeonrewards.actors.hero.Hero;
import com.game.pixeldungeonrewards.effects.particles.ShadowParticle;
import com.game.pixeldungeonrewards.items.weapon.Weapon;
import com.game.pixeldungeonrewards.sprites.ItemSprite;
import com.game.pixeldungeonrewards.sprites.ItemSprite.Glowing;
import com.game.utils.Random;

public class Death extends Weapon.Enchantment {

	private static final String TXT_GRIM	= "grim %s";
	
	private static ItemSprite.Glowing BLACK = new ItemSprite.Glowing( 0x000000 );
	
	@Override
	public boolean proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		// lvl 0 - 8%
		// lvl 1 ~ 9%
		// lvl 2 ~ 10%
		int level = Math.max( 0, weapon.effectiveLevel() );
		
		if (Random.Int( level + 100 ) >= 92) {
			
			defender.damage( defender.HP, this );
			defender.sprite.emitter().burst( ShadowParticle.UP, 5 );
			
			if (!defender.isAlive() && attacker instanceof Hero) {
				Badges.validateGrimWeapon();
			}
			
			return true;
			
		} else {
			
			return false;
			
		}
	}
	
	@Override
	public Glowing glowing() {
		return BLACK;
	}
	
	@Override
	public String name( String weaponName) {
		return String.format( TXT_GRIM, weaponName );
	}

}
