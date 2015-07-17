package MainGame;

import com.badlogic.gdx.Game;

import aurelienribon.tweenengine.Tween;
import helpers.AssetLoader;
import helpers.Assets;
import screens.Boot;

public class BasketballGame extends Game {

    private ActionResolver actionresolver;

    public BasketballGame(ActionResolver actionresolver) {
        this.actionresolver = actionresolver;
    }

    @Override
    public void create() {
        Assets.load();
        Tween.setCombinedAttributesLimit(10);
        setScreen(new Boot(this, actionresolver));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
        Assets.dispose();
    }

    @Override
    public void resume() {
        super.resume();
        Assets.manager.finishLoading();
    }
}
