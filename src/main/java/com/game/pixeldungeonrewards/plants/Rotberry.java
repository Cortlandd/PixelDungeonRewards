package com.game.pixeldungeonrewards.plants;

import com.watabou.noosa.audio.Sample;
import com.game.pixeldungeonrewards.Assets;
import com.game.pixeldungeonrewards.Dungeon;
import com.game.pixeldungeonrewards.actors.Char;
import com.game.pixeldungeonrewards.actors.blobs.Blob;
import com.game.pixeldungeonrewards.actors.blobs.ToxicGas;
import com.game.pixeldungeonrewards.actors.buffs.Buff;
import com.game.pixeldungeonrewards.actors.buffs.Roots;
import com.game.pixeldungeonrewards.actors.mobs.Mob;
import com.game.pixeldungeonrewards.effects.CellEmitter;
import com.game.pixeldungeonrewards.effects.Speck;
import com.game.pixeldungeonrewards.items.bags.Bag;
import com.game.pixeldungeonrewards.items.potions.PotionOfStrength;
import com.game.pixeldungeonrewards.scenes.GameScene;
import com.game.pixeldungeonrewards.sprites.ItemSpriteSheet;
import com.game.pixeldungeonrewards.utils.GLog;

public class Rotberry extends Plant {
	
	private static final String TXT_DESC = 
		"Berries of this shrub taste like sweet, sweet death.";
	
	{
		image = 7;
		plantName = "Rotberry";
	}
	
	@Override
	public void activate( Char ch ) {
		super.activate( ch );
		
		GameScene.add( Blob.seed( pos, 100, ToxicGas.class ) );
		
		Dungeon.level.drop( new Seed(), pos ).sprite.drop();
		
		if (ch != null) {
			Buff.prolong( ch, Roots.class, Roots.TICK * 3 );
		}
	}
	
	@Override
	public String desc() {
		return TXT_DESC;
	}
	
	public static class Seed extends Plant.Seed {
		{
			plantName = "Rotberry";
			
			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_ROTBERRY;
			
			plantClass = Rotberry.class;
			alchemyClass = PotionOfStrength.class;
		}
		
		@Override
		public boolean collect( Bag container ) {
			if (super.collect( container )) {
				
				if (Dungeon.level != null) {
					for (Mob mob : Dungeon.level.mobs) {
						mob.beckon( Dungeon.hero.pos );
					}
					
					GLog.w( "The seed emits a roar that echoes throughout the dungeon!" );
					CellEmitter.center( Dungeon.hero.pos ).start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );
					Sample.INSTANCE.play( Assets.SND_CHALLENGE );
				}
				
				return true;
			} else {
				return false;
			}
		}
		
		@Override
		public String desc() {
			return TXT_DESC;
		}
	}
}