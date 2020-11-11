package fr.bmqt.dolphin.network.packets;

import java.io.IOException;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public interface Packet<T extends INetHandler> {

    /**
     * Reads the raw packet data from the data stream.
     * @param buf the raw packet.
     * @throws IOException .
     */
    void readPacketData(PacketBuffer buf) throws IOException;

    /**
     * Writes the raw packet data to the data stream.
     * @param buf the raw packet.
     * @throws IOException .
     */
    void writePacketData(PacketBuffer buf) throws IOException;

    /**
     * Passes this Packet on to the NetHandler for processing.
     * @param handler the NetHandler.
     */
    void processPacket(T handler);


}
