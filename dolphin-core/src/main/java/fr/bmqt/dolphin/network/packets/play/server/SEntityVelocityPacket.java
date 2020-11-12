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
package fr.bmqt.dolphin.network.packets.play.server;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayClient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@NoArgsConstructor
public class SEntityVelocityPacket implements Packet<INetHandlerPlayClient> {

    protected int entityID;
    protected int motionX;
    protected int motionY;
    protected int motionZ;

    public SEntityVelocityPacket(int entityIdIn, double motionXIn, double motionYIn, double motionZIn) {
        this.entityID = entityIdIn;
        double d0 = 3.9D;
        if (motionXIn < -3.9D)
            motionXIn = -3.9D;
        if (motionYIn < -3.9D)
            motionYIn = -3.9D;
        if (motionZIn < -3.9D)
            motionZIn = -3.9D;
        if (motionXIn > 3.9D)
            motionXIn = 3.9D;
        if (motionYIn > 3.9D)
            motionYIn = 3.9D;
        if (motionZIn > 3.9D)
            motionZIn = 3.9D;

        this.motionX = (int) (motionXIn * 8000.0D);
        this.motionY = (int) (motionYIn * 8000.0D);
        this.motionZ = (int) (motionZIn * 8000.0D);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityID = buf.readVarIntFromBuffer();
        motionX = buf.readShort();
        motionY = buf.readShort();
        motionZ = buf.readShort();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(entityID);
        buf.writeShort(motionX);
        buf.writeShort(motionY);
        buf.writeShort(motionZ);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityVelocity(this);
    }
}
