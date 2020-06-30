package pl.Pijok.JailPlus.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.Pijok.JailPlus.Main;
import pl.Pijok.JailPlus.jail.Jail;
import pl.Pijok.JailPlus.jail.Prisoner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JailUtils {

    public static void loadPlayer(Player player){
        File file = new File(Main.getInstance().getDataFolder(), "prisoners.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        String name = player.getName();

        if(configuration.contains("prisoners." + name)){
            long throw_into_time = configuration.getLong("prisoners." + name + ".throw_into_time");
            long ban_time = configuration.getLong("prisoners." + name + ".ban_time");
            String prison_name = configuration.getString("prisoners." + name + ".prison_name");
            Jail jail = Main.jails.get(prison_name);
            jail.addPrisoner(player);
            Main.prisoners.put(player, new Prisoner(
                    prison_name,
                    throw_into_time,
                    ban_time
            ));

            player.teleport(jail.getLocation());

            System.out.println("Zaladowano wieznia: " + player.getName());
            System.out.println("Wyrok: " + ban_time);
        }
        else{
            return;
        }

    }

    public static void loadJails(){
        File file = new File(Main.getInstance().getDataFolder(), "jails.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        int last_id = configuration.getInt("last_id");

        for(int i = 0; i < last_id; i++){
            int x = configuration.getInt("jails." + i + ".x");
            int y = configuration.getInt("jails." + i + ".y");
            int z = configuration.getInt("jails." + i + ".z");
            String world = configuration.getString("jails." + i + ".world");
            String jail_name = configuration.getString("jails." + i + ".name");

            Main.jails.put(jail_name, new Jail(new Location(Bukkit.getWorld(world),x,y,z), new ArrayList<Player>()));
            System.out.println("Zaladowano wiezienie: " + jail_name);
        }
    }

    public static void addJail(Location location, String name){
        File file = new File(Main.getInstance().getDataFolder(), "jails.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        int last_id = configuration.getInt("last_id");

        configuration.set("jails." + last_id + ".name", name);
        configuration.set("jails." + last_id + ".x", location.getX());
        configuration.set("jails." + last_id + ".y", location.getY());
        configuration.set("jails." + last_id + ".z", location.getZ());
        configuration.set("jails." + last_id + ".world", location.getWorld().getName());

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.jails.put(name, new Jail(location, new ArrayList<Player>()));
    }

    public static void putIntoJail(Player player, String reason, String jail_name, long time){
        Jail jail = Main.jails.get(jail_name);
        jail.addPrisoner(player);
        player.teleport(jail.getLocation());
        Main.prisoners.put(player, new Prisoner(jail_name, System.currentTimeMillis(), time));

        ChatUtils.sendMessage(player, "&cZostales/as wrzucony/a do wiezienia za: &e" + reason + " &c na " + time + " sekund");
    }

    public static boolean isInJail(Player player){
        return Main.prisoners.containsKey(player);
    }

    public static boolean jailExists(String name){
        return Main.jails.containsKey(name);
    }

    public static void removeFromJail(Player player){
        Prisoner prisoner = Main.prisoners.get(player);
        Jail jail = Main.jails.get(prisoner.getPrison_name());
        player.teleport(Bukkit.getWorld("plotworld").getSpawnLocation());

        jail.removePrisoner(player);
        Main.prisoners.remove(player);
    }

    public static void removeJail(String name){
        Jail jail = Main.jails.get(name);

        if(jail.getPrisoners().size() > 0){
            for(Player player : jail.getPrisoners()){
                removeFromJail(player);
            }
        }
        Main.jails.remove(name);
    }

    public static void savePrisoner(Player player){
        if(!Main.prisoners.containsKey(player)){
            return;
        }

        Prisoner prisoner = Main.prisoners.get(player);
        String name = player.getName();
        File file = new File(Main.getInstance().getDataFolder(), "prisoners.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        configuration.set("prisoners." + name + ".throw_into_time", prisoner.getThrow_into_time());
        configuration.set("prisoners." + name + ".ban_time", prisoner.getBan_time());
        configuration.set("prisoners." + name + ".prison_name", prisoner.getPrison_name());

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
