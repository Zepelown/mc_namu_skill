package com.namu.skill

import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.skill.command.CommandDebug
import com.namu.skill.command.CommandGetSkillIcon
import com.namu.skill.command.CommandUseSkill
import com.namu.skill.model.SkillManager
import com.namu.skill.model.SkillNameTag
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin

class SkillPlugin : JavaPlugin(), CommandExecutor, Listener {

    companion object {
        lateinit var instance: SkillPlugin
    }

    override fun onEnable() {
        instance = this

        CommandManager(true).apply {
            addComponent("icon", CommandGetSkillIcon())
            addComponent("use", CommandUseSkill())
            addComponent("debug", CommandDebug())
        }.register(getCommand("skill"))

        Bukkit.getPluginManager().registerEvents(this, this)
    }

    override fun onDisable() {}

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) {
            val itemStack = event.item ?: return
            val skillNameTag = itemStack.getNBTTagCompound(SkillNameTag::class.java) ?: return

            SkillManager.getSkill(skillNameTag.name)?.use(event.player)
        }
    }

}