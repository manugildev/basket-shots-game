package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import configuration.Settings;
import gameobjects.GameObject;
import gameworld.GameRenderer;
import gameworld.GameWorld;
import helpers.AssetLoader;
import helpers.FlatColors;
import tweens.SpriteAccessor;

public class BallsUI extends GameObject {

    public Text timeText;
    TweenCallback cb;
    public GameObject ball;
    public int balls;

    public BallsUI(final GameWorld world, float x, float y, float width, float height,
                   TextureRegion texture,
                   Color color, Shape shape) {
        super(world, x, y, width, height, texture, color, shape);
        balls = Settings.NUM_OF_INITIAL_BALLS_FOR_PLAYING;
        ball = new GameObject(world, x + width / 2 - 80, y + 20, height - 40, height - 40,
                AssetLoader.ball,
                FlatColors.WHITE, Shape.CIRCLE);
        timeText = new Text(world,ball.getPosition().x+ball.getSprite().getWidth()+5, y, width, height, AssetLoader.square, Color.WHITE,
                "x " + balls, AssetLoader.font08, FlatColors.parseColor(Settings.TEXT_COLOR), 22,
                Align.left);

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        ball.update(delta);
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.render(batch, shapeRenderer);
        timeText.render(batch, shapeRenderer, GameRenderer.fontShader);
        ball.render(batch, shapeRenderer);
    }

    public void addBall(int i) {
        balls += i;
        timeText.setText("x " + balls);
    }

    public void removeBall(int i) {
        balls -= i;
        timeText.setText("x " + balls);
        if (balls == 0) {
            timeText.setText("x 0");

        }
    }

    public void effectBall() {
        ball.sprite.setScale(1);
        Tween.to(ball.sprite, SpriteAccessor.SCALE, .2f).target(1.2f).repeatYoyo(1, 0f).start(
                ball.getManager());
    }
}
