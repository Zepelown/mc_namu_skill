package com.namu.skill.model;

import com.namu.skill.SkillPlugin;
import com.namu.skill.util.EntityManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class ThrowSword extends Skill {

    @Override
    void animation(Player player) {
        Location location = player.getLocation();
        location.add(0, -0.5, 0);
        location.setPitch(0);

        ArmorStand armorStand = EntityManager.getInstance().createArmorStand(location);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setHeadPose(new EulerAngle(Skill.x, Skill.y, Skill.z));
        armorStand.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_SWORD));

        Bukkit.getScheduler().runTaskTimer(SkillPlugin.getInstance(), () -> {
            armorStand.teleport(armorStand.getLocation().add(armorStand.getLocation().getDirection().multiply(Skill.power)));
            location.getWorld().getNearbyEntities(armorStand.getLocation(), 0.1, 0.1, 0.1).forEach(entity -> {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.damage(0D);
            });
        }, 0, 0L);

        Bukkit.getScheduler().runTaskLater(SkillPlugin.getInstance(), armorStand::remove, 20L);
    }
}
