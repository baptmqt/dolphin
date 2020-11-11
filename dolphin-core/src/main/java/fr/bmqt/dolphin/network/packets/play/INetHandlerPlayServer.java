package fr.bmqt.dolphin.network.packets.play;

import fr.bmqt.dolphin.network.packets.INetHandler;
import fr.bmqt.dolphin.network.packets.play.client.*;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public interface INetHandlerPlayServer extends INetHandler {

    void handleAnimation(CAnimationPacket packet);

    void processChatMessage(CChatMessagePacket packet);

    void processClickWindow(CClickWindowPacket packet);

    void processClientSettings(CClientSettingsPacket packet);

    void processClientStatus(CClientStatusPacket packet);

    void processCloseWindow(CCloseWindowPacket packet);

    void processConfirmTeleport(CConfirmTeleportPacket packet);

    void processConfirmTransaction(CConfirmTransactionPacket packet);

    void processCreativeInventoryAction(CCreativeInventoryActionPacket packet);

    void processCustomPayload(CCustomPayloadPacket packet);

    void processEnchantItem(CEnchantItemPacket packet);

    void processHeldItemChange(CHeldItemChangePacket packet);

    void processEntityAction(CEntityActionPacket packet);

    void processKeepAlive(CKeepAlivePacket packet);

    void processPlayer(CPlayerPacket packet);

    void processPlayerAbilities(CPlayerAbilitiesPacket packet);

    @Deprecated
    void processPlayerDigging(CPlayerDiggingPacket packet);

    @Deprecated
    void processInput(CInputPacket cInputPacket);

    void processRightClickBlock(CPlayerTryToUseItemOnBlockPacket packet);

    void processPlayerBlockPlacement(CPlayerTryToUseItemPacket packet);

    @Deprecated
    void handleRecipeInfo(CRecipeInfoPacket packet);

    @Deprecated
    void handleRecipePlacement(CRecipePlacementPacket packet);

    void handleResourcePackStatus(CResourcePackStatusPacket packet);

    @Deprecated
    void handleSeenAdvancements(CSeenAdvancementsPacket packet);

    void handleSpectate(CSpectatePacket packet);

    void processSteerBoat(CSteerBoatPacket packet);

    @Deprecated
    void processTabComplete(CTabCompletePacket packet);

    @Deprecated
    void processUpdateSign(CUpdateSignPacket packet);

    @Deprecated
    void processUseEntity(CUseEntityPacket packet);

    void processVehicleMove(CVehicleMovePacket packet);
}
