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
import fr.bmqt.dolphin.network.packets.play.server.*;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public interface INetHandlerPlayClient extends INetHandler {

    @Deprecated
    void processAdvancementInfo(SAdvancementInfoPacket sAdvancementInfoPacket);

    void handleAnimation(SAnimationPacket sAnimationPacket);

    @Deprecated
    void handleBlockAction(SBlockActionPacket sBlockActionPacket);

    @Deprecated
    void handleBlockBreakAnim(SBlockBreakAnimPacket sBlockBreakAnimPacket);

    @Deprecated
    void handleBlockChange(SBlockChangePacket sBlockChangePacket);

    void handleCamera(SCameraPacket sCameraPacket);

    void handleChangeGameState(SChangeGameStatePacket sChangeGameStatePacket);

    @Deprecated
    void handleChat(SChatPacket sChatPacket);

    void handleCloseWindow(SCloseWindowPacket sCloseWindowPacket);

    void handleCollectItem(SCollectItemPacket sCollectItemPacket);

    @Deprecated
    void handleCombatEvent(SCombatEventPacket sCombatEventPacket);

    void handleConfirmTransaction(SConfirmTransactionPacket sConfirmTransactionPacket);

    void handleCustomPayload(SCustomPayloadPacket sCustomPayloadPacket);

    @Deprecated
    void handleCooldown(SCooldownPacket sCooldownPacket);

    @Deprecated
    void handleCustomSound(SCustomSoundPacket sCustomSoundPacket);

    void handleDestroyEntities(SDestroyEntitiesPacket sDestroyEntitiesPacket);

    @Deprecated
    void handleDisconnect(SDisconnectPacket sDisconnectPacket);

    @Deprecated
    void handleDisplayObjective(SDisplayObjectivePacket sDisplayObjectivePacket);

    @Deprecated
    void handleEffect(SEffectPacket sEffectPacket);

    void handleEntityMovement(SEntityPacket sEntityPacket);

    void handleEntityAttach(SEntityAttachPacket sEntityAttachPacket);

    @Deprecated
    void handleEntityEffect(SEntityEffectPacket sEntityEffectPacket);

    @Deprecated
    void handleEntityEquipment(SEntityEquipmentPacket sEntityEquipmentPacket);

    void handleEntityHeadLook(SEntityHeadLookPacket sEntityHeadLookPacket);

    @Deprecated
    void handleEntityMetadata(SEntityMetadataPacket sEntityMetadataPacket);

    @Deprecated
    void handleEntityProperties(SEntityPropertiesPacket sEntityPropertiesPacket);

    void handleEntityStatus(SEntityStatusPacket sEntityStatusPacket);

    void handleEntityTeleport(SEntityTeleportPacket sEntityTeleportPacket);

    void handleEntityVelocity(SEntityVelocityPacket sEntityVelocityPacket);

    void handleHeldItemChange(SHeldItemChangePacket sHeldItemChangePacket);

    @Deprecated
    void handleJoinGame(SJoinGamePacket sJoinGamePacket);

    void handleKeepAlive(SKeepAlivePacket sKeepAlivePacket);

    @Deprecated
    void handleMaps(SMapPacket sMapPacket);

    void handleMoveVehicle(SMoveVehiclePacket sMoveVehiclePacket);

    @Deprecated
    void handleMultiBlockChange(SMultiBlockChangePacket sMultiBlockChangePacket);

    @Deprecated
    void handleOpenWindow(SOpenWindowPacket sOpenWindowPacket);

    @Deprecated
    void handleParticles(SParticlesPacket sParticlesPacket);

    @Deprecated
    void handlePlayerAbilities(SPlayerAbilitiesPacket sPlayerAbilitiesPacket);

    @Deprecated
    void handlePlayerListHeaderFooter(SPlayerListHeaderFooterPacket sPlayerListHeaderFooterPacket);

    @Deprecated
    void handlePlayerListItem(SPlayerListItemPacket sPlayerListItemPacket);

    @Deprecated
    void handlePlayerPosLook(SPlayerPosLookPacket sPlayerPosLookPacket);

    @Deprecated
    void handleRemoveEntityEffect(SRemoveEntityEffectPacket sRemoveEntityEffectPacket);

    void handleResourcePack(SResourcePackSendPacket sResourcePackSendPacket);

    @Deprecated
    void handleRespawn(SRespawnPacket sRespawnPacket);

    @Deprecated
    void handleScoreboardObjective(SScoreboardObjectivePacket sScoreboardObjectivePacket);

    @Deprecated
    void processSelectAdvancementsTap(SSelectAdvancementsTapPacket sSelectAdvancementsTapPacket);

    @Deprecated
    void handleServerDifficulty(SServerDifficultyPacket sServerDifficultyPacket);

    void handleSetExperience(SSetExperiencePacket sSetExperiencePacket);

    void handleSetPassengers(SSetPassengersPacket sSetPassengersPacket);

    @Deprecated
    void handleSetSlot(SSetSlotPacket sSetSlotPacket);

    @Deprecated
    void handleSignEditorOpen(SSignEditorOpenPacket sSignEditorOpenPacket);

    @Deprecated
    void handleSoundEffect(SSoundEffectPacket sSoundEffectPacket);

    void handleSpawnExperienceOrb(SSpawnExperienceOrbPacket sSpawnExperienceOrbPacket);

    void handleSpawnGlobalEntity(SSpawnGlobalEntityPacket sSpawnGlobalEntityPacket);

    @Deprecated
    void handleSpawnMob(SSpawnMobPacket sSpawnMobPacket);

    void handleSpawnObject(SSpawnObjectPacket sSpawnObjectPacket);

    @Deprecated
    void handleSpawnPainting(SSpawnPaintingPacket sSpawnPaintingPacket);

    @Deprecated
    void handleSpawnPlayer(SSpawnPlayerPacket sSpawnPlayerPacket);

    @Deprecated
    void handleSpawnPosition(SSpawnPositionPacket sSpawnPositionPacket);

    @Deprecated
    void handleStatistics(SStatisticsPacket sStatisticsPacket);

    void handleTabComplete(STabCompletePacket sTabCompletePacket);

    @Deprecated
    void handleTeams(STeamsPacket sTeamsPacket);

    void handleTimeUpdate(STimeUpdatePacket sTimeUpdatePacket);

    @Deprecated
    void handleTitle(STitlePacket sTitlePacket);

    @Deprecated
        // handleUpdateEntityNBT oO
    void handleUpdateBossInfo(SUpdateBossInfoPacket sUpdateBossInfoPacket);

    void handleUpdateHealth(SUpdateHealthPacket sUpdateHealthPacket);

    @Deprecated
    void handleUpdateScore(SUpdateScorePacket sUpdateScorePacket);

    @Deprecated
    void handleUpdateTileEntity(SUpdateTileEntityPacket sUpdateTileEntityPacket);

    @Deprecated
    void handleUseBed(SUseBedPacket sUseBedPacket);

    @Deprecated
    void handleWindowItems(SWindowItemsPacket sWindowItemsPacket);

    void handleWindowProperty(SWindowsPropertyPacket sWindowsPropertyPacket);

    @Deprecated
    void handleWorldBorder(SWorldBorderPacket sWorldBorderPacket);
}
