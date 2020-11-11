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
public class SChangeGameStatePacket implements Packet<INetHandlerPlayClient> {

    protected int state;
    protected float value;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        state = buf.readUnsignedByte();
        value = buf.readFloat();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(state);
        buf.writeFloat(value);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleChangeGameState(this);
    }
}
