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
public class SSpawnExperienceOrbPacket implements Packet<INetHandlerPlayClient> {

    protected int entityID;
    protected double posX;
    protected double posY;
    protected double posZ;
    protected int xpValue;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityID = buf.readVarIntFromBuffer();
        posX = buf.readDouble();
        posY = buf.readDouble();
        posZ = buf.readDouble();
        xpValue = buf.readShort();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(entityID);
        buf.writeDouble(posX);
        buf.writeDouble(posY);
        buf.writeDouble(posZ);
        buf.writeShort(xpValue);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleSpawnExperienceOrb(this);
    }
}
