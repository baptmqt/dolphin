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
