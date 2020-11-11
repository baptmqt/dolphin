package fr.bmqt.dolphin.network.packets;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@AllArgsConstructor
public enum ConnectionState {

    HANDSHAKING(-1),
    STATUS(0),
    LOGIN(1),
    PLAY(0);

    protected int id;

    /**
     * @param id state id
     * @return the state
     */
    public static ConnectionState byId(int id) {
        for (ConnectionState state : values())
            if (state.id == id)
                return state;
        return null;
    }

}
