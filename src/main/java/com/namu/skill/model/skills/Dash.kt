package com.namu.skill.model.skills

import com.namu.skill.model.Skill
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector

class Dash() : Skill(){

    private operator fun Vector.invoke(multiply: Vector) {}

    override val name: String = "Dash"
    override val damage: Double = 0.0
    override val icon: ItemStack = ItemStack(Material.DIAMOND_BOOTS)
    override fun animation(caster: Entity) {
        val player : Player = caster as Player
        val head : Location = player.location.add(0.0, player.eyeHeight,0.0)
        val look : Vector = player.location.direction.normalize()
        val direction : Vector = head.subtract(player.location.direction).toVector().normalize()
        caster.velocity(direction.multiply(1))
    }
    

}


