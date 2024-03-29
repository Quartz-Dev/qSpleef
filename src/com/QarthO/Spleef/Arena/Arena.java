package com.QarthO.Spleef.Arena;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.QarthO.Spleef.utils.Language;
import com.QarthO.Spleef.utils.FormatMessage;

public class Arena {
	
	HashMap<Player, Integer> player_list = new HashMap<Player, Integer>();
	HashMap<Player, Location> loc_back_list = new HashMap<Player, Location>();
	
	Set<Block> floor = new HashSet<Block>();
	int floor_level;
	Material floor_type = Material.SNOW_BLOCK;
	
	World world;
	Location loc_join;
	Location loc_spec;
	String name;
	Location floor_loc1;
	Location floor_loc2;
	
	public Arena(String name, World world, Material floor_type) {
		this.name = name;
		this.world = world;
		this.floor_type = floor_type;
	}
	
	public Arena(String name, World world, Location floor_loc1, Location floor_loc2, Location loc_join, Location loc_spec, Material floor_type) {
		this.name = name;
		this.world = world;
		this.floor_loc1 = floor_loc1;
		this.floor_loc2 = floor_loc2;
		this.loc_join = loc_join;
		this.loc_spec = loc_spec;
		this.floor_type = floor_type;
		
		this.floor_level = floor_loc1.getBlockY();
		
		this.create_floor();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setLoc1(Location loc) {
		floor_loc1 = loc;
	}
	
	public Location getLoc1() {
		return floor_loc1;
	}
	
	public void setLoc2(Location loc) {
		floor_loc2 = loc;
	}
	
	public Location getLoc2() {
		return floor_loc2;
	}
	
	public void setJoinLoc(Location loc) {
		loc.add(0,1.5,0);
		loc_join = loc;
	}
	
	public Location getJoinLoc() {
		return loc_join;
	}
	
	public void setSpecLoc(Location loc) {
		loc.add(0,1.5,0);
		loc_spec = loc;
	}
	
	public Location getSpecLoc() {
		return loc_spec;
	}
	
	public void setFloorType(Material material) {
		floor_type = material;
	}
	
	public Material getFloorType() {
		return floor_type;
	}
	
	public Set<Block> getFloor(){
		return floor;
	}
	
	public int getFloorLevel() {
		return floor_level;
	}
	
	public void create_floor() {
		
		if(floor_loc1.getY() != floor_loc2.getY()) return;
		floor_level = floor_loc1.getBlockY();
		
        int topBlockX = (floor_loc1.getBlockX() < floor_loc2.getBlockX() ? floor_loc2.getBlockX() : floor_loc1.getBlockX());
        int bottomBlockX = (floor_loc1.getBlockX() > floor_loc2.getBlockX() ? floor_loc2.getBlockX() : floor_loc1.getBlockX());
 
        int topBlockZ = (floor_loc1.getBlockZ() < floor_loc2.getBlockZ() ? floor_loc2.getBlockZ() : floor_loc1.getBlockZ());
        int bottomBlockZ = (floor_loc1.getBlockZ() > floor_loc2.getBlockZ() ? floor_loc2.getBlockZ() : floor_loc1.getBlockZ());
        
        this.floor_level = (int)floor_loc1.getY();
        
        for(int x = bottomBlockX; x <= topBlockX; x++)
        {
            for(int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                Block block = floor_loc1.getWorld().getBlockAt(x, floor_level, z);
                floor.add(block);
            }
        }
    }

	//////
	
	public void player_join(Player player) {
		player_list.put(player, 1);
		loc_back_list.put(player, player.getLocation());
		player.teleport(loc_join);
	}
	
	public void player_leave(Player player) {
		player_list.remove(player);
		player.teleport(loc_back_list.get(player));
		loc_back_list.remove(player);
	}
	
	public void player_spec(Player player) {
		player_list.put(player, 3);
		if(loc_back_list.get(player) == null) loc_back_list.put(player, player.getLocation());
		player.teleport(loc_spec);
	}
	
	public void replaceFloor() {
		if(floor == null) this.create_floor();
		for(Block block : floor) {
			block.setType(floor_type);
		}
	}
	
	
	@Override
	public String toString() {
		String str = 	Language.CHAT_PREFIX.getMessage() + ChatColor.YELLOW + name + " Zone: " + FormatMessage.loc(floor_loc1) + " <-> " + FormatMessage.loc(floor_loc2) + "\n" +
						Language.CHAT_PREFIX.getMessage() + ChatColor.YELLOW + "Join: " + FormatMessage.loc(loc_join) + " | Spec: " + FormatMessage.loc(floor_loc2);
		return str;
	}
}