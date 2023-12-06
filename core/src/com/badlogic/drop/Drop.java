package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drop extends Game {

	public void create() {
		this.setScreen(new Menu(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {

	}

}