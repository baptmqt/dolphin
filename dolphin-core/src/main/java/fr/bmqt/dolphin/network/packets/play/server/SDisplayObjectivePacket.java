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
public class SDisplayObjectivePacket implements Packet<INetHandlerPlayClient> {

    protected int position;
    protected String scoreName;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        position = buf.readByte();
        scoreName = buf.readStringFromBuffer(16);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(position);
        buf.writeString(scoreName);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleDisplayObjective(this);
    }
}
