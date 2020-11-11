package fr.bmqt.dolphin.network.packets.play.server;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayClient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@NoArgsConstructor
public class SEntityVelocityPacket implements Packet<INetHandlerPlayClient> {

    protected int entityID;
    protected int motionX;
    protected int motionY;
    protected int motionZ;

    public SEntityVelocityPacket(int entityIdIn, double motionXIn, double motionYIn, double motionZIn) {
        this.entityID = entityIdIn;
        double d0 = 3.9D;
        if (motionXIn < -3.9D)
            motionXIn = -3.9D;
        if (motionYIn < -3.9D)
            motionYIn = -3.9D;
        if (motionZIn < -3.9D)
            motionZIn = -3.9D;
        if (motionXIn > 3.9D)
            motionXIn = 3.9D;
        if (motionYIn > 3.9D)
            motionYIn = 3.9D;
        if (motionZIn > 3.9D)
            motionZIn = 3.9D;

        this.motionX = (int) (motionXIn * 8000.0D);
        this.motionY = (int) (motionYIn * 8000.0D);
        this.motionZ = (int) (motionZIn * 8000.0D);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityID = buf.readVarIntFromBuffer();
        motionX = buf.readShort();
        motionY = buf.readShort();
        motionZ = buf.readShort();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(entityID);
        buf.writeShort(motionX);
        buf.writeShort(motionY);
        buf.writeShort(motionZ);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityVelocity(this);
    }
}
