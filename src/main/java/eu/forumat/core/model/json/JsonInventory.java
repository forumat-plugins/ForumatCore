package eu.forumat.core.model.json;

import eu.forumat.core.inventory.FastInv;
import eu.forumat.core.inventory.item.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@AllArgsConstructor
@Getter
public class JsonInventory {

    private final int size;
    private final String title;
    private final Material fillItemMaterial;
    private final int fillItemSubID;
    private final Material borderMaterial;
    private final int borderSubID;
    private final Material cornerMaterial;
    private final int cornerSubID;
    private final JsonItem[] otherItems;

    public FastInv toFastInv() {

        FastInv fastInv = new FastInv(getSize(), ChatColor.translateAlternateColorCodes('&', getTitle()));

        for (int i = 0; i < this.getSize(); i++) {
            fastInv.setItem(i, new ItemBuilder(getFillItemMaterial()).data(getFillItemSubID()).name("§6").build(), event -> event.setCancelled(true));
        }

        for (int i : fastInv.getBorders()) {
            fastInv.setItem(i, new ItemBuilder(getBorderMaterial()).data(getBorderSubID()).name("§6").build(), event -> event.setCancelled(true));
        }

        for (int i : fastInv.getCorners()) {
            fastInv.setItem(i, new ItemBuilder(getCornerMaterial()).data(getCornerSubID()).name("§6").build(), event -> event.setCancelled(true));
        }

        for (JsonItem item : otherItems) {
            fastInv.setItem(item.getSlot(), item.toItemStack());
        }

        return fastInv;

    }

}
