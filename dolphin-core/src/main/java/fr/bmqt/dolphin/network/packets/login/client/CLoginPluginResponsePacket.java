package fr.bmqt.dolphin.network.packets.login.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.login.INetHandlerLoginServer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 12/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Login_Plugin_Response
 *
 * MCP      : N/C
 * PacketID : 0x02
 * State    : Login
 * Bound to : Server
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CLoginPluginResponsePacket implements Packet<INetHandlerLoginServer> {

    protected int messageId;
    protected boolean successful;
    protected byte[] data; // optional

    public void readPacketData(PacketBuffer buf) throws IOException {
        messageId = buf.readVarIntFromBuffer();
        successful = buf.readBoolean();
        //todo : read data when is set;
    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(messageId);
        buf.writeBoolean(successful);
        if (data != null)
            buf.writeByteArray(data);
    }

    public void processPacket(INetHandlerLoginServer handler) {
        handler.processLoginPluginResponse(this);
    }
}
