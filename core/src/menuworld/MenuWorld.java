package menuworld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import MainGame.BasketballGame;
import gameobjects.GameObject;
import gameworld.GameWorld;
import helpers.AssetLoader;
import helpers.FlatColors;

public class MenuWorld extends GameWorld {

    private GameObject background;

    public MenuWorld(BasketballGame game, float gameWidth, float gameHeight) {
        super(game, gameWidth, gameHeight);
        background = new GameObject(this, 0, 0, gameWidth, gameHeight, AssetLoader.square,
                FlatColors.BLUE,
                GameObject.Shape.RECTANGLE);
    }

    public void update(float delta) {
        background.update(delta);
    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        background.render(batch, shapeRenderer);
    }
}
