/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;

/*  7:   */ import org.bukkit.ChatColor;
/*  8:   */ import org.bukkit.command.CommandSender;
/*  9:   */ import org.bukkit.entity.Player;

import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.Util;
import com.craftcostaserver.sw.managers.Manager;
/* 10:   */ 
/* 11:   */ public abstract class BaseCmd
/* 12:   */ {
/* 13:   */   public CommandSender sender;
/* 14:   */   public String[] args;
/* 15:   */   public String cmdName;
/* 16:15 */   public int argLength = 0;
/* 17:16 */   public boolean forcePlayer = true;
/* 18:17 */   public boolean forceInGame = false;
/* 19:18 */   public boolean forceInRegion = false;
/* 20:19 */   public String usage = "";
/* 21:   */   public Player player;
/* 22:   */   
/* 23:   */   public boolean processCmd(HG p, CommandSender s, String[] arg)
/* 24:   */   {
/* 25:23 */     this.sender = s;
/* 26:24 */     this.args = arg;
/* 27:26 */     if (this.forcePlayer)
/* 28:   */     {
/* 29:27 */       if (!(s instanceof Player)) {
/* 30:27 */         return false;
/* 31:   */       }
/* 32:28 */       this.player = ((Player)s);
/* 33:   */     }
/* 34:30 */     if (!s.hasPermission("sw." + this.cmdName)) {
/* 35:31 */       this.sender.sendMessage(ChatColor.RED + "You do not have permission to use: " + ChatColor.GOLD + "/sw " + this.cmdName);
/* 36:32 */     } else if ((this.forceInGame) && (!HG.plugin.players.containsKey(this.player.getName()))) {
/* 37:33 */       this.sender.sendMessage(ChatColor.RED + "Your not in a valid game!");
/* 38:34 */     } else if ((this.forceInRegion) && (!HG.manager.isInRegion(this.player.getLocation()))) {
/* 39:35 */       this.sender.sendMessage(ChatColor.RED + "Your not in a valid SkyWars region!");
/* 40:36 */     } else if (this.argLength > arg.length) {
/* 41:37 */       Util.scm(s, "&4Wrong usage: " + sendHelpLine());
/* 42:   */     } else {
/* 43:38 */       return run();
/* 44:   */     }
/* 45:39 */     return true;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public abstract boolean run();
/* 49:   */   
/* 50:   */   public String sendHelpLine()
/* 51:   */   {
/* 52:45 */     return "&c/&3sw " + this.cmdName + " &b" + this.usage;
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.BaseCmd
 * JD-Core Version:    0.7.0.1
 */