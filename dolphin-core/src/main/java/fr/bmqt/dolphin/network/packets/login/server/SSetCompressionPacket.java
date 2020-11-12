package fr.bmqt.dolphin.network.packets.login.server;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.login.INetHandlerLoginClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Set_Compression
 * <p>
 * MCP      : SPacketEnableCompression
 * PacketID : 0x03
 * State    : Login
 * Bound to : Client
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SSetCompressionPacket implements Packet<INetHandlerLoginClient> {

    protected int compressionThreshold;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        compressionThreshold = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(compressionThreshold);
    }

    @Override
    public void processPacket(INetHandlerLoginClient handler) {
        handler.handleSetCompression(this);
    }
}
