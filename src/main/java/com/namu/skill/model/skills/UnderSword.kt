package com.namu.skill.model.skills

import com.namu.skill.SkillPlugin
import com.namu.skill.model.Skill
import com.namu.skill.util.EntityManager
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack

class UnderSword : Skill() {

    override val name: String = this.javaClass.simpleName

    override val damage: Double = 1.0;

    override val icon: ItemStack = ItemStack(Material.DIAMOND_SWORD)

    override fun animation(caster: Entity) {
        caster.getNearbyEntities(10.0, 1.0, 10.0).filterIsInstance<LivingEntity>().forEach { entity ->
            val armorStand = EntityManager.createArmorStand(entity.location.add(0.0, -1.0, 0.0))
            armorStand.equipment!!.helmet = ItemStack(Material.DIAMOND_SWORD)

            val scheduler = Bukkit.getScheduler().runTaskTimer(SkillPlugin.instance, Runnable {
                armorStand.teleport(armorStand.location.add(0.0, 0.5, 0.0))
                damage(caster, armorStand)
            }, 0, 1L)

            Bukkit.getScheduler().runTaskLater(SkillPlugin.instance, Runnable {
                scheduler.cancel()
                armorStand.remove()
            }, 10L)

        }
    }

}