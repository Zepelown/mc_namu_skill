package com.namu.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.namu.skill.model.Skill
import com.namu.skill.model.SkillManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

abstract class SkillCommand : CommandComponent() {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {

        val skillName = args.next()

        val skill = SkillManager.getSkill(skillName)

        if (skill == null) {
            sender.sendErrorMessage("존재하지 않는 스킬 입니다.")
            return false
        }

        return onCommand(sender, label, command, componentLabel, args, skill)
    }

    abstract fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, skill: Skill): Boolean

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, componentLabel: String, args: ArgumentList): Iterable<String> {
        if (args.getCursor() == 1) {
            return SkillManager.getSkillNames()
        }
        return listOf();
    }
}