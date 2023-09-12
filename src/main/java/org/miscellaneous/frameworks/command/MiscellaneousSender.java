package org.miscellaneous.frameworks.command;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.text.Normalizer;
import java.util.Set;
import java.util.UUID;

public class MiscellaneousSender implements CommandSender {

    @Getter
    private final CommandSender commandSender;

    public MiscellaneousSender(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public Player getPlayer() {
        if (commandSender instanceof Player) {
            return (Player) commandSender;
        }
        return null;
    }
    @Override
    public void sendMessage(String s) {
        if (!(commandSender instanceof Player)) {
            /*
                Normalizando a mensagem para o console, fazendo com que pontuações e afins sejam adaptadas
                em todos os consoles
             */
            s = s.replace("§", ".cor.");
            s = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        }
        this.commandSender.sendMessage(s.replace(".cor.", "§"));
    }

    public boolean isPlayer() {
        return (commandSender instanceof Player);
    }

    /*
        Executar um comando
     */
    public void chat(String run) {
        if (isPlayer()) {
            getPlayer().chat(run);
        } else {
            if (run.startsWith("/")) run = run.substring(1);
            Bukkit.getServer().dispatchCommand(this, run);
        }
    }

    @Override
    public void sendMessage(String[] strings) {
        for (String string : strings) {
            sendMessage(string);
        }
    }

    @Override
    public Server getServer() {
        return this.commandSender.getServer();
    }

    @Override
    public String getName() {
        return this.commandSender.getName();
    }

    @NotNull
    @Override
    public Spigot spigot() {
        return this.commandSender.spigot();
    }

    @Override
    public boolean isPermissionSet(String s) {
        return this.commandSender.isPermissionSet(s);
    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        return this.commandSender.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(String s) {
        if (s.isEmpty()) {
            return true;
        }
        return this.commandSender.hasPermission(s);
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return this.commandSender.hasPermission(permission);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        return this.commandSender.addAttachment(plugin, s, b);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return this.commandSender.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        return this.commandSender.addAttachment(plugin, s, b, i);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return this.commandSender.addAttachment(plugin, i);
    }

    @Override
    public void removeAttachment(PermissionAttachment permissionAttachment) {
        this.commandSender.removeAttachment(permissionAttachment);
    }

    @Override
    public void recalculatePermissions() {
        this.commandSender.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return this.commandSender.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return this.commandSender.isOp();
    }

    @Override
    public void setOp(boolean b) {
        this.commandSender.setOp(b);
    }
}
