package fr.bmqt.dolphin.network.packets.play.server;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayClient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@NoArgsConstructor
public class SCustomPayloadPacket implements Packet<INetHandlerPlayClient> {

    protected String channel;
    protected PacketBuffer data;

    public SCustomPayloadPacket(String channel, PacketBuffer data) {
        this.channel = channel;
        this.data = data;

        if (data.writerIndex() > 1048576)
            throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        channel = buf.readStringFromBuffer(20);
        int readableBytes = buf.readableBytes();
        if (readableBytes >= 0 && readableBytes <= 1048576)
            data = new PacketBuffer(buf.readBytes(readableBytes));
        else
            throw new IOException("Payload may not be larger than 1048576 bytes");
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(channel);
        buf.writeBytes(data);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleCustomPayload(this);
    }
}
