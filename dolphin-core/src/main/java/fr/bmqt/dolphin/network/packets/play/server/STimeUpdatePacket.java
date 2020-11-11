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
public class STimeUpdatePacket implements Packet<INetHandlerPlayClient> {

    protected long totalWorldTime;
    protected long worldTime;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        totalWorldTime = buf.readLong();
        worldTime = buf.readLong();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {

        buf.writeLong(totalWorldTime);
        buf.writeLong(worldTime);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleTimeUpdate(this);
    }
}
