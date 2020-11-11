package fr.bmqt.dolphin.network.packets.play.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayServer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@NoArgsConstructor
public class CChatMessagePacket implements Packet<INetHandlerPlayServer> {

    protected String message;

    public CChatMessagePacket(String message) {
        this.message = message.length() > 256 ? message.substring(0, 256) : message;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        message = buf.readStringFromBuffer(256);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(message);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processChatMessage(this);
    }
}
