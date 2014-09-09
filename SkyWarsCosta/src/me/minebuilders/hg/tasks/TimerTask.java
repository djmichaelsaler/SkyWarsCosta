/*  1:   */ package me.minebuilders.hg.tasks;
/*  2:   */ 
/*  3:   */ import me.minebuilders.hg.Game;
/*  4:   */ import me.minebuilders.hg.HG;
/*  5:   */ import me.minebuilders.hg.Status;
/*  6:   */ import org.bukkit.Bukkit;
/*  7:   */ import org.bukkit.ChatColor;
/*  8:   */ import org.bukkit.configuration.file.FileConfiguration;
/*  9:   */ import org.bukkit.scheduler.BukkitScheduler;
/* 10:   */ 
/* 11:   */ public class TimerTask
/* 12:   */   implements Runnable
/* 13:   */ {
/* 14:   */   private int remainingtime;
/* 15:   */   private int id;
/* 16:   */   private Game game;
/* 17:   */   
/* 18:   */   public TimerTask(Game g, int time)
/* 19:   */   {
/* 20:17 */     this.remainingtime = time;
/* 21:18 */     this.game = g;
/* 22:   */     
/* 23:20 */     this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(HG.plugin, this, 600L, 600L);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void run()
/* 27:   */   {
/* 28:25 */     if ((this.game == null) || (this.game.getStatus() != Status.RUNNING)) {
/* 29:25 */       stop();
/* 30:   */     }
/* 31:27 */     this.remainingtime -= 30;
/* 32:29 */     if ((this.remainingtime == 30) && (HG.plugin.getConfig().getBoolean("settings.teleport-at-end")))
/* 33:   */     {
/* 34:30 */       this.game.msgAll("&l&cThe game is almost over, fight to the death!");
/* 35:31 */       this.game.respawnAll();
/* 36:   */     }
/* 37:32 */     else if (this.remainingtime < 10)
/* 38:   */     {
/* 39:33 */       stop();
/* 40:34 */       this.game.stop();
/* 41:   */     }
/* 42:   */     else
/* 43:   */     {
/* 44:36 */       int minutes = this.remainingtime / 60;
/* 45:37 */       int asd = Integer.valueOf(this.remainingtime % 60).intValue();
/* 46:38 */       if (minutes != 0) {
/* 47:38 */         this.game.msgAll(ChatColor.GREEN + "The game is ending in " + minutes + (asd == 0 ? " minute(s)!" : new StringBuilder(" minute(s), and ").append(asd).append(" seconds!").toString()));
/* 48:   */       } else {
/* 49:39 */         this.game.msgAll(ChatColor.GREEN + "The game is ending in " + this.remainingtime + " seconds!");
/* 50:   */       }
/* 51:   */     }
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void stop()
/* 55:   */   {
/* 56:44 */     Bukkit.getScheduler().cancelTask(this.id);
/* 57:   */   }
/* 58:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.tasks.TimerTask
 * JD-Core Version:    0.7.0.1
 */