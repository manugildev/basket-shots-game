package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import configuration.Settings;
import gameworld.GameWorld;
import helpers.AssetLoader;
import helpers.FlatColors;
import tweens.Value;
import tweens.ValueAccessor;

/**
 * Created by ManuGil on 08/07/15.
 */
public class Ball extends GameObject {

    public Body body;
    public boolean isScored;
    public Sprite pointer;
    public enum BallState {FLYING, FLOOR, IDLE, LAUNCHER}

    public BallState ballState;

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
        Tween.registerAccessor(Value.class,new ValueAccessor());
        pointer = new Sprite(AssetLoader.square);
        pointer.setColor(FlatColors.RED);
        pointer.setSize(10,40);

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
        collisions();

        if(sprite.getY()>world.gameHeight+(circle.radius*2)){
            pointer.setPosition(body.getWorldPoint(body.getLocalCenter()).x * Settings.PTM,world.gameHeight-pointer.getHeight());
        }else{
            pointer.setPosition(-100,-100);
        }
    }


    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.render(batch, shapeRenderer);
        pointer.draw(batch);
    }

    private void collisions() {
        if (Intersector.overlaps(world.ball.circle,
                world.floor.rectangle) && isFlying()) {
            touchedFloor();
        }
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

    public void setToLauncher(Vector2 point1) {
        ballState = BallState.LAUNCHER;
        world.ball.setTransform(point1);
        world.ball.body.setLinearVelocity(0, 0);
        sprite.setAlpha(1);
    }

    public void flight(Vector2 vel) {
        body.setLinearVelocity(vel);
        body.setAngularVelocity(MathUtils.random(-10, 10));
        ballState = BallState.FLYING;
    }

    public void touchedFloor() {
        if (isFlying()) {
            fadeOut(1.3f, 0.1f);
            ballState = BallState.FLOOR;
            Value timer = new Value();
            Tween.to(timer, -1, 1.4f).target(1).setCallback(new TweenCallback() {
                @Override
                public void onEvent(int type, BaseTween<?> source) {
                    ballState = BallState.IDLE;
                }
            }).setCallbackTriggers(TweenCallback.COMPLETE).start(getManager());
        }
    }

    public boolean isFlying() {
        return ballState == BallState.FLYING;
    }

    public boolean isFloor() {
        return ballState == BallState.FLOOR;
    }

    public boolean isIdle() {
        return ballState == BallState.IDLE;
    }

    public boolean isLauncher() {
        return ballState == BallState.LAUNCHER;
    }

}
