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
public class SWindowsPropertyPacket implements Packet<INetHandlerPlayClient> {

    private int windowId;
    private int property;
    private int value;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        windowId = buf.readUnsignedByte();
        property = buf.readShort();
        value = buf.readShort();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(windowId);
        buf.writeShort(property);
        buf.writeShort(value);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleWindowProperty(this);
    }
}
