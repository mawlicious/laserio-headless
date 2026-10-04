import com.direwolf20.laserio.common.network.NetworkCompatibility;

public final class NetworkCompatibilityTest {
    public static void main(String[] args) {
        if (!NetworkCompatibility.accepts(NetworkCompatibility.PROTOCOL_VERSION)) {
            throw new AssertionError("Matching patched endpoints must connect");
        }
        // Upstream 1.6.8 and 1.6.8-headless.1 both advertise protocol 2.
        String[] incompatible = {"2", "1", "1.6.8", "1.6.8-headless.1",
                "1.6.9", "ABSENT", "ACCEPTVANILLA", "laserio-headless-unknown", null};
        for (String protocol : incompatible) {
            if (NetworkCompatibility.accepts(protocol)) {
                throw new AssertionError("Must reject incompatible protocol: " + protocol);
            }
        }
        System.out.println("Passed handshake compatibility: matching release accepted; legacy/missing/unknown protocols rejected.");
    }
}
