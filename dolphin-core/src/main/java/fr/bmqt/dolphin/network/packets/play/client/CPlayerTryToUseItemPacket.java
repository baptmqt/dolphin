package fr.bmqt.dolphin.network.packets.play.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayServer;
import fr.bmqt.dolphin.util.hand.Hand;
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
public class CPlayerTryToUseItemPacket implements Packet<INetHandlerPlayServer> {

    protected Hand hand;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        hand = buf.readEnumValue(Hand.class);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeEnumValue(hand);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processPlayerBlockPlacement(this);
    }
}
