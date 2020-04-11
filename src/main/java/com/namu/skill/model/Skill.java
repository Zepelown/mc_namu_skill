package com.namu.skill.model;

import org.bukkit.entity.Player;

public abstract class Skill {

    public static double power;
    public static double x;
    public static double y;
    public static double z;

    abstract void animation(Player player);

    public void use(Player player) {
        animation(player);
    };

}
