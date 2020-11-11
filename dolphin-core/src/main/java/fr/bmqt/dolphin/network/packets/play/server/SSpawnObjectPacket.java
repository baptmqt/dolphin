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
