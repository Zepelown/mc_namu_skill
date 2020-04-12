package com.namu.skill;

import com.namu.skill.model.Dash;
import com.namu.skill.model.Skill;
import com.namu.skill.model.ThrowSword;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;

public final class SkillPlugin extends JavaPlugin implements CommandExecutor {

    private static SkillPlugin instance;

    public static SkillPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // register Skill
        skillHashMap.put("Dash", new Dash());
        skillHashMap.put("ThrowSword", new ThrowSword());

        // register command
        getCommand("skill").setExecutor(this);
    }

    @Override
    public void onDisable() {
    }

    private HashMap<String, Skill> skillHashMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Arrays.toString(skillHashMap.keySet().toArray(new String[0])));
            return false;
        }

        Skill skill = skillHashMap.get(args[0]);
        Skill.power = Double.parseDouble(args[1]);
        Skill.x = Double.parseDouble(args[2]);
        Skill.y = Double.parseDouble(args[3]);
        Skill.z = Double.parseDouble(args[4]);

        if (skill == null) {
            sender.sendMessage("Unknown Skill!");
            return false;
        }

        skill.use((Player) sender);
        return true;
    }
}
