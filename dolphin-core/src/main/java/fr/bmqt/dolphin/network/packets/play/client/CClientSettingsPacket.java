package fr.bmqt.dolphin.network.packets.play.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayServer;
import fr.bmqt.dolphin.util.hand.HandSide;
import fr.bmqt.dolphin.util.settings.ChatVisibility;
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
public class CClientSettingsPacket implements Packet<INetHandlerPlayServer> {

    protected String lang;
    protected int view;
    protected ChatVisibility chatVisibility;
    protected boolean enableColors;
    protected int modelPartFlags;
    protected HandSide mainHandSide;

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        lang = buf.readStringFromBuffer(16);
        view = buf.readByte();
        chatVisibility = buf.readEnumValue(ChatVisibility.class);
        enableColors = buf.readBoolean();
        modelPartFlags = buf.readUnsignedByte();
        mainHandSide = buf.readEnumValue(HandSide.class);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        lang = buf.readStringFromBuffer(16);
        view = buf.readByte();
        chatVisibility = buf.readEnumValue(ChatVisibility.class);
        enableColors = buf.readBoolean();
        modelPartFlags = buf.readUnsignedByte();
        mainHandSide = buf.readEnumValue(HandSide.class);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processClientSettings(this);
    }
}
