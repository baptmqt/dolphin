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
 * https://wiki.vg/Protocol#Collect_Item
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SCollectItemPacket implements Packet<INetHandlerPlayClient> {

    private int collectedItemEntityId;
    private int collectorEntityId;
    private int pickupItemCount;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        collectedItemEntityId = buf.readVarIntFromBuffer();
        collectorEntityId = buf.readVarIntFromBuffer();
        pickupItemCount = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(collectedItemEntityId);
        buf.writeVarIntToBuffer(collectorEntityId);
        buf.writeVarIntToBuffer(pickupItemCount);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleCollectItem(this);
    }
}
