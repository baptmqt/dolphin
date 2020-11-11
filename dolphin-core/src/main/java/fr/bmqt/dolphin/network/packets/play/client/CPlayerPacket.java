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
public class CPlayerPacket implements Packet<INetHandlerPlayServer> {

    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;
    protected boolean moving;
    protected boolean rotating;

    public CPlayerPacket(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        onGround = buf.readUnsignedByte() != 0;
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(onGround ? 1 : 0);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processPlayer(this);
    }

    public double getX(double defaultValue) {
        return this.moving ? this.x : defaultValue;
    }

    public double getY(double defaultValue) {
        return this.moving ? this.y : defaultValue;
    }

    public double getZ(double defaultValue) {
        return this.moving ? this.z : defaultValue;
    }

    public float getYaw(float defaultValue) {
        return this.rotating ? this.yaw : defaultValue;
    }

    public float getPitch(float defaultValue) {
        return this.rotating ? this.pitch : defaultValue;
    }


    public static class Position extends CPlayerPacket {
        public Position() {
            this.moving = true;
        }

        public Position(double xIn, double yIn, double zIn, boolean onGroundIn) {
            this.x = xIn;
            this.y = yIn;
            this.z = zIn;
            this.onGround = onGroundIn;
            this.moving = true;
        }

        public void readPacketData(PacketBuffer buf) throws IOException {
            this.x = buf.readDouble();
            this.y = buf.readDouble();
            this.z = buf.readDouble();
            super.readPacketData(buf);
        }

        public void writePacketData(PacketBuffer buf) throws IOException {
            buf.writeDouble(this.x);
            buf.writeDouble(this.y);
            buf.writeDouble(this.z);
            super.writePacketData(buf);
        }
    }

    public static class PositionRotation extends CPlayerPacket {
        public PositionRotation() {
            this.moving = true;
            this.rotating = true;
        }

        public PositionRotation(double xIn, double yIn, double zIn, float yawIn, float pitchIn, boolean onGroundIn) {
            this.x = xIn;
            this.y = yIn;
            this.z = zIn;
            this.yaw = yawIn;
            this.pitch = pitchIn;
            this.onGround = onGroundIn;
            this.rotating = true;
            this.moving = true;
        }

        public void readPacketData(PacketBuffer buf) throws IOException {
            this.x = buf.readDouble();
            this.y = buf.readDouble();
            this.z = buf.readDouble();
            this.yaw = buf.readFloat();
            this.pitch = buf.readFloat();
            super.readPacketData(buf);
        }

        public void writePacketData(PacketBuffer buf) throws IOException {
            buf.writeDouble(this.x);
            buf.writeDouble(this.y);
            buf.writeDouble(this.z);
            buf.writeFloat(this.yaw);
            buf.writeFloat(this.pitch);
            super.writePacketData(buf);
        }
    }

    public static class Rotation extends CPlayerPacket {
        public Rotation() {
            this.rotating = true;
        }

        public Rotation(float yawIn, float pitchIn, boolean onGroundIn) {
            this.yaw = yawIn;
            this.pitch = pitchIn;
            this.onGround = onGroundIn;
            this.rotating = true;
        }

        public void readPacketData(PacketBuffer buf) throws IOException {
            this.yaw = buf.readFloat();
            this.pitch = buf.readFloat();
            super.readPacketData(buf);
        }

        public void writePacketData(PacketBuffer buf) throws IOException {
            buf.writeFloat(this.yaw);
            buf.writeFloat(this.pitch);
            super.writePacketData(buf);
        }
    }

}
