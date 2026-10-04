# LaserIO Headless

Unofficial **LaserIO 1.6.8** fork for **Minecraft 1.20.1 / Forge**. It replaces
`java.awt.Color` throughout the mod with a small packed sRGB color class, avoiding
native desktop initialization when laser block entities load on a dedicated server.

This addresses crashes such as:

```text
java.lang.UnsatisfiedLinkError: libawt_xawt.so:
libXrender.so.1: cannot open shared object file
```

Based on [Direwolf20's LaserIO v1.6.8 source](https://github.com/Direwolf20-MC/LaserIO/tree/8da801d).
Original authorship and the MIT license are preserved. This is an unofficial fix,
not an upstream release. The patch applies to LaserIO; other mods may still use AWT.

## Installation

1. Stop the server and back up your world; try a copy first.
2. Download `laserio-1.6.8-headless.1.jar` from this repository's releases.
3. Replace the existing LaserIO jar in both server and client `mods` folders.
   Keep only one LaserIO version installed in each folder.
4. Start the server and check existing nodes, connections, cards and their colors.

Saved ARGB color integers, block IDs and color packet values remain unchanged.
The complete original modpack/world has not been tested locally.

## Build and verification

Install Java 17 and set `JAVA_HOME` to its installation directory, then run:

```sh
sh gradlew build --console=plain
```

The installable, reobfuscated Forge jar is in `build/libs/`. The build also runs:

- 400,005 comparisons against AWT for packed color values, component values,
  float rounding, constants and saved ARGB round trips.
- Color initialization/save/packet checks with Java's desktop module unavailable.
- A scan requiring zero AWT/Swing references in all packaged classes.

GitHub Actions runs the same checks on Linux and uploads the built jar as an artifact.
See [HEADLESS-PATCH.md](HEADLESS-PATCH.md) for the patch scope.
