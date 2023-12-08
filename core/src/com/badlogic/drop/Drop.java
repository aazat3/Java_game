package com.badlogic.drop;

import com.badlogic.gdx.Game;

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