package fr.bmqt.dolphin.network.packets.status;

import fr.bmqt.dolphin.network.packets.INetHandler;
import fr.bmqt.dolphin.network.packets.status.server.SPongPacket;
import fr.bmqt.dolphin.network.packets.status.server.SServerInfoPacket;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public interface INetHandlerStatusClient extends INetHandler {

    void handlePong(SPongPacket sPongPacket);

    void handleServerInfo(SServerInfoPacket sServerInfoPacket);
}
