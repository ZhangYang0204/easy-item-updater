package pers.zhangyang.easyitemupdater.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pers.zhangyang.easyitemupdater.domain.Updater;
import pers.zhangyang.easylibrary.annotation.EventListener;
import pers.zhangyang.easylibrary.util.CommandUtil;
import pers.zhangyang.easylibrary.util.MessageUtil;
import pers.zhangyang.easyitemupdater.yaml.MessageYaml;
import pers.zhangyang.easyitemupdater.yaml.UpdaterYaml;
import pers.zhangyang.easylibrary.util.PlayerUtil;

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

                        //lore匹配到了
                        //检查背包空间
                        if (u.getCompensatedItemStack()!=null&&PlayerUtil.checkSpace(player,u.getCompensatedItemStack())
                                <itemStack.getAmount()*u.getCompensatedItemStack().getAmount()){
                            MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notEnoughSpace"));
                            event.setCancelled(true);
                            break;
                        }

                        //添加物品和执行指令
                        for (int i=0;i<itemStack.getAmount();i++){
                            if(u.getCmdList()!=null){
                                CommandUtil.dispatchCommandList(player,u.getCmdList());
                            }
                            if (u.getCompensatedItemStack()!=null) {
                                PlayerUtil.addItem(player, u.getCompensatedItemStack(), u.getCompensatedItemStack().getAmount()
                                       );
                            }
                        }

                        itemStack.setAmount(0);
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.updateItemStack"));
                        break;

                    }
                }


                continue;
            }



            assert u.getRecycledItemStack() != null;


            if (u.isEnableMatchAmount()){
                if (u.getRecycledItemStack().equals(itemStack)){

                    //完全匹配

                    if (u.getCompensatedItemStack()!=null&&PlayerUtil.checkSpace(player,u.getCompensatedItemStack())
                            <u.getCompensatedItemStack().getAmount()){
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notEnoughSpace"));
                        event.setCancelled(true);
                        break;
                    }

                    //添加物品和执行指令
                        if(u.getCmdList()!=null){
                            CommandUtil.dispatchCommandList(player,u.getCmdList());
                        }
                        if (u.getCompensatedItemStack()!=null) {
                            PlayerUtil.addItem(player, u.getCompensatedItemStack(), u.getCompensatedItemStack().getAmount()
                                    );
                        }


                    itemStack.setAmount(0);
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.updateItemStack"));

                }
                continue;
            }

            if (u.getRecycledItemStack().isSimilar(itemStack)){


                //相似匹配

                if (u.getCompensatedItemStack()!=null&&PlayerUtil.checkSpace(player,u.getCompensatedItemStack())
                        <itemStack.getAmount()*u.getCompensatedItemStack().getAmount()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notEnoughSpace"));
                    event.setCancelled(true);
                    break;
                }

                //添加物品和执行指令
                for (int i=0;i<itemStack.getAmount();i++){
                    if(u.getCmdList()!=null){
                        CommandUtil.dispatchCommandList(player,u.getCmdList());
                    }
                    if (u.getCompensatedItemStack()!=null) {
                        PlayerUtil.addItem(player, u.getCompensatedItemStack(), u.getCompensatedItemStack().getAmount()
                               );
                    }
                }


                itemStack.setAmount(0);
                MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.updateItemStack"));


            }

        }








    }
}
