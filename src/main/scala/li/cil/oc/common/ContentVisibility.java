package li.cil.oc.common;

import li.cil.oc.Settings;
import li.cil.oc.common.init.OCBlocks;
import li.cil.oc.common.init.OCItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

/** Optional content that stays registered for world compatibility, but can be hidden from catalogs and have its recipes gated. */
public final class ContentVisibility {
    private ContentVisibility() {}

    private static final class ItemSets {
        private static final Set<Item> TIER_FOUR = Set.of(
                OCBlocks.CaseTier4().asItem(),
                OCBlocks.HoloScreenTier4().asItem(),
                OCBlocks.ScreenTier4().asItem(),
                OCBlocks.FlatScreenBackTier4().asItem(),
                OCBlocks.FlatScreenFrontTier4().asItem(),
                OCItems.APUTier3().asItem(),
                OCItems.ChipTier4().asItem(),
                OCItems.ComponentBusTier4().asItem(),
                OCItems.CPUTier4().asItem(),
                OCItems.GraphicsCardTier4().asItem(),
                OCItems.HDDTier4().asItem(),
                OCItems.RAMTier7().asItem(),
                OCItems.RAMTier8().asItem(),
                OCItems.ServerTier4().asItem(),
                OCItems.SSDTier3().asItem()
        );

        private static final Set<Item> OPEN_SCREENS = Set.of(
                OCBlocks.FlatScreenBackTier1().asItem(),
                OCBlocks.FlatScreenBackTier2().asItem(),
                OCBlocks.FlatScreenBackTier3().asItem(),
                OCBlocks.FlatScreenBackTier4().asItem(),
                OCBlocks.FlatScreenFrontTier1().asItem(),
                OCBlocks.FlatScreenFrontTier2().asItem(),
                OCBlocks.FlatScreenFrontTier3().asItem(),
                OCBlocks.FlatScreenFrontTier4().asItem()
        );
    }

    public static boolean isTier4(Item item) {
        return ItemSets.TIER_FOUR.contains(item);
    }

    public static boolean isOpenScreens(Item item) {
        return ItemSets.OPEN_SCREENS.contains(item);
    }

    public static boolean isHidden(ItemStack stack) {
        return !stack.isEmpty() && isHidden(stack.getItem());
    }

    public static boolean isHidden(Item item) {
        return (Settings.get().hideTier4() && isTier4(item)) ||
                (Settings.get().hideOpenScreens() && isOpenScreens(item));
    }

    public static Set<Item> hiddenItems() {
        Set<Item> hidden = new HashSet<>();
        if (Settings.get().hideTier4()) hidden.addAll(ItemSets.TIER_FOUR);
        if (Settings.get().hideOpenScreens()) hidden.addAll(ItemSets.OPEN_SCREENS);
        return Set.copyOf(hidden);
    }
}
