package gameworld;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

import MainGame.ActionResolver;
import MainGame.BasketballGame;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import configuration.Configuration;
import configuration.Settings;
import gameobjects.Ball;
import gameobjects.Basket;
import gameobjects.Floor;
import gameobjects.GameObject;
import helpers.AssetLoader;
import helpers.FlatColors;
import screens.MenuScreen;
import tweens.Value;
import ui.Banner;
import ui.Launcher;
import ui.MenuButton;
import ui.Text;

public class GameWorld {
    public float gameWidth, gameHeight;
    public int score = 0;
    private BasketballGame game;
    public ActionResolver actionResolver;

    //GAMEOBJECTS
    public Floor floor;
    public Ball ball;
    public Basket basket;
    public Launcher launcher;
    public Text scoreT;
    public Banner banner;
    public GameObject background, top;
    public ArrayList<Ball> balls = new ArrayList<Ball>();
    public MenuButton homeButton;

    //BOX2D
    public World worldB;
    Body body;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    Sprite sprite;


    public GameWorld(BasketballGame game, ActionResolver actionResolver, float gameWidth,
                     float gameHeight) {
        this.game = game;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.actionResolver = actionResolver;
        start();
    }

    private void start() {
        worldB = new World(new Vector2(0, Settings.WORLD_GRAVITY), true);
        debugRenderer = new Box2DDebugRenderer();

        //GAMEOBJECTS
        background = new GameObject(this, 0, 0, gameWidth, gameHeight, AssetLoader.background,
                Color.WHITE,
                GameObject.Shape.RECTANGLE);
        top = new GameObject(this, 0, 0, gameWidth, gameHeight, AssetLoader.square,
                Color.BLACK, GameObject.Shape.RECTANGLE);
        top.fadeOut(.5f, 0f);
        floor = new Floor(this, 1, 0, gameWidth - 2, 150, AssetLoader.square, FlatColors.GREY,
                GameObject.Shape.RECTANGLE);
        /*ball = new Ball(this, gameWidth / 2, gameHeight / 2, 100, 100, AssetLoader.ball,
                FlatColors.WHITE, GameObject.Shape.CIRCLE);*/
        for (int i = 0; i < Settings.NUM_OF_INITIAL_BALLS; i++) {
            Ball b = new Ball(this, gameWidth / 2, gameHeight / 2, 100, 100, AssetLoader.ball,
                    FlatColors.WHITE, GameObject.Shape.CIRCLE);
            balls.add(b);
        }
        basket = new Basket(this, 100, 100, 50, gameHeight - 360, AssetLoader.square,
                FlatColors.GREY,
                GameObject.Shape.RECTANGLE);
        launcher = new Launcher(this);
        scoreT = new Text(this, 700, gameHeight - 150, gameWidth - 1400, 100, AssetLoader.square,
                Color.WHITE,
                "0", AssetLoader.font, FlatColors.BLACK, 10,
                Align.center);
        banner = new Banner(this, 0, gameHeight / 2 - (Settings.BANNER_HEIGHT / 2), gameWidth,
                Settings.BANNER_HEIGHT, AssetLoader.square, FlatColors.RED,
                GameObject.Shape.RECTANGLE);
        homeButton = new MenuButton(this,50,gameHeight-50-(AssetLoader.homeButton.getRegionHeight())/2,AssetLoader.homeButton.getRegionWidth()/2,AssetLoader.homeButton.getRegionHeight()/2,AssetLoader.homeButton,FlatColors.WHITE,
                GameObject.Shape.RECTANGLE);


    }

    public void update(float delta) {
        worldB.step(1f / 60f, 6, 2);
        background.update(delta);
        floor.update(delta);
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).update(delta);
        }
        basket.update(delta);
        launcher.update(delta);
        scoreT.update(delta);
        banner.update(delta);
        top.update(delta);
        homeButton.update(delta);
    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        if (Configuration.DEBUG) {
            batch.end();
            debugMatrix = batch.getProjectionMatrix().cpy().scale(Settings.PTM, Settings.PTM, 0);
            batch.begin();


        }
        background.render(batch, shapeRenderer);
        floor.render(batch, shapeRenderer);
        launcher.render(batch, shapeRenderer);
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).render(batch, shapeRenderer);
        }
        basket.render(batch, shapeRenderer);
        scoreT.render(batch, shapeRenderer, GameRenderer.fontShader);
        banner.render(batch, shapeRenderer);
        homeButton.render(batch,shapeRenderer);
        top.render(batch, shapeRenderer);
        if (Configuration.DEBUG) debugRenderer.render(worldB, debugMatrix);

    }

    public void addScore(int i) {
        score += i;
        scoreT.setText(score + "");
    }

    public Ball getIdleBall() {
        for (int i = 0; i < balls.size(); i++) {
            if (balls.get(i).isIdle()) {
                return balls.get(i);
            }
        }

        return addBall();
    }

    private Ball addBall() {
        balls.add(new Ball(this, gameWidth / 2, gameHeight / 2, 100, 100, AssetLoader.ball,
                FlatColors.WHITE, GameObject.Shape.CIRCLE));
        return balls.get(balls.size() - 1);
    }

    public void goToMenu() {
        finishEffects();
        Value timer = new Value();
        timer.setValue(0);
        Tween.to(timer, -1, .5f).target(1).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new MenuScreen(game, actionResolver));
            }
        }).setCallbackTriggers(TweenCallback.COMPLETE).start(background.getManager());
    }

    private void finishEffects() {
        top.fadeIn(0.5f, 0f);
    }
}
