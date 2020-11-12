package fr.bmqt.dolphin.network.packets.login.server;

import com.mojang.authlib.GameProfile;
import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.login.INetHandlerLoginClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 * @docs https://wiki.vg/Protocol#Login_Success
 *
 * MCP      : SPacketLoginSuccess
 * PacketID : 0x02
 * State    : Login
 * Bound to : Client
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SLoginSuccessPacket implements Packet<INetHandlerLoginClient> {

    protected GameProfile profile;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        UUID uuid = UUID.fromString(buf.readStringFromBuffer(36));
        String name = buf.readStringFromBuffer(16);
        profile = new GameProfile(uuid, name);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        UUID uuid = profile.getId();
        buf.writeString(uuid == null ? "" : uuid.toString());
        buf.writeString(profile.getName());
    }

    @Override
    public void processPacket(INetHandlerLoginClient handler) {
        handler.handleLoginSuccess(this);
    }
}
