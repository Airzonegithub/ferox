package us.ferox.client.api.hud;

import net.minecraft.client.gui.ScaledResolution;
import us.ferox.client.api.setting.Setting;
import us.ferox.client.api.traits.Minecraft;
import us.ferox.client.api.traits.Render;
import us.ferox.client.api.util.font.FontUtil;

import java.util.ArrayList;

public abstract class HudComponent implements Minecraft, Render {
    private final String name = getAnnotation().name();;
    private int posX = 2;
    private int posY = 2;
    private int dragX;
    private int dragY;
    private int width;
    private int height;
    protected boolean visible = false;
    private boolean dragging;
    private ArrayList<Setting> settings = new ArrayList<>();

    private ComponentInfo getAnnotation() {
        if (getClass().isAnnotationPresent(ComponentInfo.class)) {
            return getClass().getAnnotation(ComponentInfo.class);
        }
        throw new IllegalStateException("Annotation 'ComponentInfo' not found!");
    }

    public abstract void render();

    protected final void drawString(String text) {
        FontUtil.drawText(text, posX, posY, HudManager.getColor());
    }

    public final String getName() {
        return name;
    }

    public final int getPosX() {
        return posX;
    }

    public final void setPosX(int posX) {
        this.posX = posX;
    }

    public final int getPosY() {
        return posY;
    }

    public final void setPosY(int posY) {
        this.posY = posY;
    }

    public final boolean isVisible() {
        return visible;
    }

    public final void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDragX() {
        return dragX;
    }

    public void setDragX(int dragX) {
        this.dragX = dragX;
    }

    public int getDragY() {
        return dragY;
    }

    public void setDragY(int dragY) {
        this.dragY = dragY;
    }

    public boolean isMouseOnComponent(int x, int y) {
        if (x >= this.posX && x <= this.posX + this.width && y >= this.posY && y <= this.posY + this.height) {
            return true;
        } else {
            return false;
        }
    }

    public void collide() {
        ScaledResolution sr = new ScaledResolution(mc);

        if (posX <= 0) {
            setPosX(0);
        }

        if (posX >= sr.getScaledWidth() - width) {
            setPosX(sr.getScaledWidth() - width);
        }

        if (posY <= 0){
            setPosY(0);
        }

        if (getPosY() >= sr.getScaledHeight() - height) {
            setPosY(sr.getScaledHeight() - height);
        }
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (this.dragging) {
            this.setPosX(mouseX - getDragX());
            this.setPosY(mouseY - getDragY());
        }
        collide();
    }
    public ArrayList<Setting> getSettings() {
        return settings;
    }

    public Setting addSetting(Setting setting) {
        settings.add(setting);
        return setting;
    }
}
