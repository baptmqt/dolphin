package fr.bmqt.dolphin.network.packets.status.server;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.status.INetHandlerStatusClient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Pong
 *
 * MCP      : SPacketPong
 * PacketID : 0x01
 * State    : Status
 * Bound to : Client
 */
@AllArgsConstructor
@Getter
public class SPongPacket implements Packet<INetHandlerStatusClient> { // ID : 0x01

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
    public void processPacket(INetHandlerStatusClient handler) {
        handler.handlePong(this);
    }
}
