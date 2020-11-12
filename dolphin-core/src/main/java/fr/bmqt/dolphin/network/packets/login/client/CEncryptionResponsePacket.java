/*
 * MIT License
 *
 * Copyright (c) 2020.  Baptiste MAQUET
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
