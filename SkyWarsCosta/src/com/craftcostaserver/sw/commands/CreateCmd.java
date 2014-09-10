/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import java.util.List;

/* 11:   */ import org.bukkit.ChatColor;
/* 12:   */ import org.bukkit.Location;
/* 13:   */ import org.bukkit.World;
/* 14:   */ import org.bukkit.configuration.Configuration;
/* 15:   */ import org.bukkit.configuration.file.FileConfiguration;
/* 16:   */ import org.bukkit.entity.Player;

import com.craftcostaserver.sw.Bound;
import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.PlayerSession;
import com.craftcostaserver.sw.Util;
import com.craftcostaserver.sw.data.Data;
/* 17:   */ 
/* 18:   */ public class CreateCmd
/* 19:   */   extends BaseCmd
/* 20:   */ {
/* 21:   */   public CreateCmd()
/* 22:   */   {
/* 23:17 */     this.forcePlayer = true;
/* 24:18 */     this.cmdName = "create";
/* 25:19 */     this.argLength = 5;
/* 26:20 */     this.usage = "<&cname&b> <&cmin-player&b> <&cmax-player&b> <&ctime&b>";
/* 27:   */   }
/* 28:   */   
/* 29:   */   public boolean run()
/* 30:   */   {
/* 31:25 */     if (!HG.plugin.playerses.containsKey(this.player.getName()))
/* 32:   */     {
/* 33:26 */       Util.msg(this.player, ChatColor.RED + "You need to make a selection before making an arena!");
/* 34:   */     }
/* 35:   */     else
/* 36:   */     {
/* 37:28 */       PlayerSession s = (PlayerSession)HG.plugin.playerses.get(this.player.getName());
/* 38:29 */       if (!s.hasValidSelection())
/* 39:   */       {
/* 40:30 */         Util.msg(this.player, ChatColor.RED + "You need to make a selection before making an arena!");
/* 41:   */       }
/* 42:32 */       else if ((!Util.isInt(this.args[2])) || (!Util.isInt(this.args[3])) || (!Util.isInt(this.args[4])))
/* 43:   */       {
/* 44:33 */         this.player.sendMessage(ChatColor.RED + "Wrong usage: " + sendHelpLine());
/* 45:   */       }
/* 46:   */       else
/* 47:   */       {
/* 48:34 */         if (Integer.parseInt(this.args[4]) % 30 != 0)
/* 49:   */         {
/* 50:35 */           this.player.sendMessage(ChatColor.RED + "time-in-seconds must be divisible by 30!");
/* 51:36 */           this.player.sendMessage(ChatColor.RED + "Ex: 90 is divisible by 30");
/* 52:37 */           return true;
/* 53:   */         }
/* 54:38 */         if (Integer.parseInt(this.args[2]) > Integer.parseInt(this.args[3]))
/* 55:   */         {
/* 56:39 */           this.player.sendMessage(ChatColor.RED + "min-players cannot be more then max-players!");
/* 57:40 */           sendHelpLine();
/* 58:   */         }
/* 59:   */         else
/* 60:   */         {
/* 61:42 */           Location l = s.getLoc1();
/* 62:43 */           Location l2 = s.getLoc2();
/* 63:44 */           int freeroam = HG.plugin.getConfig().getInt("settings.free-roam");
/* 64:45 */           Configuration c = HG.arenaconfig.getCustomConfig();
/* 65:46 */           c.set("arenas." + this.args[1] + ".bound.world", this.player.getWorld().getName());
/* 66:47 */           c.set("arenas." + this.args[1] + ".bound.x", Integer.valueOf(l.getBlockX()));
/* 67:48 */           c.set("arenas." + this.args[1] + ".bound.y", Integer.valueOf(l.getBlockY()));
/* 68:49 */           c.set("arenas." + this.args[1] + ".bound.z", Integer.valueOf(l.getBlockZ()));
/* 69:50 */           c.set("arenas." + this.args[1] + ".bound.x2", Integer.valueOf(l2.getBlockX()));
/* 70:51 */           c.set("arenas." + this.args[1] + ".bound.y2", Integer.valueOf(l2.getBlockY()));
/* 71:52 */           c.set("arenas." + this.args[1] + ".bound.z2", Integer.valueOf(l2.getBlockZ()));
/* 72:53 */           c.set("arenas." + this.args[1] + ".info." + "timer", Integer.valueOf(Integer.parseInt(this.args[4])));
/* 73:54 */           c.set("arenas." + this.args[1] + ".info." + "min-players", Integer.valueOf(Integer.parseInt(this.args[2])));
/* 74:55 */           c.set("arenas." + this.args[1] + ".info." + "max-players", Integer.valueOf(Integer.parseInt(this.args[3])));
/* 75:56 */           HG.arenaconfig.saveCustomConfig();
/* 76:57 */           HG.arenaconfig.reloadCustomConfig();
/* 77:   */           
/* 78:59 */           Bound b = new Bound(this.player.getWorld().getName(), l.getBlockX(), l.getBlockY(), l.getBlockZ(), l2.getBlockX(), l2.getBlockY(), l2.getBlockZ());
/* 79:60 */           HG.plugin.games.add(new Game(this.args[1], b, Integer.parseInt(this.args[4]), Integer.parseInt(this.args[2]), Integer.parseInt(this.args[3]), freeroam));
/* 80:61 */           Util.msg(this.player, ChatColor.GREEN + "You created SkyWars arena " + this.args[1] + "!");
/* 81:62 */           return true;
/* 82:   */         }
/* 83:   */       }
/* 84:   */     }
/* 85:66 */     return true;
/* 86:   */   }
/* 87:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.CreateCmd
 * JD-Core Version:    0.7.0.1
 */