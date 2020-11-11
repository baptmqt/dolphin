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
public class SConfirmTransactionPacket implements Packet<INetHandlerPlayClient> {

    protected int windowId;
    protected short actionNumber;
    protected boolean accepted;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        windowId = buf.readUnsignedByte();
        actionNumber = buf.readShort();
        accepted = buf.readBoolean();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(windowId);
        buf.writeShort(actionNumber);
        buf.writeBoolean(accepted);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleConfirmTransaction(this);
    }
}
