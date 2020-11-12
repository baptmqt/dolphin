package fr.bmqt.dolphin.network.packets.status;

import fr.bmqt.dolphin.network.packets.INetHandler;
import fr.bmqt.dolphin.network.packets.status.client.CPingPacket;
import fr.bmqt.dolphin.network.packets.status.client.CRequestPacket;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public interface INetHandlerStatusServer extends INetHandler {

    void processPing(CPingPacket cPingPacket);

    void processServerQuery(CRequestPacket cRequestPacket);
}
