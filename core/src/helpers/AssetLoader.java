package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import configuration.Configuration;

/**
 * Created by ManuGil on 09/03/15.
 */
public class AssetLoader {

    public static Texture logoTexture, dotT, arrowCapT, arrowBotT, ballT;
    public static TextureRegion logo, square, dot, arrowCap, arrowBot, ball;

    private static Preferences prefs;
    public static BitmapFont font;

    public static void load1() {
        logoTexture = new Texture("logo.png");
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        logo = new TextureRegion(logoTexture, 0, 0, logoTexture.getWidth(),
                logoTexture.getHeight());
        load();
    }

    public static void load() {
        square = new TextureRegion(new Texture("square.png"));
        dotT = new Texture(Gdx.files.internal("dot.png"));
        dotT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        dot = new TextureRegion(dotT);
        arrowCapT = new Texture("arrowCap.png");
        arrowBotT = new Texture("arrowBot.png");
        arrowCap = new TextureRegion(arrowCapT);
        arrowBot = new TextureRegion(arrowBotT);
        ballT = new Texture("ball.png");
        ballT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        ball = new TextureRegion(ballT);


        //FONTS
        //LOADING FONT
        Texture tfont = new Texture(Gdx.files.internal("misc/font.png"), true);
        tfont.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);

        font = new BitmapFont(Gdx.files.internal("misc/font.fnt"), new TextureRegion(tfont),
                true);
        font.getData().setScale(1.5f, -1.5f);
        font.setColor(FlatColors.WHITE);
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


}
