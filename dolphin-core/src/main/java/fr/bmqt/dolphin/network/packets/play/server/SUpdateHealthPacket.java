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
public class SUpdateHealthPacket implements Packet<INetHandlerPlayClient> {

    protected float health;
    protected int foodLevel;
    protected float saturationLevel;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        health = buf.readFloat();
        foodLevel = buf.readVarIntFromBuffer();
        saturationLevel = buf.readFloat();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeFloat(health);
        buf.writeVarIntToBuffer(foodLevel);
        buf.writeFloat(saturationLevel);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleUpdateHealth(this);
    }
}
