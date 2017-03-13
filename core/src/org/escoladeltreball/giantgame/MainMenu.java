package org.escoladeltreball.giantgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by jmendez on 3/13/17.
 */

public class MainMenu implements Screen {

    private MainGame game;
    private SpriteBatch batch;
    private Texture bg;
    private Player player;
    private World world;

    public MainMenu(MainGame game) {
        this.game = game;
        world = new World(new Vector2(0, -9.8f), true);
        batch = game.getBatch();
        bg = new Texture("game-bg.png");
        player = new Player(
                world,
                "player-1.png",
                GameInfo.H_WIDTH,
                GameInfo.H_HEIGHT
                );
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        player.updatePlayer();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(bg, 0, 0);
        batch.draw(player, player.getX(), player.getY());

        batch.end();

        world.step(
                Gdx.graphics.getDeltaTime(),
                6,
                2
        );
    }


    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        batch.dispose();
        player.getTexture().dispose();
    }
}
