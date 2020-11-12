/*
 * MIT License
 *
 * Copyright (c) 2020.  Baptiste MAQUET
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.bmqt.dolphin.network.packets;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import fr.bmqt.dolphin.network.packets.handshake.client.CHandshakePacket;
import fr.bmqt.dolphin.network.packets.login.client.CEncryptionResponsePacket;
import fr.bmqt.dolphin.network.packets.login.client.CLoginPluginResponsePacket;
import fr.bmqt.dolphin.network.packets.login.client.CLoginStartPacket;
import fr.bmqt.dolphin.network.packets.login.server.*;
import fr.bmqt.dolphin.network.packets.status.client.CPingPacket;
import fr.bmqt.dolphin.network.packets.status.client.CRequestPacket;
import fr.bmqt.dolphin.network.packets.status.server.SPongPacket;
import fr.bmqt.dolphin.network.packets.status.server.SResponsePacket;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Baptiste MAQUET on 12/11/2020
 * @project dolphin-parent
 */
@Getter
@RequiredArgsConstructor
public enum ConnectionState {

    HANDSHAKING(-1) {
        {
            // Client write, Server read
            registerPacket(PacketDirection.SERVER_BOUND, 0x00, CHandshakePacket.class);
        }
    },
    STATUS(0) {
        {
            // Client write, Server read
            registerPacket(PacketDirection.SERVER_BOUND,0x00, CRequestPacket.class);
            registerPacket(PacketDirection.SERVER_BOUND,0x01, CPingPacket.class);

            // Client read, Server write
            registerPacket(PacketDirection.SERVER_BOUND,0x00, SResponsePacket.class);
            registerPacket(PacketDirection.SERVER_BOUND,0x01, SPongPacket.class);
        }
    },
    LOGIN(1) {
        {
            // Client write, Server read
            registerPacket(PacketDirection.SERVER_BOUND,0x00, CLoginStartPacket.class);
            registerPacket(PacketDirection.SERVER_BOUND,0x01, CEncryptionResponsePacket.class);
            registerPacket(PacketDirection.SERVER_BOUND,0x02, CLoginPluginResponsePacket.class);

            // Client read, Server write
            registerPacket(PacketDirection.CLIENT_BOUND,0x00, SDisconnectPacket.class);
            registerPacket(PacketDirection.CLIENT_BOUND,0x01, SEncryptionRequestPacket.class);
            registerPacket(PacketDirection.CLIENT_BOUND,0x02, SLoginSuccessPacket.class);
            registerPacket(PacketDirection.CLIENT_BOUND,0x03, SSetCompressionPacket.class);
            registerPacket(PacketDirection.CLIENT_BOUND,0x04, SLoginPluginResponsePacket.class);
        }
    },
    PLAY(2) {
        {

        }
    };

    static {
        for (ConnectionState state : values())
            for (PacketDirection direction : PacketDirection.values())
                state.packetsRegistry.put(direction, HashBiMap.<Integer, Class<? extends Packet<?>>>create());
    }

    public static ConnectionState getById(int stateId) {
        for (ConnectionState state : values())
            if (state.id == stateId)
                return state;
        return null;
    }

    public static int getPacketID(ConnectionState connectionState, PacketDirection direction, Class<? extends Packet<?>> packetClass) {
        return connectionState.getPacketID(direction, packetClass);
    }

    /************************************************************************************************************
     *                                              Class                                                       *
     ************************************************************************************************************/


    protected final int id;
    protected final Map<PacketDirection, BiMap<Integer, Class<? extends Packet<?>>>> packetsRegistry = new HashMap<>();

    protected void registerPacket(PacketDirection direction, int packetId, Class<? extends Packet<?>> packetClass) {
        BiMap<Integer, Class<? extends Packet<?>>> biMap = packetsRegistry.get(direction);

        if (biMap.containsKey(packetId)) {
            String ex = direction + " packetID " + packetId + " is already registered for packet class " + biMap.get(packetId);
            throw new IllegalArgumentException(ex);
        }
        if (biMap.containsValue(packetClass)) {
            String ex = direction + " packet " + packetClass + " is already known as ID " + getPacketID(direction, packetClass);
            throw new IllegalArgumentException(ex);
        }

        biMap.put(packetId, packetClass);
    }

    public int getPacketID(PacketDirection direction, Class<? extends Packet<?>> packetClass) {
        for (Map.Entry<Integer, Class<? extends Packet<?>>> entry : packetsRegistry.get(direction).entrySet())
            if (entry.getValue().equals(packetClass))
                return entry.getKey();
        return -1;
    }

    public Packet<?> getPacket(PacketDirection direction, int packetId) throws IllegalAccessException, InstantiationException {
        Class<? extends Packet<?>> packetClass = packetsRegistry.get(direction).get(packetId);
        return packetClass == null ? null : packetClass.newInstance();
    }


}
