package com.namu.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.namu.skill.model.Skill
import com.namu.skill.util.EntityManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandUseSkill : SkillCommand() {

    override val argumentsLength: Int = 1

    override val description: String = "use skill then summon armor stand cursor location"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, skill: Skill): Boolean {
        val player = sender as Player
        val armorStand = EntityManager.createArmorStand(player.location)
        skill.use(armorStand)
        return true
    }

}
