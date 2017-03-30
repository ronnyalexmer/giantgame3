package org.escoladeltreball.giantgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by jmendez on 3/23/17.
 */

public class GamePlay implements Screen {

    private GameMain game;
    private SpriteBatch batch;
    private Sprite[] backgrounds;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;
    private float lastYposition;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    private World world;
    private CloudsController cloudsController;
    private Player player;

    public GamePlay(GameMain game) {
        this.game = game;
        this.batch = game.getBatch();
        mainCamera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.H_WIDTH, GameInfo.H_HEIGHT, 0);
        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);
        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        box2DCamera.position.set(GameInfo.H_WIDTH, GameInfo.H_HEIGHT, 0);
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, GameInfo.GRAVITY), true);
        cloudsController = new CloudsController(world);
        player = cloudsController.positionThePlayer(player);
        createBackgrounds();
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        drawBackgrounds();
        cloudsController.drawClouds(batch);

        player.drawPlayer(batch);

        batch.end();


        //  debugRenderer.render(world, box2DCamera.combined);

        batch.setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        player.updatePlayer();
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

    }

    private void update(float delta) {
        moveCamera();
        checkBackgoundsOutOfBounds();
        cloudsController.setCameraY(mainCamera.position.y);
        cloudsController.createAndArrangeClouds();
    }

    private void moveCamera() {
        mainCamera.position.y -=0.5;
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }

    private void createBackgrounds() {
        backgrounds = new Sprite[3];

        for (int i = 0; i < backgrounds.length; i++) {
            backgrounds[i] = new Sprite(new Texture("game-bg.png"));
            backgrounds[i].setPosition(0, -(i * backgrounds[i].getHeight()));
            lastYposition = Math.abs(backgrounds[i].getY());
        }

    }

    private void drawBackgrounds() {
        for (int i = 0; i < backgrounds.length; i++) {
            batch.draw(backgrounds[i], backgrounds[i].getX(), backgrounds[i].getY());
        }
    }

    private void checkBackgoundsOutOfBounds() {
        for (int i = 0; i < backgrounds.length; i++) {
            if ((backgrounds[i].getY() - backgrounds[i].getHeight() / 2f - 15 > mainCamera.position.y)) {
                float newPosition = backgrounds[i].getHeight() + lastYposition;
                backgrounds[i].setPosition(0, -newPosition);
                lastYposition = newPosition;
            }
        }
    }
}
