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
public class SSetExperiencePacket implements Packet<INetHandlerPlayClient> {

    protected float experienceBar;
    protected int totalExperience;
    protected int level;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        experienceBar = buf.readFloat();
        totalExperience = buf.readVarIntFromBuffer();
        level = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeFloat(experienceBar);
        buf.writeVarIntToBuffer(level);
        buf.writeVarIntToBuffer(totalExperience);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleSetExperience(this);
    }
}
