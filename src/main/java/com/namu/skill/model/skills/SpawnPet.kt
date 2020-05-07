package com.namu.skill.model.skills

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.particle.ParticleManager
import com.kkomi.devlibrary.particle.ParticleRangeEffect
import com.namu.skill.SkillPlugin
import com.namu.skill.model.Skill
import com.namu.skill.util.EntityManager
import org.apache.commons.lang.math.DoubleRange
import org.bukkit.*
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin

class SpawnPet : Skill() {

    override val name: String = "SpawnPet"

    override val damage: Double = 10.0

    override val icon: ItemStack = createItemStack(Material.PAPER, "펫 소환권", listOf(), 1).apply {
        itemMeta = itemMeta!!.apply { setCustomModelData(1) }
    }

    override fun animation(caster: Entity) {
        val pet = EntityManager.createArmorStand(caster.location)

        pet.equipment!!.helmet = ItemStack(Material.STICK).apply {
            itemMeta = itemMeta!!.apply { setCustomModelData(1) }
        }

        var x = 0

        Bukkit.getScheduler().runTaskTimer(SkillPlugin.instance, Runnable {
            x += 10
            val spawnedLocation = caster.location.clone().add(
                    cos(Math.toRadians(caster.location.yaw.toDouble())),
                    sin(Math.toRadians(x.toDouble())) / 2,
                    sin(Math.toRadians(caster.location.yaw.toDouble()))
            )
            pet.teleport(spawnedLocation)
        }, 5L, 1L)

        Bukkit.getScheduler().runTaskTimer(SkillPlugin.instance, Runnable {
            pet.getNearbyEntities(10.0, 10.0, 10.0)
                    .filter { it !is Player }
                    .filterIsInstance<LivingEntity>()
                    .forEach {
                        val d = it.location.distance(pet.location.up())
                        val dv = it.location.up().toVector().subtract(pet.location.up().toVector())
                        for (i in 0..d.toInt() * 2) {
                            val loc = pet.location.up().add(dv.clone().multiply(i / 20.0))
                            loc.world!!.spawnParticle(Particle.REDSTONE, loc, 1, Particle.DustOptions(Color.AQUA, 1f))
                        }
                        it.damage(damage, caster)
                        return@forEach
                    }
        }, 5L, 60L)
    }

    fun Location.up(): Location {
        return add(0.0, 2.0, 0.0)
    }

}
