package com.namu.skill.model

import com.namu.skill.model.skills.SpawnPet
import com.namu.skill.model.skills.SpinSword
import com.namu.skill.model.skills.UnderSword

object SkillManager {

    private val nameBySkill: MutableMap<String, Skill> = mutableMapOf()

    init {
        registerSkill(
                UnderSword(),
                SpinSword(),
                SpawnPet()
        )
    }

    fun getSkill(skillName: String): Skill? {
        return nameBySkill[skillName]
    }

    fun getSkillNames(): Set<String> {
        return nameBySkill.keys
    }

    private fun registerSkill(vararg skill: Skill) {
        skill.forEach { nameBySkill[it.name] = it }
    }

}