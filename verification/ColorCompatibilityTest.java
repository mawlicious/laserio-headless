import com.direwolf20.laserio.util.Color;
import java.util.Arrays;
import java.util.Random;

public final class ColorCompatibilityTest {
    private static void compare(Color actual, java.awt.Color expected) {
        if (actual.getRGB() != expected.getRGB()
                || actual.getRed() != expected.getRed()
                || actual.getGreen() != expected.getGreen()
                || actual.getBlue() != expected.getBlue()
                || actual.getAlpha() != expected.getAlpha()
                || !Arrays.equals(actual.getColorComponents(null), expected.getColorComponents(null))) {
            throw new AssertionError("Color encoding/components differ");
        }
        Color restored = new Color(actual.getRGB(), true);
        if (!restored.equals(actual) || restored.hashCode() != actual.hashCode()) {
            throw new AssertionError("Saved ARGB round trip differs");
        }
    }
    public static void main(String[] args) {
        compare(Color.WHITE, java.awt.Color.WHITE);
        compare(Color.DARK_GRAY, java.awt.Color.DARK_GRAY);
        compare(Color.BLUE, java.awt.Color.BLUE);
        compare(Color.GREEN, java.awt.Color.GREEN);
        compare(new Color(1f, 0f, 0f, 0.33f), new java.awt.Color(1f, 0f, 0f, 0.33f));
        Random random = new Random(168);
        for (int i = 0; i < 100_000; i++) {
            int packed = random.nextInt();
            compare(new Color(packed, true), new java.awt.Color(packed, true));
            compare(new Color(packed), new java.awt.Color(packed));
            int r = random.nextInt(256), g = random.nextInt(256);
            int b = random.nextInt(256), a = random.nextInt(256);
            compare(new Color(r, g, b, a), new java.awt.Color(r, g, b, a));
            float rf = random.nextFloat(), gf = random.nextFloat();
            float bf = random.nextFloat(), af = random.nextFloat();
            compare(new Color(rf, gf, bf, af), new java.awt.Color(rf, gf, bf, af));
        }
        System.out.println("Passed 400,005 color compatibility and saved ARGB checks.");
    }
}
