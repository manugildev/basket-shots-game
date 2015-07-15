package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;

import configuration.Configuration;
import configuration.Settings;
import gameobjects.GameObject;
import gameworld.GameRenderer;
import gameworld.GameWorld;
import helpers.AssetLoader;
import helpers.FlatColors;

public class ScoresUI extends GameObject {
    public Text scoreText, bestText;

    public ScoresUI(GameWorld world, float x, float y, float width, float height,
                    TextureRegion texture,
                    Color color, Shape shape) {
        super(world, x, y, width, height, texture, color, shape);
        scoreText = new Text(world, x + 30, y, width, height, AssetLoader.square, Color.WHITE,
                "Score: 0", AssetLoader.font08,
                FlatColors.parseColor(Settings.TEXT_COLOR), 22, Align.left);
        bestText = new Text(world, x, y, world.gameWidth-x-30, height, AssetLoader.square, Color.WHITE,
                Configuration.BEST_TEXT + AssetLoader.getHighScore(), AssetLoader.font08,
                FlatColors.parseColor(Settings.TEXT_COLOR), 22, Align.right);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        scoreText.update(delta);
        bestText.update(delta);
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.render(batch, shapeRenderer);
        scoreText.render(batch, shapeRenderer, GameRenderer.fontShader);
        bestText.render(batch, shapeRenderer, GameRenderer.fontShader);
    }

    public void setScoreText(int score){
        scoreText.setText(Configuration.SCORE_TEXT + score);
    }
    public void setBestText(int score){
        bestText.setText(Configuration.BEST_TEXT + score);
    }
}
