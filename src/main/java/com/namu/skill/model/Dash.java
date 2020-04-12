package com.namu.skill.model;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Dash extends Skill {
    @Override
    void animation(Player player) {
        player.setVelocity(player.getLocation().clone().getDirection().multiply(4.0D));
        player.sendMessage("Use Dash!!!");
    }
}
