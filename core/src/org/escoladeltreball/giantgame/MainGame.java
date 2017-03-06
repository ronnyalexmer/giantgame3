package org.escoladeltreball.giantgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {
	/*
	NOMES UN SPRITEBATCH PER GAME
	 */
	SpriteBatch batch;
	Texture img;
	Texture player1;
	
	@Override
	public void create () {

		batch = new SpriteBatch();
		img = new Texture("game-bg.png");
		player1 = new Texture("player-1.png");
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(img, 0, 0);
		batch.draw(player1, GameInfo.H_WIDTH - player1.getWidth()/2, GameInfo.H_HEIGHT-player1.getHeight()/2);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
