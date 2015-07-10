package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import configuration.Settings;
import gameworld.GameWorld;

/**
 * Created by ManuGil on 08/07/15.
 */
public class Ball extends GameObject {

    public Body body;
    public boolean isScored;

    public Ball(GameWorld world, float x, float y, float width, float height,
                TextureRegion texture,
                Color color, Shape s) {
        super(world, x, y, width, height, texture, color, s);
        isScored = false;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) /
                Settings.PTM, (sprite.getY() + sprite.getHeight() / 2) / Settings.PTM);
        body = world.worldB.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(sprite.getWidth() / 2 / Settings.PTM,
                sprite.getHeight() / 2 / Settings.PTM));
        shape.setRadius(circle.radius / Settings.PTM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Settings.CATEGORY_BALL;
        fixtureDef.filter.maskBits = Settings.MASK_BALL;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.8f;
        fixtureDef.restitution = 0.6f;
        body.createFixture(fixtureDef);
        shape.dispose();
        circle.setRadius(sprite.getWidth() / 2);

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        sprite.setPosition((body.getPosition().x * Settings.PTM),
                (body.getPosition().y * Settings.PTM));
        circle.setPosition(body.getWorldPoint(body.getLocalCenter()).x * Settings.PTM,
                body.getWorldPoint(body.getLocalCenter()).y * Settings.PTM);
        sprite.setRotation((float) Math.toDegrees(body.getAngle()));
        sprite.setOrigin(0, 0);
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.render(batch, shapeRenderer);
    }

    @Override
    public void setPosition(Vector2 pos) {
        body.setLinearVelocity(new Vector2());
        body.setTransform(new Vector2(pos.x - circle.radius / Settings.PTM,
                pos.y - circle.radius / Settings.PTM), 0);
    }

    public void setTransform(Vector2 point1) {
        this.body.setTransform(new Vector2((point1.x - circle.radius) / Settings.PTM,
                (point1.y - circle.radius) / Settings.PTM), 0);
    }
}
