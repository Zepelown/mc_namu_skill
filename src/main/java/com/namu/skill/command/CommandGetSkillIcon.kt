package com.namu.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.nms.addNBTTagCompound
import com.namu.skill.model.Skill
import com.namu.skill.model.SkillNameTag
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGetSkillIcon : SkillCommand() {

    override val argumentsLength: Int = 1

    override val description: String = "get skill icon"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, skill: Skill): Boolean {

        (sender as Player).inventory.addItem(skill.icon.addNBTTagCompound(SkillNameTag(skill.name)))
        sender.sendMessage("스킬 아이콘이 지급되었습니다.")

        return true
    }

}