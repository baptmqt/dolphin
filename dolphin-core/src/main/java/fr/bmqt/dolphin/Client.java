package fr.bmqt.dolphin;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.play.client.CKeepAlivePacket;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public class Client {

    public static void main(String[] args) throws IOException {

        Packet packet = new CKeepAlivePacket();

        packet.readPacketData(null);
        packet.processPacket(null);
    }

}
