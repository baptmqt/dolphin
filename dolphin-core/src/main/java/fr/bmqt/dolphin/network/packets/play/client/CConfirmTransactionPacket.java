package fr.bmqt.dolphin.network.packets.play.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayServer;
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
public class CConfirmTransactionPacket implements Packet<INetHandlerPlayServer> {

    protected int windowId;
    protected short uid;
    protected boolean accepted;


    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        windowId = buf.readByte();
        uid = buf.readShort();
        accepted = buf.readByte() != 0;
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(windowId);
        buf.writeShort(uid);
        buf.writeByte(accepted ? 1 : 0);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processConfirmTransaction(this);
    }
}
