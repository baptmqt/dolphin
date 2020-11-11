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
public class STabCompletePacket implements Packet<INetHandlerPlayClient> {

    protected String[] matches;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        matches = new String[buf.readVarIntFromBuffer()];
        for (int i = 0; i < matches.length; ++i)
            this.matches[i] = buf.readStringFromBuffer(32767);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(matches.length);
        for (String match : matches)
            buf.writeString(match);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleTabComplete(this);
    }
}
