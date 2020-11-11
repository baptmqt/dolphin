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
public class CHeldItemChangePacket implements Packet<INetHandlerPlayServer> {

    protected int slotId;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        slotId = buf.readShort();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeShort(slotId);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processHeldItemChange(this);
    }
}
