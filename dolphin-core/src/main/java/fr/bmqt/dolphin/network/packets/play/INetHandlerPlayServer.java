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
