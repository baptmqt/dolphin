package fr.bmqt.dolphin.network.packets.handshake.client;

import fr.bmqt.dolphin.network.packets.ConnectionState;
import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.handshake.INetHandlerHandshakeServer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * https://wiki.vg/Protocol#Handshake
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CHandshakePacket implements Packet<INetHandlerHandshakeServer> { // ID : 0x00

    protected int protocolVersion;
    protected String host;
    protected int port;
    protected ConnectionState requestedState;

    public void readPacketData(PacketBuffer buf) throws IOException {
        protocolVersion = buf.readVarIntFromBuffer();
        host = buf.readStringFromBuffer(255);
        port = buf.readUnsignedShort();
        requestedState = ConnectionState.byId(buf.readVarIntFromBuffer());
    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(protocolVersion);
        buf.writeString(host);
        buf.writeShort(port);
        buf.writeVarIntToBuffer(requestedState.getId());
    }

    @Override
    public void processPacket(INetHandlerHandshakeServer handler) {
        handler.processHandshake(this);
    }
}
