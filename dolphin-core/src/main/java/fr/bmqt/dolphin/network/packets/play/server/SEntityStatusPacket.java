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
public class SEntityStatusPacket implements Packet<INetHandlerPlayClient> {

    protected int entityId;
    protected byte logicOpcode;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityId = buf.readInt();
        logicOpcode = buf.readByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeInt(entityId);
        buf.writeByte(logicOpcode);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityStatus(this);
    }
}
