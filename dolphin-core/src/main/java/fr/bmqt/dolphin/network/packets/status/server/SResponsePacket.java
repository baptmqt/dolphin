package fr.bmqt.dolphin.network.packets.status.server;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.status.INetHandlerStatusClient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Pong
 *
 * MCP      : SPacketServerInfo
 * PacketID : 0x00
 * State    : Status
 * Bound to : Client
 */
@AllArgsConstructor
@Getter
public class SResponsePacket implements Packet<INetHandlerStatusClient> { // ID : 0x00

    /**
     * todo : Format ServerStatusResponse :
     * https://wiki.vg/Server_List_Ping#Response
     */
    protected String jsonResponse;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        jsonResponse = buf.readStringFromBuffer(32767);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(jsonResponse);
    }

    @Override
    public void processPacket(INetHandlerStatusClient handler) {
        handler.handleResponse(this);
    }
}
