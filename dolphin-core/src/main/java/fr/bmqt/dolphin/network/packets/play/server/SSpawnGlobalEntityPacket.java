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
public class SSpawnGlobalEntityPacket implements Packet<INetHandlerPlayClient> {

    protected int entityId;
    protected double x;
    protected double y;
    protected double z;
    protected int type;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityId = buf.readVarIntFromBuffer();
        type = buf.readByte();
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {

        buf.writeVarIntToBuffer(entityId);
        buf.writeByte(type);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleSpawnGlobalEntity(this);
    }
}
