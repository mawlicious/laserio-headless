package com.direwolf20.laserio.common.network;

/** Requires this patched release on both endpoints during Forge's handshake. */
public final class NetworkCompatibility {
    public static final String PROTOCOL_VERSION = "laserio-headless-1.6.9";

    private NetworkCompatibility() {}

    public static boolean accepts(String remoteProtocol) {
        return PROTOCOL_VERSION.equals(remoteProtocol);
    }
}
