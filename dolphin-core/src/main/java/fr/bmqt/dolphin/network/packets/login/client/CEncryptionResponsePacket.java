package fr.bmqt.dolphin.network.packets.login.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.login.INetHandlerLoginServer;
import fr.bmqt.dolphin.util.CryptManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.PublicKey;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Encryption_Response
 *
 * MCP      : CPacketEncryptionResponse
 * PacketID : 0x01
 * State    : Login
 * Bound to : Server
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CEncryptionResponsePacket implements Packet<INetHandlerLoginServer> { // ID : 0x01

    protected byte[] secretKeyEncrypted;
    protected byte[] verifyTokenEncrypted;

    public CEncryptionResponsePacket(SecretKey secret, PublicKey key, byte[] verifyToken) {
        this.secretKeyEncrypted = CryptManager.encryptData(key, secret.getEncoded());
        this.verifyTokenEncrypted = CryptManager.encryptData(key, verifyToken);
    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        this.secretKeyEncrypted = buf.readByteArray();
        this.verifyTokenEncrypted = buf.readByteArray();
    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByteArray(this.secretKeyEncrypted);
        buf.writeByteArray(this.verifyTokenEncrypted);
    }

    public void processPacket(INetHandlerLoginServer handler) {
        handler.processEncryptionResponse(this);
    }

}
