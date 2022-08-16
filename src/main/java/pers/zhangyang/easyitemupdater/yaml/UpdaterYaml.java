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
                teleportPointList.add(new Updater(loreMatcherList));
                continue;
            }

            ItemStack itemStack=getItemStack("updater."+s+".itemStack");
            if (itemStack==null){
                continue;
            }
            boolean onlyLore=getBooleanDefault("updater."+s+".enableOnlyMatchLore");
            boolean amount=getBooleanDefault("updater."+s+".enableMatchAmount");
            teleportPointList.add(new Updater(onlyLore,amount,itemStack));

        }
        return teleportPointList;
    }
}
