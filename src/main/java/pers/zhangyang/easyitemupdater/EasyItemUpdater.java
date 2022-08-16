package pers.zhangyang.easyitemupdater;

import org.bstats.bukkit.Metrics;
import pers.zhangyang.easylibrary.EasyPlugin;

public class EasyItemUpdater extends EasyPlugin {
    @Override
    public void onOpen() {
        new Metrics(this,16159);
    }

    @Override
    public void onClose() {

    }
}
