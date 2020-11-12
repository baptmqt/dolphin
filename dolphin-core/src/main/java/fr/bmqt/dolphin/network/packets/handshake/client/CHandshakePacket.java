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
package fr.bmqt.dolphin.network.packets.handshake.client;

import fr.bmqt.dolphin.network.packets.ConnectionState;
import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.handshake.INetHandlerHandshakeServer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Handshake
 *
 * MCP      : C00Handshake
 * PacketID : 0x00
 * State    : Handshaking
 * Bound to : Server
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CHandshakePacket implements Packet<INetHandlerHandshakeServer> { // ID : 0x00

    protected int protocolVersion;
    protected String host;
    protected int port;
    protected ConnectionState requestedState;

    public void readPacketData(PacketBuffer buf) throws IOException {
        protocolVersion = buf.readVarIntFromBuffer();
        host = buf.readStringFromBuffer(255);
        port = buf.readUnsignedShort();
        requestedState = ConnectionState.getById(buf.readVarIntFromBuffer());
    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(protocolVersion);
        buf.writeString(host);
        buf.writeShort(port);
        buf.writeVarIntToBuffer(requestedState.getId());
    }

    @Override
    public void processPacket(INetHandlerHandshakeServer handler) {
        handler.processHandshake(this);
    }
}
