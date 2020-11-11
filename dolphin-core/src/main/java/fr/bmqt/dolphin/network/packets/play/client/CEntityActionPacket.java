package fr.bmqt.dolphin.network.packets.play.client;

import fr.bmqt.dolphin.network.packets.Packet;
import fr.bmqt.dolphin.network.packets.PacketBuffer;
import fr.bmqt.dolphin.network.packets.play.INetHandlerPlayServer;
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
public class CEntityActionPacket implements Packet<INetHandlerPlayServer> {

    protected int entityID;
    protected Action action;
    protected int auxData;

    /** todo : more constructors ?
     *  CEntityActionPacket(Entity entity, Action action) -> (auxData = 0)
     *  CEntityActionPacket(Entity entity, Action action, int auxData)
     *  entityID = entity.getID()
     */

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        entityID = buf.readVarIntFromBuffer();
        action = buf.readEnumValue(Action.class);
        auxData = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(entityID);
        buf.writeEnumValue(action);
        buf.writeVarIntToBuffer(auxData);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processEntityAction(this);
    }

    public static enum Action
    {
        START_SNEAKING,
        STOP_SNEAKING,
        STOP_SLEEPING,
        START_SPRINTING,
        STOP_SPRINTING,
        START_RIDING_JUMP,
        STOP_RIDING_JUMP,
        OPEN_INVENTORY,
        START_FALL_FLYING;
    }

}
