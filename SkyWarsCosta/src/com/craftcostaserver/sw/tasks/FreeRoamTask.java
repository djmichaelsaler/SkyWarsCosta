/*  1:   */ package com.craftcostaserver.sw.tasks;
/*  2:   */ 
/*  3:   */ /*  6:   */ import org.bukkit.Bukkit;
/*  7:   */ import org.bukkit.entity.Player;
/*  8:   */ import org.bukkit.scheduler.BukkitScheduler;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.Util;
/*  9:   */ 
/* 10:   */ public class FreeRoamTask
/* 11:   */   implements Runnable
/* 12:   */ {
/* 13:   */   private Game game;
/* 14:   */   private int id;
/* 15:   */   
/* 16:   */   public FreeRoamTask(Game g)
/* 17:   */   {
/* 18:16 */     this.game = g;
/* 19:17 */     for (String s : g.getPlayers())
/* 20:   */     {
/* 21:18 */       Player p = Bukkit.getPlayer(s);
/* 22:19 */       if (p != null)
/* 23:   */       {
/* 24:20 */         Util.scm(p, "&4[]---------[ &6&lThe game has started! &4]---------[]");
/* 25:21 */         Util.scm(p, " &e You have " + g.getRoamTime() + " seconds to roam without taking damage!");
/* 26:22 */         p.setHealth(20.0D);
/* 27:23 */         p.setFoodLevel(20);
/* 28:24 */         g.unFreeze(p);
/* 29:   */       }
/* 30:   */     }
/* 31:27 */     this.id = Bukkit.getScheduler().scheduleSyncDelayedTask(HG.plugin, this, g.getRoamTime() * 20L);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void run()
/* 35:   */   {
/* 36:32 */     this.game.msgAll("&c&lFree-Roam is over, PVP is now enabled!");
/* 37:33 */     this.game.startGame();
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void stop()
/* 41:   */   {
/* 42:37 */     Bukkit.getScheduler().cancelTask(this.id);
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.tasks.FreeRoamTask
 * JD-Core Version:    0.7.0.1
 */