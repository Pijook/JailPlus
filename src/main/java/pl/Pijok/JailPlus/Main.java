package pl.Pijok.JailPlus;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.Pijok.JailPlus.jail.Jail;
import pl.Pijok.JailPlus.jail.Prisoner;
import pl.Pijok.JailPlus.utils.JailUtils;

import java.util.HashMap;

public class Main extends JavaPlugin {

    private static Main instance;

    public static HashMap<String, Jail> jails = new HashMap<String, Jail>();
    public static HashMap<Player, Prisoner> prisoners = new HashMap<Player, Prisoner>();

    @Override
    public void onEnable() {
        instance = this;


        JailUtils.loadJails();

    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

}
