package humanextended.util;

import static humanextended.HumanExpansionMod.imagePath;
import static humanextended.util.TextureLoader.getTexture;

import com.badlogic.gdx.graphics.Texture;

public class ImageManager {
    public static Texture GANGWAY_BEAM;
    public static Texture INCINERATOR_LARGE;
    public static Texture INCINERATOR_INNER;

    public static void initialize() {
        GANGWAY_BEAM = getTexture(imagePath("vfx/gangway_beam.png"));
        INCINERATOR_LARGE = getTexture(imagePath("vfx/incinerator_large.png"));
        INCINERATOR_INNER = getTexture(imagePath("vfx/incinerator_inner.png"));
    }
}
