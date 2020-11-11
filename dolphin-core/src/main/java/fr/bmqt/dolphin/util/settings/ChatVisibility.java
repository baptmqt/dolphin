package fr.bmqt.dolphin.util.settings;

import lombok.Getter;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
public enum ChatVisibility {

    FULL(0),
    SYSTEM(1),
    HIDDEN(2);

    private final int chatVisibility;

    ChatVisibility(int id) {
        this.chatVisibility = id;
    }

}
