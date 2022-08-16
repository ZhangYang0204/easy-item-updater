package pers.zhangyang.easyitemupdater.domain;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class Updater {
    private boolean enableOnlyMatchLore;
    private boolean enableMatchAmount;
    private ItemStack itemStack;
    private List<String> loreMatcherList;

    public Updater(boolean enableOnlyMatchLore, boolean enableMatchAmount,@NotNull ItemStack itemStack) {
        this.enableOnlyMatchLore = enableOnlyMatchLore;
        this.enableMatchAmount = enableMatchAmount;
        this.itemStack = itemStack;
    }
    public Updater(List<String> loreMatcherList) {
        this.loreMatcherList = loreMatcherList;
    }
    public boolean isEnableOnlyMatchLore() {
        return enableOnlyMatchLore;
    }

    @Nullable
    public List<String> getLoreMatcherList() {
        return loreMatcherList;
    }

    public boolean isEnableMatchAmount() {
        return enableMatchAmount;
    }

    @Nullable
    public ItemStack getItemStack() {
        return itemStack;
    }

}
