package us.ferox.client.api.util.render;

/**
 * @author bon
 * @since 11/18/20
 */

public class GuiUtil {
    public static int mX;
    public static int mY;
    public static int keydown;
    public static boolean ldown;
    public static boolean lheld;
    public static boolean rdown;

    public static boolean mouseOver(int minX, int minY, int maxX, int maxY) {
        return mX >= minX && mY >= minY && mX <= maxX && mY <= maxY;
    }

    public static void updateMousePos(int mouseX, int mouseY) {
        mX = mouseX;
        mY = mouseY;
        ldown = false;
        rdown = false;
        keydown = -1;
    }

    public static void updateLeftClick() {
        ldown = true;
        lheld = true;
    }

    public static void updateRightClick() {
        rdown = true;
    }

    public static void updateMouseState() {
        lheld = false;
    }

    public static void updateKeyState(int key) {
        keydown = key;
    }
}
