package fr.bmqt.dolphin.network.packets.login.server;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.login.INetHandlerLoginClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 12/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Login_Plugin_Request
 * <p>
 * MCP      : N/C
 * PacketID : 0x04
 * State    : Login
 * Bound to : Client
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SLoginPluginResponsePacket implements Packet<INetHandlerLoginClient> {

    //todo, todo, need Identifier ??

    //protected int messageId;
    //protected String? channel;
    // byte[] data; // optional

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
       // messageId = buf.readVarIntFromBuffer();

    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
       // buf.writeVarIntToBuffer(messageId);
    }

    @Override
    public void processPacket(INetHandlerLoginClient handler) {
        handler.handleLoginPluginResponse(this);
    }
}
