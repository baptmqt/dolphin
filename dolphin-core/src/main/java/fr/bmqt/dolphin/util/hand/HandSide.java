package fr.bmqt.dolphin.util.hand;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public enum HandSide {

    LEFT,
    RIGHT;

    public HandSide opposite() {
        return this == LEFT ? RIGHT : LEFT;
    }

}
