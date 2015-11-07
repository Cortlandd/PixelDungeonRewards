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
package com.game.pixeldungeonrewards.plants;

import com.game.pixeldungeonrewards.Dungeon;
import com.game.pixeldungeonrewards.actors.Char;
import com.game.pixeldungeonrewards.actors.blobs.Blob;
import com.game.pixeldungeonrewards.actors.blobs.Fire;
import com.game.pixeldungeonrewards.effects.CellEmitter;
import com.game.pixeldungeonrewards.effects.particles.FlameParticle;
import com.game.pixeldungeonrewards.items.potions.PotionOfLiquidFlame;
import com.game.pixeldungeonrewards.scenes.GameScene;
import com.game.pixeldungeonrewards.sprites.ItemSpriteSheet;

public class Firebloom extends Plant {

	private static final String TXT_DESC = "When something touches a Firebloom, it bursts into flames.";
	
	{
		image = 0;
		plantName = "Firebloom";
	}
	
	@Override
	public void activate( Char ch ) {
		super.activate( ch );
		
		GameScene.add( Blob.seed( pos, 2, Fire.class ) );
		
		if (Dungeon.visible[pos]) {
			CellEmitter.get( pos ).burst( FlameParticle.FACTORY, 5 );
		}
	}
	
	@Override
	public String desc() {
		return TXT_DESC;
	}
	
	public static class Seed extends Plant.Seed {
		{
			plantName = "Firebloom";
			
			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_FIREBLOOM;
			
			plantClass = Firebloom.class;
			alchemyClass = PotionOfLiquidFlame.class;
		}
		
		@Override
		public String desc() {
			return TXT_DESC;
		}
	}
}
