/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import java.util.List;

/*  9:   */ import org.bukkit.ChatColor;
/* 10:   */ import org.bukkit.Location;
/* 11:   */ import org.bukkit.World;
/* 12:   */ import org.bukkit.configuration.Configuration;
/* 13:   */ import org.bukkit.entity.Player;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.Util;
import com.craftcostaserver.sw.data.Data;
import com.craftcostaserver.sw.managers.Manager;
/* 14:   */ 
/* 15:   */ public class AddSpawnCmd
/* 16:   */   extends BaseCmd
/* 17:   */ {
/* 18:   */   public AddSpawnCmd()
/* 19:   */   {
/* 20:16 */     this.forcePlayer = true;
/* 21:17 */     this.cmdName = "addspawn";
/* 22:18 */     this.argLength = 1;
/* 23:19 */     this.forceInRegion = true;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public boolean run()
/* 27:   */   {
/* 28:24 */     Game g = HG.manager.getGame(this.player.getLocation());
/* 29:25 */     int num = g.getSpawns().size() + 1;
/* 30:26 */     Configuration c = HG.arenaconfig.getCustomConfig();
/* 31:27 */     List<String> d = c.getStringList("arenas." + g.getName() + ".spawns");
/* 32:28 */     Location l = this.player.getLocation();
/* 33:29 */     for (Location lb : g.getSpawns()) {
/* 34:30 */       if (lb.getBlock().equals(l.getBlock()))
/* 35:   */       {
/* 36:31 */         this.player.sendMessage(ChatColor.RED + "You cannot have two spawns in the same location!");
/* 37:32 */         return true;
/* 38:   */       }
/* 39:   */     }
/* 40:35 */     d.add(l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ() + ":" + l.getYaw() + ":" + l.getPitch());
/* 41:36 */     c.set("arenas." + g.getName() + ".spawns", d);
/* 42:37 */     g.addSpawn(l);
/* 43:38 */     HG.arenaconfig.saveCustomConfig();
/* 44:39 */     Util.msg(this.player, "You set HungerGames spawn #" + num + "!");
/* 45:   */     
/* 46:41 */     HG.manager.checkGame(g, this.player);
/* 47:42 */     return true;
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.AddSpawnCmd
 * JD-Core Version:    0.7.0.1
 */