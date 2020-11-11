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
public class CCustomPayloadPacket implements Packet<INetHandlerPlayServer> {

    protected String channel;
    protected PacketBuffer data;

    public CCustomPayloadPacket(String channel, PacketBuffer data) {
        this.channel = channel;
        this.data = data;

        if (data.writerIndex() > 32767)
            throw new IllegalArgumentException("Payload may not be larger than 32767 bytes");
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        channel = buf.readStringFromBuffer(20);
        int readableBytes = buf.readableBytes();

        if (readableBytes >= 0 && readableBytes <= 32767)
            data = new PacketBuffer(buf.readBytes(readableBytes));
        else
            throw new IOException("Payload may not be larger than 32767 bytes");
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(channel);
        buf.writeBytes(data);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processCustomPayload(this);

        if (this.data != null)
            data.release();
    }
}
