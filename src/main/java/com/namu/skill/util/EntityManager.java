package com.namu.skill.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class EntityManager {
    private static EntityManager instance;

    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    private EntityManager() {
    }

    private static World defaultWorld = Bukkit.getWorld("world");

    public ArmorStand createArmorStand(Location location) {
        return (ArmorStand) defaultWorld.spawnEntity(location, EntityType.ARMOR_STAND);
    }
}
