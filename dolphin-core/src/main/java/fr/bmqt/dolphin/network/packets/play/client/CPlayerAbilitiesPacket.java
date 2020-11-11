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
public class CPlayerAbilitiesPacket implements Packet<INetHandlerPlayServer> { // todo : constructor with PlayerCapabilities ?

    protected boolean invulnerable;
    protected boolean flying;
    protected boolean allowFlying;
    protected boolean creativeMode;
    protected float flySpeed;
    protected float walkSpeed;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        byte b = buf.readByte();
        invulnerable = (b & 1) > 0;
        flying = (b & 2) > 0;
        allowFlying = (b & 4) > 0;
        creativeMode = (b & 8) > 0;
        flySpeed = buf.readFloat();
        walkSpeed = buf.readFloat();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        byte b = 0;
        if (invulnerable)
            b = (byte) (b | 1);
        if (flying)
            b = (byte) (b | 2);
        if (allowFlying)
            b = (byte) (b | 4);
        if (creativeMode)
            b = (byte) (b | 8);
        buf.writeByte(b);
        buf.writeFloat(flySpeed);
        buf.writeFloat(walkSpeed);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processPlayerAbilities(this);
    }
}
