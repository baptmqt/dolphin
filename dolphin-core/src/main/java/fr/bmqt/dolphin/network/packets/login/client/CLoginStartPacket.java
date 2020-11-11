package fr.bmqt.dolphin.network.packets.login.client;

import com.mojang.authlib.GameProfile;
import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.login.INetHandlerLoginServer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CLoginStartPacket implements Packet<INetHandlerLoginServer>{ // ID : 0x01

    protected GameProfile profile;

    public void readPacketData(PacketBuffer buf) throws IOException {
        profile = new GameProfile(null, buf.readStringFromBuffer(16));
    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(profile.getName());
    }

    public void processPacket(INetHandlerLoginServer handler) {
        handler.processLoginStart(this);
    }
}
