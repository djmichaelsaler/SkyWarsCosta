/*  1:   */ package com.craftcostaserver.sw;
/*  2:   */ 
/*  3:   */ import java.util.Iterator;
/*  4:   */ import java.util.List;
/*  5:   */ import org.bukkit.Bukkit;
/*  6:   */ import org.bukkit.Server;
/*  7:   */ import org.bukkit.block.BlockState;
/*  8:   */ import org.bukkit.scheduler.BukkitScheduler;
/*  9:   */ 
/* 10:   */ public class Rollback
/* 11:   */   implements Runnable
/* 12:   */ {
/* 13:   */   private final Iterator<BlockState> session;
/* 14:   */   private Game g;
/* 15:   */   private int timerID;
/* 16:   */   
/* 17:   */   public Rollback(Game g)
/* 18:   */   {
/* 19:15 */     this.g = g;
/* 20:16 */     g.setStatus(Status.ROLLBACK);
/* 21:17 */     this.session = g.getBlocks().iterator();
/* 22:18 */     this.timerID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(HG.plugin, this, 1L, 2L);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void run()
/* 26:   */   {
/* 27:22 */     int i = 0;
/* 28:23 */     while ((i < 50) && (this.session.hasNext()))
/* 29:   */     {
/* 30:24 */       i++;
/* 31:25 */       ((BlockState)this.session.next()).update(true);
/* 32:   */     }
/* 33:27 */     if (!this.session.hasNext())
/* 34:   */     {
/* 35:28 */       Bukkit.getServer().getScheduler().cancelTask(this.timerID);
/* 36:29 */       this.g.resetBlocks();
/* 37:30 */       this.g.setStatus(Status.STOPPED);
/* 38:   */     }
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.Rollback
 * JD-Core Version:    0.7.0.1
 */