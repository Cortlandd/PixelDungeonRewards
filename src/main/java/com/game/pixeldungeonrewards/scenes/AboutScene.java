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
package com.game.pixeldungeonrewards.scenes;

import android.content.Intent;
import android.net.Uri;

import com.watabou.input.Touchscreen.Touch;
import com.watabou.noosa.BitmapTextMultiline;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.TouchArea;
import com.game.pixeldungeonrewards.PixelDungeon;
import com.game.pixeldungeonrewards.effects.Flare;
import com.game.pixeldungeonrewards.ui.Archs;
import com.game.pixeldungeonrewards.ui.ExitButton;
import com.game.pixeldungeonrewards.ui.Icons;
import com.game.pixeldungeonrewards.ui.Window;

public class AboutScene extends PixelScene {

    private static final String MY_TITLE = "Pixel Dungeon Rewards";
	private static final String MY_TXT =
		"Pixel Dungeon Rewards is a SaturnUp" +
		"platform implementation of the game" +
		"Pixel Dungeon. With Pixel Dungeon Rewards";
	private static final String MY_LINK = "https://github.com/Cortlandd/PixelDungeonRewards";


	private static final String TXT =
		"This game is inspired by Brian Walker's Brogue. " +
		"Try it on Windows, Mac OS or Linux - it's awesome! ;)\n\n" +
		"Visit official website for additional info:";

	private static final String LNK = "pixeldungeon.watabou.ru";

	@Override
	public void create() {
		super.create();

		BitmapTextMultiline myText = createMultiline( MY_TXT, 8 );
        myText.maxWidth = Math.min( Camera.main.width, 120 );
        myText.measure();
		add(myText);

        myText.x = align((Camera.main.width - myText.width()) / 2);
        myText.y = align((Camera.main.height - myText.height()) / 2 - 40);

		BitmapTextMultiline myLink = createMultiline( MY_LINK, 8 );
        myLink.maxWidth = Math.min( Camera.main.width, 120 );
        myLink.measure();
        myLink.hardlight(Window.TITLE_COLOR);
		add( myLink );

        myLink.x = myText.x;
        myLink.y = myText.y + myText.height();

		TouchArea myHotArea = new TouchArea( myLink ) {
			@Override
			protected void onClick( Touch touch ) {
				Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( MY_LINK ) );
				Game.instance.startActivity( intent );
			}
		};
		add( myHotArea );

        BitmapTextMultiline myTitle = createMultiline( MY_TITLE, 8 );
        myTitle.maxWidth = Math.min( Camera.main.width, 120 );
        myTitle.measure();
        add( myTitle );

        myTitle.x = align( ( Camera.main.width - myText.width()) / 2 + 3 );
        myTitle.y = myText.y - myTitle.height - 10;

		Archs myArchs = new Archs();
        myArchs.setSize( Camera.main.width, Camera.main.height );
		addToBack(myArchs);


		// ===============================================================

		BitmapTextMultiline text = createMultiline( TXT, 8 );
		text.maxWidth = Math.min( Camera.main.width, 120 );
		text.measure();
		add( text );

		text.x = align( (Camera.main.width - text.width()) / 2 );
		text.y = align( (Camera.main.height - text.height()) / 2 + 45 );

		BitmapTextMultiline link = createMultiline( LNK, 8 );
		link.maxWidth = Math.min( Camera.main.width, 120 );
		link.measure();
		link.hardlight( Window.TITLE_COLOR );
		add( link );

		link.x = text.x;
		link.y = text.y + text.height();

		TouchArea hotArea = new TouchArea( link ) {
			@Override
			protected void onClick( Touch touch ) {
				Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "http://" + LNK ) );
				Game.instance.startActivity( intent );
			}
		};
		add( hotArea );

		Image wata = Icons.WATA.get();
		wata.x = align( (Camera.main.width - wata.width) / 2 );
		wata.y = text.y - wata.height - 8;
		add( wata );

		new Flare( 7, 64 ).color( 0x112233, true ).show( wata, 0 ).angularSpeed = +20;

		Archs archs = new Archs();
		archs.setSize( Camera.main.width, Camera.main.height );
		addToBack( archs );

		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );

		fadeIn();
	}

	@Override
	protected void onBackPressed() {
		PixelDungeon.switchNoFade( TitleScene.class );
	}
}
