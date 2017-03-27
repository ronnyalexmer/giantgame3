package org.escoladeltreball.giantgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by jmendez on 3/27/17.
 */

public class CloudsController {

    private final float DISTANCE_BETWEEN_CLOUDS = 250F;

    private World world;
    private Array<Cloud> clouds = new Array<Cloud>();
    private float minX;
    private float maxX;
    private Random random = new Random();

    public CloudsController(World world) {
        this.world = world;
        minX = GameInfo.H_WIDTH - 150;
        maxX = GameInfo.H_WIDTH + 100;
        createClouds();
        positionClouds();
    }

    private void createClouds() {
        for (int i = 0; i < 2; i++) {
            clouds.add(new Cloud(world, "dark-cloud.png", "Dark Cloud", 0, 0));
        }

        for (int i = 0; i < 6; i++) {
            clouds.add(new Cloud(world, "cloud-" + ((i + 1) % 3 + 1) + ".png", "White Cloud", 0, 0));
        }

        while(clouds.get(0).getCloudName().equals("Dark Cloud")) {
            clouds.shuffle();
        }
    }

    private void positionClouds() {
        float positionY = GameInfo.H_HEIGHT;
        int controlX = 0;
        for(Cloud c: clouds) {
            float tempX = 0;
            if(controlX == 0) {
                tempX = randomBetweenNumbers(maxX - 50, maxX);
                controlX = 1;
            } else if (controlX == 1) {
                tempX = randomBetweenNumbers(minX + 50, minX);
                controlX = 0;
            }
            //tempX -= 100;
            c.setSpritePosition(tempX, positionY);
            positionY -= DISTANCE_BETWEEN_CLOUDS;
        }
    }

    public void drawClouds(SpriteBatch batch) {
        for(Cloud c: clouds) {
            batch.draw(c, c.getX(), c.getY());
        }
    }

    private float randomBetweenNumbers(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

}
