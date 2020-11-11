package fr.bmqt.dolphin.network.packets.play.client;

import fr.bmqt.dolphin.inventory.ClickType;
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
public class CClickWindowPacket implements Packet<INetHandlerPlayServer> {

    protected int windowId;
    protected int slotId;
    protected int usedButton;
    protected short actionNumber;
    //protected ItemStack clickedItem; //todo : need to implement ItemStack.class
    protected ClickType clickType;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        windowId = buf.readByte();
        slotId = buf.readShort();
        usedButton = buf.readByte();
        actionNumber = buf.readShort();
        clickType = buf.readEnumValue(ClickType.class);
        // clickedItem = buf.readItemStackFromBuffer(); // todo : need to implement readItemStackFromBuffer
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(windowId);
        buf.writeShort(slotId);
        buf.writeByte(usedButton);
        buf.writeShort(actionNumber);
        buf.writeEnumValue(clickType);
        // buf.writeItemStackToBuffer(clickedItem); // todo : need to implement writeItemStackToBuffer
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processClickWindow(this);
    }
}
