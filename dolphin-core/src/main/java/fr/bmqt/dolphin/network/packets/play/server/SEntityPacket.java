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

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SEntityPacket implements Packet<INetHandlerPlayClient> {

    protected int entityId;
    protected int posX;
    protected int posY;
    protected int posZ;
    protected byte yaw;
    protected byte pitch;
    protected boolean onGround;
    protected boolean rotating;

    public SEntityPacket(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityId = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(entityId);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityMovement(this);
    }

    public static class S15EntityRelMovePacket extends SEntityPacket {
        public S15EntityRelMovePacket() {
        }

        public S15EntityRelMovePacket(int entityIdIn, long xIn, long yIn, long zIn, boolean onGroundIn) {
            super(entityIdIn);
            this.posX = (int) xIn;
            this.posY = (int) yIn;
            this.posZ = (int) zIn;
            this.onGround = onGroundIn;
        }

        public void readPacketData(PacketBuffer buf) throws IOException {
            super.readPacketData(buf);
            this.posX = buf.readShort();
            this.posY = buf.readShort();
            this.posZ = buf.readShort();
            this.onGround = buf.readBoolean();
        }

        public void writePacketData(PacketBuffer buf) throws IOException {
            super.writePacketData(buf);
            buf.writeShort(this.posX);
            buf.writeShort(this.posY);
            buf.writeShort(this.posZ);
            buf.writeBoolean(this.onGround);
        }
    }

    public static class S16EntityLookPacket extends SEntityPacket {
        public S16EntityLookPacket() {
            this.rotating = true;
        }

        public S16EntityLookPacket(int entityIdIn, byte yawIn, byte pitchIn, boolean onGroundIn) {
            super(entityIdIn);
            this.yaw = yawIn;
            this.pitch = pitchIn;
            this.rotating = true;
            this.onGround = onGroundIn;
        }

        public void readPacketData(PacketBuffer buf) throws IOException {
            super.readPacketData(buf);
            this.yaw = buf.readByte();
            this.pitch = buf.readByte();
            this.onGround = buf.readBoolean();
        }

        public void writePacketData(PacketBuffer buf) throws IOException {
            super.writePacketData(buf);
            buf.writeByte(this.yaw);
            buf.writeByte(this.pitch);
            buf.writeBoolean(this.onGround);
        }
    }

    public static class S17EntityLookMovePacket extends SEntityPacket {
        public S17EntityLookMovePacket() {
            this.rotating = true;
        }

        public S17EntityLookMovePacket(int entityIdIn, long xIn, long yIn, long zIn, byte yawIn, byte pitchIn, boolean onGroundIn) {
            super(entityIdIn);
            this.posX = (int) xIn;
            this.posY = (int) yIn;
            this.posZ = (int) zIn;
            this.yaw = yawIn;
            this.pitch = pitchIn;
            this.onGround = onGroundIn;
            this.rotating = true;
        }

        public void readPacketData(PacketBuffer buf) throws IOException {
            super.readPacketData(buf);
            this.posX = buf.readShort();
            this.posY = buf.readShort();
            this.posZ = buf.readShort();
            this.yaw = buf.readByte();
            this.pitch = buf.readByte();
            this.onGround = buf.readBoolean();
        }

        public void writePacketData(PacketBuffer buf) throws IOException {
            super.writePacketData(buf);
            buf.writeShort(this.posX);
            buf.writeShort(this.posY);
            buf.writeShort(this.posZ);
            buf.writeByte(this.yaw);
            buf.writeByte(this.pitch);
            buf.writeBoolean(this.onGround);
        }
    }
}
