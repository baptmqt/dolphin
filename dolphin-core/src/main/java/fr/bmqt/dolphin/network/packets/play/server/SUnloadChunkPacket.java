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
public class SUnloadChunkPacket implements Packet<INetHandlerPlayClient> {

    protected int x;
    protected int z;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        x = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeInt(x);
        buf.writeInt(z);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {

    }
}
