package com.namu.skill.model

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack

abstract class Skill {

    abstract val name: String

    abstract val damage: Double

    abstract val icon: ItemStack

    abstract fun animation(caster: Entity)

    fun use(caster: Entity) {
        animation(caster)
    }

    fun damage(caster: Entity, entity: Entity) {
        entity.getNearbyEntities(0.1, 0.1, 0.1)
                .filterIsInstance<LivingEntity>()
                .forEach { livingEntity -> livingEntity.damage(damage, caster) }
    }

}