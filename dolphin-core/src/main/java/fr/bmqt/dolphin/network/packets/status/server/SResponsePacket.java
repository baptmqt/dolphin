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
package fr.bmqt.dolphin.network.packets.status.server;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.status.INetHandlerStatusClient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Pong
 *
 * MCP      : SPacketServerInfo
 * PacketID : 0x00
 * State    : Status
 * Bound to : Client
 */
@AllArgsConstructor
@Getter
public class SResponsePacket implements Packet<INetHandlerStatusClient> { // ID : 0x00

    /**
     * todo : Format ServerStatusResponse :
     * https://wiki.vg/Server_List_Ping#Response
     */
    protected String jsonResponse;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        jsonResponse = buf.readStringFromBuffer(32767);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(jsonResponse);
    }

    @Override
    public void processPacket(INetHandlerStatusClient handler) {
        handler.handleResponse(this);
    }
}
