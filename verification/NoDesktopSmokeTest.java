import com.direwolf20.laserio.util.Color;

public final class NoDesktopSmokeTest {
    public static void main(String[] args) {
        Color initial = new Color(1f, 0f, 0f, 0.33f);
        Color loaded = new Color(initial.getRGB(), true);
        if (loaded.getRGB() != 0x54ff0000 || !loaded.equals(initial)) {
            throw new AssertionError("Default laser color/save round trip changed");
        }
        Color packetColor = new Color(0x80123456, true);
        if (packetColor.getAlpha() != 128 || packetColor.getRed() != 18
                || packetColor.getGreen() != 52 || packetColor.getBlue() != 86) {
            throw new AssertionError("Packet ARGB decoding changed");
        }
        System.out.println("Passed color initialization/save/packet checks with java.desktop unavailable.");
    }
}
