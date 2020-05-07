package com.namu.skill.model.skills

import com.namu.skill.SkillPlugin
import com.namu.skill.model.Skill
import com.namu.skill.util.EntityManager
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import org.bukkit.util.EulerAngle
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin

class SpinSword : Skill() {

    override val name: String = "SpinSword"

    override val damage: Double = 1.0

    override val icon: ItemStack = ItemStack(Material.GOLDEN_SWORD)

    private val radius = 4

    override fun animation(caster: Entity) {

        for (i in 0 until 360 step 60) { // 60도 씩 시작

            // summon
            val armorStand = EntityManager.createArmorStand(caster.location.clone().add(cos(Math.toRadians(i.toDouble())) * radius, 2.0, sin(Math.toRadians(i.toDouble())) * radius))
            armorStand.equipment!!.helmet = ItemStack(Material.DIAMOND_SWORD)
            armorStand.headPose = EulerAngle(0.0, 0.0, 0.0)

            var repeat = 0 // 몇번을 반복 하였는 가?

            Bukkit.getScheduler().runTaskTimer(SkillPlugin.instance, Runnable {
                val nx = cos(Math.toRadians((i + repeat).toDouble())) * radius
                val ny = sin(Math.toRadians((i + repeat).toDouble())) * radius
                armorStand.teleport(caster.location.clone().add(Vector(nx, 0.0, ny)))

                repeat += 10
            }, 0L, 1L)

        }

    }

}