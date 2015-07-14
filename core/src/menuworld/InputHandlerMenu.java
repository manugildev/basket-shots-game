package menuworld;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import configuration.Configuration;

public class InputHandlerMenu implements InputProcessor {

    private MenuWorld world;
    private float scaleFactorX;
    private float scaleFactorY;

    public InputHandlerMenu(MenuWorld world, float scaleFactorX, float scaleFactorY) {
        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
        this.world = world;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.R) {
        } else if (keycode == Input.Keys.F) {
        } else if (keycode == Input.Keys.D) {
            Configuration.DEBUG = !Configuration.DEBUG;
        } else if (keycode == Input.Keys.A) {
        } else if (keycode == Input.Keys.S) {
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        for (int i = 0; i < world.menuButtons.size(); i++) {
            world.menuButtons.get(i).isTouchDown(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        if (world.menuButtons.get(0).isTouchUp(screenX, screenY)) {
            world.goToGameScreen();
        } else if (world.menuButtons.get(1).isTouchUp(screenX, screenY)) {
            world.goToPracticeMode();
        } else if (world.menuButtons.get(2).isTouchUp(screenX, screenY)) {
            world.actionResolver.showScores();
        } else if (world.menuButtons.get(3).isTouchUp(screenX, screenY)) {
            world.actionResolver.shareGame(Configuration.SHARE_MESSAGE);
        }
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (world.gameHeight - screenY / scaleFactorY);
    }
}
