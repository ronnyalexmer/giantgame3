package org.escoladeltreball.giantgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by jmendez on 3/13/17.
 */

public class MainMenu implements Screen, ContactListener {

    private GameMain game;
    private SpriteBatch batch;
    private Texture bg;
    private Player player;
    Cloud cloud;
    private World world;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    public MainMenu(GameMain game) {
        this.game = game;
        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        box2DCamera.position.set(GameInfo.H_WIDTH, GameInfo.H_HEIGHT, 0);
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -9.8f), true);
        world.setContactListener(this);
        batch = game.getBatch();
        bg = new Texture("game-bg.png");
        player = new Player(
                world,

                GameInfo.H_WIDTH,
                GameInfo.H_HEIGHT + 250
                );

        cloud = new Cloud(world, "cloud-1.png", "Cloud 1", GameInfo.H_WIDTH, GameInfo.H_HEIGHT - 250);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        player.updatePlayer();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(bg, 0, 0);
        batch.draw(player, player.getX(), player.getY() - player.getHeight() / 2);
        batch.draw(cloud, cloud.getX(), cloud.getY());
        batch.end();

        debugRenderer.render(world, box2DCamera.combined);

        world.step(
                Gdx.graphics.getDeltaTime(),
                6,
                2
        );
    }

    private void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            player.getBody().applyLinearImpulse(new Vector2(-1, 0), player.getBody().getWorldCenter(), true);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            player.getBody().applyLinearImpulse(new Vector2(1, 0), player.getBody().getWorldCenter(), true);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.getBody().applyLinearImpulse(new Vector2(0, 3), player.getBody().getWorldCenter(), true);
        }
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

    /**
     * Called when two fixtures begin to touch.
     *
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {
        System.out.println(contact.getFixtureA().getUserData());
        System.out.println(contact.getFixtureB().getUserData());
    }

    /**
     * Called when two fixtures cease to touch.
     *
     * @param contact
     */
    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
