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
public class SResourcePackSendPacket implements Packet<INetHandlerPlayClient> {

    protected String url;
    protected String hash; //todo prevent hash > 40

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        url = buf.readStringFromBuffer(32767);
        hash = buf.readStringFromBuffer(40);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(url);
        buf.writeString(hash);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleResourcePack(this);
    }
}
