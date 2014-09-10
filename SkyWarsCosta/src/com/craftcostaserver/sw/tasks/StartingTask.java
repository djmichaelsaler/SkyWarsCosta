/*  1:   */ package com.craftcostaserver.sw.tasks;
/*  2:   */ 
/*  3:   */ /*  6:   */ import org.bukkit.Bukkit;
/*  7:   */ import org.bukkit.scheduler.BukkitScheduler;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.Util;
/*  8:   */ 
/*  9:   */ public class StartingTask
/* 10:   */   implements Runnable
/* 11:   */ {
/* 12:   */   private int timer;
/* 13:   */   private int id;
/* 14:   */   private Game game;
/* 15:   */   
/* 16:   */   public StartingTask(Game g)
/* 17:   */   {
/* 18:16 */     this.timer = 30;
/* 19:17 */     this.game = g;
/* 20:18 */     Util.broadcast("&b&l Arena " + g.getName() + " will begin in 30 seconds!");
/* 21:19 */     Util.broadcast("&b&l Use:&3&l /sw join " + g.getName() + "&b&l to join!");
/* 22:   */     
/* 23:21 */     this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(HG.plugin, this, 100L, 100L);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void run()
/* 27:   */   {
/* 28:26 */     this.timer -= 5;
/* 29:28 */     if (this.timer <= 0)
/* 30:   */     {
/* 31:29 */       stop();
/* 32:30 */       this.game.startFreeRoam();
/* 33:   */     }
/* 34:   */     else
/* 35:   */     {
/* 36:32 */       this.game.msgAll("The game will start in " + this.timer + " seconds..");
/* 37:   */     }
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void stop()
/* 41:   */   {
/* 42:37 */     Bukkit.getScheduler().cancelTask(this.id);
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.tasks.StartingTask
 * JD-Core Version:    0.7.0.1
 */