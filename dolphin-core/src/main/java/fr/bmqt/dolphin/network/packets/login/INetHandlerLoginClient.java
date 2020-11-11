package fr.bmqt.dolphin.network.packets.login;

import fr.bmqt.dolphin.network.packets.INetHandler;
import fr.bmqt.dolphin.network.packets.login.server.SDisconnectPacket;
import fr.bmqt.dolphin.network.packets.login.server.SEnableCompressionPacket;
import fr.bmqt.dolphin.network.packets.login.server.SEncryptionRequestPacket;
import fr.bmqt.dolphin.network.packets.login.server.SLoginSuccessPacket;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public interface INetHandlerLoginClient extends INetHandler {


    void handleDisconnect(SDisconnectPacket packet);

    void handleEnableCompression(SEnableCompressionPacket packet);

    void handleEncryptionRequest(SEncryptionRequestPacket packet);

    void handleLoginSuccess(SLoginSuccessPacket sLoginSuccessPacket);
}
