package eu.forumat.core.model.json;

import eu.forumat.core.inventory.item.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class JsonItem {

    private Material material;
    private int subID;
    private final int amount;
    private final int slot;
    private final String displayName;
    private final Enchantment enchantment;
    private final String[] lore;

    public ItemStack toItemStack() {

        ItemBuilder builder = new ItemBuilder(this.material)
                .name(ChatColor.translateAlternateColorCodes('&', this.displayName))
                .data(this.subID)
                .amount(this.amount);

        if (this.enchantment != null) builder.enchant(this.enchantment);

        if (this.lore != null)
            builder.lore(Arrays.stream(this.lore).map(loreEntry -> ChatColor.translateAlternateColorCodes('&', loreEntry)).collect(Collectors.toList()));

        return builder.build();
    }

    public ItemStack toItemStack(String... placeHolders) {

        if (placeHolders == null) return toItemStack();

        String finalDisplayName = this.displayName;
        List<String> finalLore = Arrays.stream(this.lore).collect(Collectors.toList());

        for (int i = 0; i < placeHolders.length; ) {
            AtomicInteger j = new AtomicInteger(i);

            finalDisplayName = finalDisplayName.replace(placeHolders[i], placeHolders[i + 1]);
            finalLore = finalLore.stream().map(loreEntry -> {
                String replacedValue = loreEntry.replace(placeHolders[j.get()], placeHolders[j.get() + 1]);
                return ChatColor.translateAlternateColorCodes('&', replacedValue);
            }).collect(Collectors.toList());

            i += 2;
        }

        ItemBuilder builder = new ItemBuilder(this.material)
                .name(ChatColor.translateAlternateColorCodes('&', finalDisplayName))
                .data(this.subID)
                .lore(finalLore)
                .amount(this.amount);

        if (this.enchantment != null) builder.enchant(this.enchantment);

        return builder.build();
    }

}
