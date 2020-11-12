package fr.bmqt.dolphin.network.packets.status.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.status.INetHandlerStatusServer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Ping
 *
 * MCP      : CPacketPing
 * PacketID : 0x01
 * State    : Status
 * Bound to : Server
 */
@Getter
@AllArgsConstructor
public class CPingPacket implements Packet<INetHandlerStatusServer> { // ID : 0x01

    protected long clientTime;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        clientTime = buf.readLong();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeLong(clientTime);
    }

    @Override
    public void processPacket(INetHandlerStatusServer handler) {
        handler.processPing(this);
    }
}
