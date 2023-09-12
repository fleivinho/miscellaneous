package org.miscellaneous.apis.itemcreator;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MiscellaneousItemCreator {

    @Getter
    private ItemStack itemStack;

    @Getter
    private ItemMeta itemMeta;

    public MiscellaneousItemCreator(Material material) {
        parse(new ItemStack(material));
    }

    private void parse(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public MiscellaneousItemCreator setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public MiscellaneousItemCreator setDisplayName(String displayName) {
        this.itemMeta.setDisplayName(displayName.replace("&", "§"));
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public MiscellaneousItemCreator setUnbreakable(boolean unbreakable) {
        this.itemMeta.spigot().setUnbreakable(unbreakable);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public String toBase64() {
        return null;
    }

}
