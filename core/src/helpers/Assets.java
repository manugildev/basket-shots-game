package helpers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static final AssetManager manager = new AssetManager();

    public static void load() {
        manager.load("arrow.png", Texture.class);
        manager.load("arrowBot.png", Texture.class);
        manager.load("arrowCap.png", Texture.class);
        manager.load("background.png", Texture.class);
        manager.load("background_menu.png", Texture.class);
        manager.load("ball.png", Texture.class);
        manager.load("buttons.png", Texture.class);
        manager.load("dot.png", Texture.class);
        manager.load("logo.png", Texture.class);
        manager.load("net.png", Texture.class);
        manager.load("pointer.png", Texture.class);
        manager.load("scoreback.png", Texture.class);
        manager.load("shadow.png", Texture.class);
        manager.load("square.png", Texture.class);
        manager.load("timerback.png", Texture.class);
        manager.load("title.png", Texture.class);
        manager.load("tutorial.png", Texture.class);
    }

    public static void dispose() {
        manager.dispose();
    }
}
