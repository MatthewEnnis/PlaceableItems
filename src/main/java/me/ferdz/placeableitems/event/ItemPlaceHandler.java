package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemPlaceHandler {

    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock e) {
        if (e.getWorld().isRemote() || e.getPlayer() == null || e.getFace() == null) {
            return;
        }
        if (!e.getPlayer().isSneaking()) { // TODO: #13 Make configurable hotkey for placing items
            return; // Abort if the user is not sneaking
        }

        ItemStack itemStack = e.getPlayer().getHeldItem(e.getHand());
        Item item = itemStack.getItem();
        PlaceableItemsBlock block = PlaceableItemsBlockRegistry.blockMap.get(item);
        if (block == null) {
            return;
        }

        // TODO: I got recommended to cache the BlockItems, as the creation may be expensive
        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        ActionResultType result = blockItem.tryPlace(
                new BlockItemUseContext(
                        new ItemUseContext(e.getPlayer(), e.getHand(),
                                new BlockRayTraceResult(e.getPlayer().getLookVec(), e.getFace(), e.getPos(), false))));
        if (result == ActionResultType.SUCCESS) {
            if (e.getPlayer().isCreative()) {
                itemStack.grow(1);
            }
            e.setResult(Event.Result.ALLOW);
        }
    }
}
