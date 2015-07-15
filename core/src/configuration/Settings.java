package configuration;

import helpers.FlatColors;

/**
 * Created by ManuGil on 08/07/15.
 */
public class Settings {

    //GENERAL
    public static final String COLOR_LOADING_SCREEN_BACKGROUND = "#202020";
    public static final float LOGO_SCALE = .3f;

    //GAMEWORLD
    public static final float WORLD_GRAVITY = -25.8f;
    public static final float PTM = 100f; //Pixels Per Metter (Don't Touch this)
    public static final float BANNER_HEIGHT = 120;

    //SCORESUI
    public static final String TEXT_COLOR ="0d4273";

    //TIME
    public static final float INITIAL_TIME = 15;
    public static final float REDUCTION_EVERY_POINT = 0.5f;

    //BALL
    public static final float BALL_FORCE_SCALE = 0.07f;
    public static final boolean FADE_OUT_ON_FLOOR = true; //Set to false for testing
    public static final int NUM_OF_INITIAL_BALLS = 20;
    public static final String TOP_POINTER_COLOR = FlatColors.DARK_BLACK.toString();

    //ARROw
    public static final float ARROW_WIDTH = 100;
    public static final String ARROW_GREEN_COLOR = "55a08b";
    public static final String ARROW_RED_COLOR = "d1584b";

    //COLLISION
    public static final short CATEGORY_BALL = 0x0001;  // 0000000000000001 in binary
    public static final short CATEGORY_FLOOR = 0x0002; // 0000000000000010 in binary
    public static final short CATEGORY_BASKET = 0x0004;
    public static final short MASK_BALL = CATEGORY_FLOOR | CATEGORY_BASKET ; // or ~CATEGORY_PLAYER
    public static final short MASK_FLOOR = CATEGORY_BALL; // or ~CATEGORY_MONSTER
    public static final short MASK_BASKET = CATEGORY_BALL;
}
