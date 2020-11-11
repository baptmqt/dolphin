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
public class SEntityHeadLookPacket implements Packet<INetHandlerPlayClient> {

    protected int entityId;
    protected byte yaw;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityId = buf.readVarIntFromBuffer();
        yaw = buf.readByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(entityId);
        buf.writeByte(yaw);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityHeadLook(this);
    }
}
