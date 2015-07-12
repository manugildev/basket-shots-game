package MainGame;

import com.badlogic.gdx.Game;

import aurelienribon.tweenengine.Tween;
import helpers.AssetLoader;
import screens.GameScreen;

public class BasketballGame extends Game {

	private ActionResolver actionresolver;

	public BasketballGame(ActionResolver actionresolver) {
		this.actionresolver = actionresolver;
	}

	@Override
	public void create() {
		AssetLoader.load1();
		Tween.setCombinedAttributesLimit(10);
		setScreen(new GameScreen(this, actionresolver));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}


}
