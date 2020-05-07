package com.namu.skill.util

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType

object EntityManager {

    val defaultWorld : World = Bukkit.getWorld("world")!!

    fun createArmorStand(location: Location): ArmorStand {
        val armorStand = defaultWorld.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand
        armorStand.isVisible = false
        armorStand.setGravity(false)
        return armorStand
    }

}