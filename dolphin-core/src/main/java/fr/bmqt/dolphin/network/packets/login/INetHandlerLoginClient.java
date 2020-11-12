package fr.bmqt.dolphin.network.packets.login;

import fr.bmqt.dolphin.network.packets.INetHandler;
import fr.bmqt.dolphin.network.packets.login.server.*;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public interface INetHandlerLoginClient extends INetHandler {

    void handleDisconnect(SDisconnectPacket packet);

    void handleSetCompression(SSetCompressionPacket packet);

    void handleEncryptionRequest(SEncryptionRequestPacket packet);

    void handleLoginSuccess(SLoginSuccessPacket sLoginSuccessPacket);

    void handleLoginPluginResponse(SLoginPluginResponsePacket sLoginPluginResponsePacket);
}
