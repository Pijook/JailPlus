package pl.Pijok.JailPlus.jail;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Jail {

    private Location location;
    private ArrayList<Player> prisoners;

    public Jail(Location location, ArrayList<Player> prisoners){
        this.location = location;
        this.prisoners = prisoners;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Player> getPrisoners() {
        return prisoners;
    }

    public void setPrisoners(ArrayList<Player> prisoners) {
        this.prisoners = prisoners;
    }

    public void addPrisoner(Player player) {
        prisoners.add(player);
    }

    public void removePrisoner(Player player){
        prisoners.remove(player);
    }
}
