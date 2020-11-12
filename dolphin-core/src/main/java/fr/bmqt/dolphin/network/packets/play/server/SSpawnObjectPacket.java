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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SSpawnObjectPacket implements Packet<INetHandlerPlayClient> {

    protected int entityId;
    protected UUID uniqueId;
    protected double x;
    protected double y;
    protected double z;
    protected int speedX;
    protected int speedY;
    protected int speedZ;
    protected int pitch;
    protected int yaw;
    protected int type;
    protected int data;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityId = buf.readVarIntFromBuffer();
        uniqueId = buf.readUuid();
        type = buf.readByte();
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        pitch = buf.readByte();
        yaw = buf.readByte();
        data = buf.readInt();
        speedX = buf.readShort();
        speedY = buf.readShort();
        speedZ = buf.readShort();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(entityId);
        buf.writeUuid(uniqueId);
        buf.writeByte(type);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeByte(pitch);
        buf.writeByte(yaw);
        buf.writeInt(data);
        buf.writeShort(speedX);
        buf.writeShort(speedY);
        buf.writeShort(speedZ);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleSpawnObject(this);
    }
}
