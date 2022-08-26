package pers.zhangyang.easyitemupdater.yaml;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import pers.zhangyang.easyitemupdater.domain.Updater;
import pers.zhangyang.easylibrary.base.YamlBase;

import java.util.ArrayList;
import java.util.List;

public class UpdaterYaml extends YamlBase {

    public static final UpdaterYaml INSTANCE = new UpdaterYaml();

    private UpdaterYaml() {
        super("updater.yml");
    }


    public List<Updater> listTeleportPoint(){
        List<Updater> teleportPointList=new ArrayList<>();

        ConfigurationSection configurationSection=yamlConfiguration.getConfigurationSection("updater");
        if (configurationSection==null){
            return teleportPointList;
        }
        for (String s:configurationSection.getKeys(false)){
            List<String> loreMatcherList=getStringList("updater."+s+".loreMatcher");
            if (loreMatcherList!=null){

                ItemStack cItemStack=getItemStack("updater."+s+".compensatedItemStack");
                List<String> cmdList=getStringList("updater."+s+".compensatedCommand");

                Updater updater=new Updater(loreMatcherList);
                updater.setCmdList(cmdList);
                updater.setCompensatedItemStack(cItemStack);

                teleportPointList.add(updater);
                continue;
            }

            ItemStack itemStack=getItemStack("updater."+s+".recycledItemStack");
            if (itemStack==null){
                continue;
            }
            boolean amount=getBooleanDefault("updater."+s+".enableMatchAmount");
            Updater updater=new Updater(amount,itemStack);

            ItemStack cItemStack=getItemStack("updater."+s+".compensatedItemStack");
            List<String> cmdList=getStringList("updater."+s+".compensatedCommand");

            updater.setCmdList(cmdList);
            updater.setCompensatedItemStack(cItemStack);

            teleportPointList.add(updater);

        }
        return teleportPointList;
    }
}
