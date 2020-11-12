package fr.bmqt.dolphin.network.packets.login.server;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.login.INetHandlerLoginClient;
import fr.bmqt.dolphin.util.CryptManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.security.PublicKey;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Encryption_Request
 * <p>
 * MCP      : SPacketEncryptionRequest
 * PacketID : 0x01
 * State    : Login
 * Bound to : Client
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SEncryptionRequestPacket implements Packet<INetHandlerLoginClient> {

    protected String hashedServerId;
    protected PublicKey publicKey;
    protected byte[] verifyToken;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        hashedServerId = buf.readStringFromBuffer(20);
        publicKey = CryptManager.decodePublicKey(buf.readByteArray());
        verifyToken = buf.readByteArray();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(hashedServerId);
        buf.writeByteArray(publicKey.getEncoded());
        buf.writeByteArray(verifyToken);
    }

    @Override
    public void processPacket(INetHandlerLoginClient handler) {
        handler.handleEncryptionRequest(this);
    }
}
