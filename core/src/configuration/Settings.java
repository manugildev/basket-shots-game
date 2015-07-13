package configuration;

/**
 * Created by ManuGil on 08/07/15.
 */
public class Settings {

    //GAMEWORLD
    public static final float WORLD_GRAVITY = -25.8f;
    public static final float PTM = 100f; //Pixels Per Metter (Don't Touch this)
    public static final float BANNER_HEIGHT = 120;

    //BALL
    public static final float BALL_FORCE_SCALE = 0.07f;
    public static final boolean FADE_OUT_ON_FLOOR = false; //Set to false for testing
    public static final int NUM_OF_INITIAL_BALLS = 1;

    //ARROw
    public static final float ARROW_WIDTH = 100;
    public static final String ARROW_GREEN_COLOR = "55a08b";
    public static final String ARROW_RED_COLOR ="b63535" ;

    //COLLISION
    public static final short CATEGORY_BALL = 0x0001;  // 0000000000000001 in binary
    public static final short CATEGORY_FLOOR = 0x0002; // 0000000000000010 in binary
    public static final short CATEGORY_BASKET = 0x0004;
    public static final short MASK_BALL = CATEGORY_FLOOR | CATEGORY_BASKET; // or ~CATEGORY_PLAYER
    public static final short MASK_FLOOR = CATEGORY_BALL; // or ~CATEGORY_MONSTER
    public static final short MASK_BASKET = CATEGORY_BALL;
}
