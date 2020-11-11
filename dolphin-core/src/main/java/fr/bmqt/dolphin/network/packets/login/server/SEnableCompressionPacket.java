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
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SEnableCompressionPacket implements Packet<INetHandlerLoginClient> {

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
        handler.handleEnableCompression(this);
    }
}
