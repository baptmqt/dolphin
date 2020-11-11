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
public class CVehicleMovePacket implements Packet<INetHandlerPlayServer> {

    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        yaw = buf.readFloat();
        pitch = buf.readFloat();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeFloat(yaw);
        buf.writeFloat(pitch);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processVehicleMove(this);
    }
}
