package com.namu.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandDebug : CommandComponent() {
    override val argumentsLength: Int = 0
    override val description: String = ""
    override val usage: String = ""
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        println((sender as Player).inventory.itemInMainHand)
        return true
    }
}