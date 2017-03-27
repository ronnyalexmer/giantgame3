package org.escoladeltreball.giantgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by jmendez on 3/16/17.
 */

public class Cloud extends Sprite {

    private World world;
    private Body body;
    private String cloudName;

    public Cloud(World world,  String cloud, String cloudName, float x, float y) {
        super(new Texture(cloud));
        this.world = world;
        this.cloudName = cloudName;
        setSpritePosition(x, y);
//        this.setPosition(
//                x - this.getWidth() / 2,
//                y - this.getHeight() / 2);
        createBody();
    }

    private void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((getX() + getWidth() / 2f - 50) / GameInfo.PPM, (getY() + getHeight() / 2) / GameInfo.PPM);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()  / 2 - 15) / GameInfo.PPM,
                getHeight() / 2 / GameInfo.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Cloud");
        fixture.setSensor(true);
        shape.dispose();

    }

    public void setSpritePosition(float x, float y) {

        setPosition(x, y);
        createBody();
    }

    public String getCloudName() {
        return cloudName;
    }
}
