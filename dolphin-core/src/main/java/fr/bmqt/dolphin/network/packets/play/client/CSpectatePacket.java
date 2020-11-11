package fr.bmqt.dolphin.network.packets.play.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayServer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CSpectatePacket implements Packet<INetHandlerPlayServer> {

    protected UUID id;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        id = buf.readUuid();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeUuid(id);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.handleSpectate(this);
    }
}
