package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import configuration.Configuration;

public class AssetLoader {

    public static Texture logoTexture, dotT, arrowCapT, arrowBotT, ballT, backgroundT, netT, shadowT, pointerT;
    public static TextureRegion logo, square, dot, arrowCap, arrowBot, ball, background, net, shadow, pointer;

    public static Texture backgroundMenuT, titleT, buttonsT;
    public static TextureRegion backgroundMenu, title, playButton, practiceButton, scoresButton, shareButton, homeButton,
            scoreBack, timerBack, tutorial;

    private static Preferences prefs;
    public static BitmapFont font, font08;

    public static void load1() {
        logoTexture = getAssetTexture("logo.png");
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        logo = new TextureRegion(logoTexture, 0, 0, logoTexture.getWidth(),
                logoTexture.getHeight());
        load();
    }

    public static void load() {
        square = new TextureRegion(getAssetTexture("square.png"));
        dotT = getAssetTexture("dot.png");
        dotT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        dot = new TextureRegion(dotT);

        backgroundT = getAssetTexture("background.png");
        backgroundT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        background = new TextureRegion(backgroundT);

        //ARROW
        arrowCapT = getAssetTexture("arrowCap.png");
        arrowCapT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        arrowBotT = getAssetTexture("arrowBot.png");
        arrowBotT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        arrowCap = new TextureRegion(arrowCapT);
        arrowBot = new TextureRegion(arrowBotT);

        //BALL
        ballT = getAssetTexture("ball.png");
        ballT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        ball = new TextureRegion(ballT);

        //NET
        netT = getAssetTexture("net.png");
        netT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        net = new TextureRegion(netT);

        //shadow
        shadowT = getAssetTexture("shadow.png");
        shadowT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        shadow = new TextureRegion(shadowT);

        //pointer
        pointerT = getAssetTexture("pointer.png");
        pointerT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        pointer = new TextureRegion(pointerT);


        //MENU
        //BACKGROUND MENU
        backgroundMenuT = getAssetTexture("background_menu.png");
        backgroundMenuT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        backgroundMenu = new TextureRegion(backgroundMenuT);

        //TITLE LOGO
        titleT = getAssetTexture("title.png");
        titleT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        title = new TextureRegion(titleT);

        //BUTTONS
        int numOfLines = 5;
        buttonsT = getAssetTexture("buttons.png");
        buttonsT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playButton = new TextureRegion(buttonsT, 0, (buttonsT.getHeight() / numOfLines) * 0,
                buttonsT.getWidth(), buttonsT.getHeight() / numOfLines);
        practiceButton = new TextureRegion(buttonsT, 0, (buttonsT.getHeight() / numOfLines) * 1,
                buttonsT.getWidth(), buttonsT.getHeight() / numOfLines);
        scoresButton = new TextureRegion(buttonsT, 0, (buttonsT.getHeight() / numOfLines) * 2,
                buttonsT.getWidth(), buttonsT.getHeight() / numOfLines);
        shareButton = new TextureRegion(buttonsT, 0, (buttonsT.getHeight() / numOfLines) * 3,
                buttonsT.getWidth(), buttonsT.getHeight() / numOfLines);
        homeButton = new TextureRegion(buttonsT, 0, (buttonsT.getHeight() / numOfLines) * 4,
                168, buttonsT.getHeight() / numOfLines);

        //SCOREBACK
        scoreBack = new TextureRegion(getAssetTexture("scoreback.png"));
        timerBack = new TextureRegion(getAssetTexture("timerback.png"));
        tutorial = new TextureRegion(getAssetTexture("tutorial.png"));

        //FONTS
        //LOADING FONT
        Texture tfont = new Texture(Gdx.files.internal("misc/font.png"), true);
        tfont.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);

        font = new BitmapFont(Gdx.files.internal("misc/font.fnt"), new TextureRegion(tfont),
                true);
        font.getData().setScale(1.5f, -1.5f);
        font.setColor(FlatColors.WHITE);

        font08 = new BitmapFont(Gdx.files.internal("misc/font.fnt"), new TextureRegion(tfont),
                true);
        font08.getData().setScale(0.8f, -0.8f);
        font08.setColor(FlatColors.WHITE);

        //PREFERENCES
        prefs = Gdx.app.getPreferences(Configuration.GAME_NAME);

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        if (!prefs.contains("games")) {
            prefs.putInteger("games", 0);
        }
    }

    public static void dispose() {
        dotT.dispose();
        logoTexture.dispose();
        ballT.dispose();
        arrowCapT.dispose();
        arrowBotT.dispose();
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static void setButtonsClicked(int val) {
        prefs.putInteger("buttonsClicked", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void addGamesPlayed() {
        prefs.putInteger("games", prefs.getInteger("games") + 1);
        prefs.flush();
    }

    public static int getGamesPlayed() {
        return prefs.getInteger("games");
    }

    public static void setAds(boolean removeAdsVersion) {
        prefs = Gdx.app.getPreferences(Configuration.GAME_NAME);
        prefs.putBoolean("ads", removeAdsVersion);
        prefs.flush();
    }

    public static boolean getAds() {
        Gdx.app.log("ADS", prefs.getBoolean("ads") + "");
        return prefs.getBoolean("ads", false);
    }


    public static int getCoinNumber() {
        return prefs.getInteger("bonus");
    }

    public static void addCoinNumber(int number) {
        prefs.putInteger("bonus", prefs.getInteger("bonus") + number);
        prefs.flush();
    }

    public static void prefs() {
        prefs = Gdx.app.getPreferences(Configuration.GAME_NAME);
    }

    public static void setVolume(boolean val) {
        prefs.putBoolean("volume", val);
        prefs.flush();
    }

    public static boolean getVolume() {
        return prefs.getBoolean("volume");
    }


    public static Texture getAssetTexture(String fileName) {
        return Assets.manager.get(fileName, Texture.class);
    }
}
