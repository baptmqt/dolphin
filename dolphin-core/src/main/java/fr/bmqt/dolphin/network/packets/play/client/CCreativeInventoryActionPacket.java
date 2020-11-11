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
public class CCreativeInventoryActionPacket implements Packet<INetHandlerPlayServer> { // stack.copy() in constructor ?

    protected int slotId;
    //protected ItemStack stack; //todo : need to implement ItemStack.class

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        slotId = buf.readShort();
        //stack = buf.readItemStackFromBuffer(); // todo : need to implement readItemStackFromBuffer
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        //buf.writeShort(slotId);
        //buf.writeItemStackToBuffer(stack); // todo : need to implement writeItemStackToBuffer
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processCreativeInventoryAction(this);
    }
}
