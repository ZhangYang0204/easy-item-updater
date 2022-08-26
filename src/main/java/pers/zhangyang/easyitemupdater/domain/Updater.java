package pers.zhangyang.easyitemupdater.domain;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Updater {
    private boolean enableMatchAmount;
    private ItemStack recycledItemStack;

    private ItemStack   compensatedItemStack;

    private List<String> cmdList;

    @Nullable
    public List<String> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<String> cmdList) {
        this.cmdList = cmdList;
    }

    private List<String> loreMatcherList;

    public Updater( Boolean enableMatchAmount,@NotNull ItemStack itemStack) {
        this.enableMatchAmount = enableMatchAmount;
        this.recycledItemStack = itemStack;
    }



    public Updater(List<String> loreMatcherList) {
        this.loreMatcherList = loreMatcherList;
    }

    @Nullable
    public ItemStack getCompensatedItemStack() {
        return compensatedItemStack;
    }

    public void setCompensatedItemStack(ItemStack compensatedItemStack) {
        this.compensatedItemStack = compensatedItemStack;
    }

    @Nullable
    public List<String> getLoreMatcherList() {
        return loreMatcherList;
    }

    public boolean isEnableMatchAmount() {
        return enableMatchAmount;
    }

    @Nullable
    public ItemStack getRecycledItemStack() {
        return recycledItemStack;
    }

}
