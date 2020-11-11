package fr.bmqt.dolphin.network.packets.status.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.status.INetHandlerStatusServer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@AllArgsConstructor
public class CServerQueryPacket implements Packet<INetHandlerStatusServer> { // ID : 0x00


    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {

    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {

    }

    @Override
    public void processPacket(INetHandlerStatusServer handler) {
        handler.processServerQuery(this);
    }
}
