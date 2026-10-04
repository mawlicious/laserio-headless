# LaserIO 1.6.8 headless patch

Unofficial build for Minecraft 1.20.1 / Forge, based on Direwolf20-MC/LaserIO
commit 8da801d (upstream v1.6.8). Upstream source:
https://github.com/Direwolf20-MC/LaserIO/tree/8da801d

Changes:
- Replace every java.awt.Color import with a local packed sRGB Color class.
- Preserve ARGB encoding in saved block entity NBT and color network packets.
- Preserve color components and constants used for client rendering.
- Identify the artifact as 1.6.8-headless.1 and include the upstream MIT license.
- Remove the unused Parchment build plugin; upstream uses official mappings.

Build with Java 17:
    ./gradlew build --console=plain

The installable, reobfuscated Forge jar is in build/libs/.
Replace the original LaserIO jar on the server and clients, retaining only one
LaserIO jar in each mods directory. Back up the world and try a copy first.
This patch fixes LaserIO's AWT dependency; it does not change other mods.

Verification:
- ColorCompatibilityTest compares 400,005 cases against java.awt.Color,
  including float rounding, channels, constants and ARGB save round trips.
- NoDesktopSmokeTest initializes and restores colors with only java.base enabled.
- Final jar must contain zero class references to java/awt or javax/swing.
- The user's complete modpack and existing world are not available locally.
