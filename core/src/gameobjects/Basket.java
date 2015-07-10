package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import configuration.Settings;
import gameworld.GameWorld;
import helpers.AssetLoader;
import helpers.FlatColors;

public class Basket extends GameObject {

    Body body;
    GameObject contact1, contact2;
    public boolean c1 = false, c2 = false;

    public Basket(GameWorld world, float x, float y, float width, float height,
                  TextureRegion texture,
                  Color color, Shape s) {
        super(world, x, y, width, height, texture, color, s);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Settings.PTM,
                (sprite.getY() + sprite.getHeight() / 2) / Settings.PTM);
        body = world.worldB.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2 / Settings.PTM, sprite.getHeight() / 2 / Settings.PTM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Settings.CATEGORY_BASKET;
        fixtureDef.filter.maskBits = Settings.MASK_BASKET;
        body.createFixture(fixtureDef);
        shape.dispose();

        BodyDef bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.StaticBody;
        bodyDef1.position.set((sprite.getX() + 30 + sprite.getWidth() / 2) / Settings.PTM,
                (sprite.getY() + 150 + sprite.getHeight() / 2) / Settings.PTM);
        body = world.worldB.createBody(bodyDef1);

        PolygonShape shape1 = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2 / Settings.PTM, 5 / Settings.PTM);
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape1;
        fixtureDef1.filter.categoryBits = Settings.CATEGORY_BASKET;
        fixtureDef1.filter.maskBits = Settings.MASK_BASKET;
        body.createFixture(fixtureDef1);
        shape.dispose();

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        bodyDef2.position.set((sprite.getX() + 260 + sprite.getWidth() / 2) / Settings.PTM,
                (sprite.getY() + 150 + sprite.getHeight() / 2) / Settings.PTM);
        body = world.worldB.createBody(bodyDef2);

        PolygonShape shape2 = new PolygonShape();
        shape.setAsBox(10 / Settings.PTM, 5 / Settings.PTM);
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape2;
        fixtureDef2.filter.categoryBits = Settings.CATEGORY_BASKET;
        fixtureDef2.filter.maskBits = Settings.MASK_BASKET;
        body.createFixture(fixtureDef2);
        shape.dispose();

        contact1 = new GameObject(world,
                (sprite.getX() + 60 + sprite.getWidth() / 2) + (sprite.getWidth() / 2),
                (sprite.getY() + 150 + sprite.getHeight() / 2), 120, 5,
                AssetLoader.square, FlatColors.RED, Shape.RECTANGLE);

        contact2 = new GameObject(world,
                (sprite.getX() + 60 + sprite.getWidth() / 2) + (sprite.getWidth() / 2),
                (sprite.getY() + 20 + sprite.getHeight() / 2), 120, 5,
                AssetLoader.square, FlatColors.BLUE, Shape.RECTANGLE);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        contact1.update(delta);
        contact2.update(delta);
        scoreCollision();
    }

    private void scoreCollision() {
        if (!world.ball.isScored) {
            if (Intersector.overlaps(world.ball.circle, contact1.getRectangle())) {
                c1 = true;
            }
            if (c1 && Intersector.overlaps(world.ball.circle, contact2.getRectangle())) {
                c2 = true;
                world.ball.isScored = true;
                scoreAPoint();
            }
        }
    }

    private void scoreAPoint() {
        world.addScore(1);
    }

    public void resetScoreLogic(){
        world.ball.isScored = false;
        c1 = false;
        c2 = false;
    }


    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.render(batch, shapeRenderer);
        contact1.render(batch, shapeRenderer);
        contact2.render(batch, shapeRenderer);
    }


}
