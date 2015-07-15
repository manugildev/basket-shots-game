package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import configuration.Configuration;
import configuration.Settings;
import gameobjects.GameObject;
import gameworld.GameRenderer;
import gameworld.GameWorld;
import helpers.AssetLoader;
import helpers.FlatColors;
import tweens.Value;

public class TimeUI extends GameObject {
    public Text timeText;
    TweenCallback cb;
    Tween timerTween;
    Value timer = new Value();
    TweenManager manager;

    public TimeUI(GameWorld world, float x, float y, float width, float height,
                  TextureRegion texture,
                  Color color, Shape shape) {
        super(world, x, y, width, height, texture, color, shape);
        timeText = new Text(world, x, y, width, height, AssetLoader.square, Color.WHITE,
                "Time: 0", AssetLoader.font08, FlatColors.parseColor(Settings.TEXT_COLOR), 22,
                Align.center);
        manager = new TweenManager();
        timer.setValue(0);
        startTimer(Settings.INITIAL_TIME, 2f);

        cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                timerTween.kill();
                startTimer(Settings.INITIAL_TIME, 0f);
            }
        };

    }

    @Override
    public void update(float delta) {
        manager.update(delta);
        super.update(delta);
        float value = timer.getValue();
        if (timer.getValue() >= 10)
            setTimeText("" + Math.round(value * 100) / 100);
        else setTimeText("0" + Math.round(value * 100) / 100);
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.render(batch, shapeRenderer);
        timeText.render(batch, shapeRenderer, GameRenderer.fontShader);
    }

    public void setTimeText(String time) {
        timeText.setText(Configuration.TIME_TEXT + time);
    }

    public void startTimer(float time, float delay) {
        timerTween = null;
        timer = new Value();
        timer.setValue(time);
        timerTween = Tween.to(timer, -1, time).delay(delay).target(0).ease(
                TweenEquations.easeNone).start(manager);
    }

    public void reset() {
        timerTween = null;
        timerTween = Tween.to(timer, -1, .3f).target(Settings.INITIAL_TIME).ease(
                TweenEquations.easeInOutSine).setCallback(cb)
                .setCallbackTriggers(TweenCallback.COMPLETE).start(getManager());
    }

}
