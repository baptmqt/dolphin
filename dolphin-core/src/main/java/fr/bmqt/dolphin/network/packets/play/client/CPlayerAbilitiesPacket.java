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
package fr.bmqt.dolphin.network.packets.play.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayServer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CPlayerAbilitiesPacket implements Packet<INetHandlerPlayServer> { // todo : constructor with PlayerCapabilities ?

    protected boolean invulnerable;
    protected boolean flying;
    protected boolean allowFlying;
    protected boolean creativeMode;
    protected float flySpeed;
    protected float walkSpeed;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        byte b = buf.readByte();
        invulnerable = (b & 1) > 0;
        flying = (b & 2) > 0;
        allowFlying = (b & 4) > 0;
        creativeMode = (b & 8) > 0;
        flySpeed = buf.readFloat();
        walkSpeed = buf.readFloat();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        byte b = 0;
        if (invulnerable)
            b = (byte) (b | 1);
        if (flying)
            b = (byte) (b | 2);
        if (allowFlying)
            b = (byte) (b | 4);
        if (creativeMode)
            b = (byte) (b | 8);
        buf.writeByte(b);
        buf.writeFloat(flySpeed);
        buf.writeFloat(walkSpeed);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processPlayerAbilities(this);
    }
}
