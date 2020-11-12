package fr.bmqt.dolphin.network.packets.login;

import fr.bmqt.dolphin.network.packets.INetHandler;
import fr.bmqt.dolphin.network.packets.login.client.CEncryptionResponsePacket;
import fr.bmqt.dolphin.network.packets.login.client.CLoginPluginResponsePacket;
import fr.bmqt.dolphin.network.packets.login.client.CLoginStartPacket;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public interface INetHandlerLoginServer extends INetHandler {

    void processEncryptionResponse(CEncryptionResponsePacket packet);

    void processLoginStart(CLoginStartPacket packet);

    void processLoginPluginResponse(CLoginPluginResponsePacket cLoginPluginResponse);
}
