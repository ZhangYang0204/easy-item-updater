package pers.zhangyang.easyitemupdater.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pers.zhangyang.easyitemupdater.domain.Updater;
import pers.zhangyang.easylibrary.annotation.EventListener;
import pers.zhangyang.easylibrary.util.MessageUtil;
import pers.zhangyang.easyitemupdater.yaml.MessageYaml;
import pers.zhangyang.easyitemupdater.yaml.UpdaterYaml;

@EventListener
public class PlayerClickInventory implements Listener {
    @EventHandler
    public void on(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player player= (Player) event.getWhoClicked();

        ItemStack itemStack=event.getCurrentItem();
        if (itemStack==null){
            return;
        }
        for (Updater u: UpdaterYaml.INSTANCE.listTeleportPoint()){
            if (u.getLoreMatcherList()!=null){

                if (itemStack.getItemMeta()==null){
                    continue;
                }
                if (itemStack.getItemMeta().getLore()==null){
                    continue;
                }
                for (String matcher:u.getLoreMatcherList()){
                    if (itemStack.getItemMeta().getLore().contains(matcher)){
                        itemStack.setAmount(0);
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.updateItemStack"));
                        break;
                    }
                }


                continue;
            }


            assert u.getItemStack() != null;
            if (u.isEnableOnlyMatchLore()){

                if (u.getItemStack().getItemMeta()==null
                ||itemStack.getItemMeta()==null){
                    continue;
                }

                if (u.getItemStack().getItemMeta().getLore()==null&&itemStack.getItemMeta().getLore()==null){
                    itemStack.setAmount(0);

                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.updateItemStack"));
                    continue;
                }
                if (u.getItemStack().getItemMeta().getLore()!=null&&itemStack.getItemMeta().getLore()!=null
                &&u.getItemStack().getItemMeta().equals(itemStack.getItemMeta().getLore())){
                    itemStack.setAmount(0);
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.updateItemStack"));
                    continue;
                }
                continue;

            }
            if (u.isEnableMatchAmount()){
                if (u.getItemStack().equals(itemStack)){
                    itemStack.setAmount(0);
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.updateItemStack"));
                }
                continue;
            }

            if (u.getItemStack().isSimilar(itemStack)){
                itemStack.setAmount(0);
                MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.updateItemStack"));
            }

        }








    }
}
