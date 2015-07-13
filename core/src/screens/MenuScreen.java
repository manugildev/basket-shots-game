package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import MainGame.ActionResolver;
import MainGame.BasketballGame;
import gameworld.GameRenderer;
import helpers.InputHandler;
import menuworld.MenuWorld;

public class MenuScreen implements Screen {

    private static MenuWorld world;
    private GameRenderer renderer;
    public static float sW = Gdx.graphics.getWidth();
    public static float sH = Gdx.graphics.getHeight();
    public static float gameHeight = 1080;
    public static float gameWidth = sW / (sH / gameHeight);

    public MenuScreen(BasketballGame game, ActionResolver actionresolver) {
        Gdx.app.log("GameWidth " + gameWidth, "GameHeight " + gameHeight);
        world = new MenuWorld(game, gameWidth, gameHeight);
        Gdx.input.setInputProcessor(new InputHandler(world, sW / gameWidth, sH
                / gameHeight));
        renderer = new GameRenderer(world);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }

    @Override
    public void show() {

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
