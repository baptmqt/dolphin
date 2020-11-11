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
public class SDestroyEntitiesPacket implements Packet<INetHandlerPlayClient> {

    protected int[] entityIDs;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityIDs = new int[buf.readVarIntFromBuffer()];
        for (int i = 0; i < entityIDs.length; ++i)
            entityIDs[i] = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(entityIDs.length);
        for (int i : entityIDs)
            buf.writeVarIntToBuffer(i);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleDestroyEntities(this);
    }
}
