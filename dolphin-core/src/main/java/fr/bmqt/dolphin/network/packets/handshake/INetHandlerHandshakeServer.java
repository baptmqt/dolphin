package fr.bmqt.dolphin.network.packets.handshake;

import fr.bmqt.dolphin.network.packets.INetHandler;
import fr.bmqt.dolphin.network.packets.handshake.client.CHandshakePacket;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public interface INetHandlerHandshakeServer extends INetHandler {

    void processHandshake(CHandshakePacket packet);

}
