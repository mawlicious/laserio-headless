package com.direwolf20.laserio.util;

/** Packed sRGB color without AWT or native desktop library initialization. */
public final class Color {
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color DARK_GRAY = new Color(64, 64, 64);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color GREEN = new Color(0, 255, 0);

    private final int argb;
    private final float[] components;

    public Color(int rgb) {
        this(rgb, false);
    }

    public Color(int value, boolean hasAlpha) {
        argb = hasAlpha ? value : value | 0xff000000;
        components = null;
    }

    public Color(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    public Color(int red, int green, int blue, int alpha) {
        checkChannel(red);
        checkChannel(green);
        checkChannel(blue);
        checkChannel(alpha);
        argb = (alpha << 24) | (red << 16) | (green << 8) | blue;
        components = null;
    }

    public Color(float red, float green, float blue, float alpha) {
        argb = (toChannel(alpha) << 24) | (toChannel(red) << 16)
                | (toChannel(green) << 8) | toChannel(blue);
        // Preserve original float precision, matching the former AWT behavior.
        components = new float[] {red, green, blue};
    }

    private static int toChannel(float value) {
        if (!(value >= 0 && value <= 1)) {
            throw new IllegalArgumentException("Color component must be between 0 and 1");
        }
        return (int) (value * 255 + 0.5f);
    }

    private static void checkChannel(int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Color component must be between 0 and 255");
        }
    }

    public int getRGB() { return argb; }
    public int getRed() { return (argb >>> 16) & 255; }
    public int getGreen() { return (argb >>> 8) & 255; }
    public int getBlue() { return argb & 255; }
    public int getAlpha() { return (argb >>> 24) & 255; }

    public float[] getColorComponents(float[] target) {
        float[] result = target == null ? new float[3] : target;
        result[0] = components == null ? getRed() / 255f : components[0];
        result[1] = components == null ? getGreen() / 255f : components[1];
        result[2] = components == null ? getBlue() / 255f : components[2];
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Color color && argb == color.argb;
    }

    @Override
    public int hashCode() { return argb; }
}
