package menuworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

import MainGame.ActionResolver;
import MainGame.BasketballGame;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import configuration.Configuration;
import gameobjects.GameObject;
import gameworld.GameWorld;
import helpers.AssetLoader;
import helpers.FlatColors;
import screens.GameScreen;
import screens.MenuScreen;
import tweens.SpriteAccessor;
import tweens.Value;
import ui.MenuButton;
import ui.MusicButton;
import ui.ScoresUI;
import ui.SoundButton;

public class MenuWorld extends GameWorld {

    private GameObject background, title, top;
    public ArrayList<MenuButton> menuButtons = new ArrayList<MenuButton>();
    private ArrayList<TextureRegion> buttonTextures = new ArrayList<TextureRegion>();
    public ActionResolver actionResolver;
    public MusicButton musicButton;
    public SoundButton soundButton;

    public MenuWorld(BasketballGame game, ActionResolver actionResolver, float gameWidth,
                     float gameHeight) {
        super(game, actionResolver, gameWidth, gameHeight, null);
        this.actionResolver = actionResolver;
        top = new GameObject(this, 0, 0, gameWidth, gameHeight, AssetLoader.square, Color.BLACK,
                GameObject.Shape.RECTANGLE);
        background = new GameObject(this, 0, 0, gameWidth, gameHeight, AssetLoader.backgroundMenu,
                FlatColors.WHITE,
                GameObject.Shape.RECTANGLE);
        title = new GameObject(this, gameWidth / 2 - (AssetLoader.title.getRegionWidth() / 2),
                gameHeight / 2 - (AssetLoader.title.getRegionHeight() / 2),
                AssetLoader.title.getRegionWidth(), AssetLoader.title.getRegionHeight(),
                AssetLoader.title,
                FlatColors.WHITE, GameObject.Shape.RECTANGLE);
        scoreUI = new ScoresUI(this, gameWidth - 400,
                gameHeight - 45 - AssetLoader.scoreBack.getRegionHeight(),
                AssetLoader.scoreBack.getRegionWidth(), AssetLoader.scoreBack.getRegionHeight(),
                AssetLoader.scoreBack, FlatColors.WHITE,
                GameObject.Shape.RECTANGLE);
        scoreUI.setScoreText(Configuration.BEST_TEXT + AssetLoader.getHighScore());
        buttonTextures.add(AssetLoader.playButton);
        buttonTextures.add(AssetLoader.practiceButton);
        buttonTextures.add(AssetLoader.scoresButton);
        buttonTextures.add(AssetLoader.shareButton);

        for (int i = 0; i < buttonTextures.size(); i++) {
            MenuButton b = new MenuButton(this, gameWidth - 500,
                    gameHeight - AssetLoader.playButton.getRegionHeight() - 250 - (150 * i),
                    AssetLoader.playButton.getRegionWidth(),
                    AssetLoader.playButton.getRegionHeight(), buttonTextures.get(i),
                    FlatColors.WHITE, GameObject.Shape.RECTANGLE);
            menuButtons.add(b);
        }

        musicButton = new MusicButton(
                gameWidth / 2 - (AssetLoader.musicButton.getRegionWidth() / 2) - 20,
                scoreUI.getRectangle().y, AssetLoader.musicButton.getRegionWidth() / 2,
                AssetLoader.noMusicButton.getRegionHeight() / 2, AssetLoader.noMusicButton,
                AssetLoader.musicButton, Color.WHITE);

        soundButton = new SoundButton(gameWidth / 2 + 20, scoreUI.getRectangle().y,
                AssetLoader.soundButton.getRegionWidth() / 2,
                AssetLoader.soundButton.getRegionHeight() / 2, AssetLoader.nosoundButton,
                AssetLoader.soundButton, Color.WHITE);

        startEffects();
        checkIfMusicWasPlaying();
    }

    private void startEffects() {
        top.fadeOut(0.5f, 0f);
        title.effectY(title.getPosition().y + gameHeight, title.getPosition().y, .5f, .5f);
        Tween.to(title.sprite, SpriteAccessor.SCALE, 0.5f).target(0.98f).repeatYoyo(10000, 0)
                .start(title.getManager());
        for (int i = 0; i < menuButtons.size(); i++) {
            menuButtons.get(i).effectX(menuButtons.get(i).getPosition().x + gameWidth / 2,
                    menuButtons.get(i).getPosition().x, .5f, 0.04f * i + (0.5f));
        }
        scoreUI.effectY(scoreUI.getPosition().y + gameHeight, scoreUI.getPosition().y, .5f, .6f);

    }

    public void update(float delta) {
        top.update(delta);
        background.update(delta);
        title.update(delta);
        for (int i = 0; i < menuButtons.size(); i++) {
            menuButtons.get(i).update(delta);
        }
        scoreUI.update(delta);
        musicButton.update(delta);
        musicButton.sprite.setY(scoreUI.getRectangle().y);
        soundButton.update(delta);
        soundButton.sprite.setY(scoreUI.getRectangle().y);

    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        background.render(batch, shapeRenderer);
        title.render(batch, shapeRenderer);
        for (int i = 0; i < menuButtons.size(); i++) {
            menuButtons.get(i).render(batch, shapeRenderer);
        }
        scoreUI.renderMenu(batch, shapeRenderer);
        musicButton.draw(batch);
        soundButton.draw(batch);
        top.render(batch, shapeRenderer);
    }

    private void checkIfMusicWasPlaying() {
        if (AssetLoader.getVolume()) {
            AssetLoader.music.setLooping(true);
            AssetLoader.music.play();
            AssetLoader.music.setVolume(Configuration.MUSIC_VOLUME);
            AssetLoader.setVolume(true);
        } else AssetLoader.music.pause();

        if (AssetLoader.music.isPlaying()) musicButton.setIsPressed(false);
        else musicButton.setIsPressed(true);

        if (AssetLoader.getSounds()) soundButton.setIsPressed(false);
        else soundButton.setIsPressed(true);
    }


    public void goToGameScreen() {
        finishEffects();
        Value timer = new Value();
        Tween.to(timer, -1, 1f).target(0).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                MenuScreen.game
                        .setScreen(new GameScreen(MenuScreen.game, actionResolver, STATE.GAME));
            }
        }).setCallbackTriggers(TweenCallback.COMPLETE).start(title.getManager());

    }

    private void finishEffects() {
        top.fadeIn(.5f, .5f);
        title.effectY(title.getPosition().y, title.getPosition().y + gameHeight, .5f, .0f);
        scoreUI.effectY(scoreUI.getPosition().y, scoreUI.getPosition().y + gameHeight, .5f, 0f);
        for (int i = menuButtons.size() - 1; i >= 0; i--) {
            menuButtons.get(i).effectX(menuButtons.get(i).getPosition().x,
                    menuButtons.get(i).getPosition().x + gameWidth / 2, .5f,
                    (0.04f * menuButtons.size()) - (0.04f * i));
        }
    }

    public void goToPracticeMode() {
        MenuScreen.game.setScreen(new GameScreen(MenuScreen.game, actionResolver, STATE.PRACTICE));
    }
}
