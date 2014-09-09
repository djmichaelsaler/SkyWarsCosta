/*  1:   */ package me.minebuilders.hg.tasks;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ import me.minebuilders.hg.Bound;
/*  6:   */ import me.minebuilders.hg.ChestDrop;
/*  7:   */ import me.minebuilders.hg.Config;
/*  8:   */ import me.minebuilders.hg.Game;
/*  9:   */ import me.minebuilders.hg.HG;
/* 10:   */ import me.minebuilders.hg.Util;
/* 11:   */ import org.bukkit.Bukkit;
/* 12:   */ import org.bukkit.Location;
/* 13:   */ import org.bukkit.Server;
/* 14:   */ import org.bukkit.World;
/* 15:   */ import org.bukkit.entity.FallingBlock;
/* 16:   */ import org.bukkit.entity.Player;
/* 17:   */ import org.bukkit.scheduler.BukkitScheduler;
/* 18:   */ 
/* 19:   */ public class ChestDropTask
/* 20:   */   implements Runnable
/* 21:   */ {
/* 22:   */   private Game g;
/* 23:   */   private int timerID;
/* 24:22 */   private List<ChestDrop> chests = new ArrayList();
/* 25:   */   
/* 26:   */   public ChestDropTask(Game g)
/* 27:   */   {
/* 28:25 */     this.g = g;
/* 29:26 */     this.timerID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(HG.plugin, this, Config.randomChestInterval, Config.randomChestInterval);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void run()
/* 33:   */   {
/* 34:31 */     Integer[] i = this.g.getRegion().getRandomLocs();
/* 35:   */     
/* 36:33 */     int x = i[0].intValue();
/* 37:34 */     int y = i[1].intValue();
/* 38:35 */     int z = i[2].intValue();
/* 39:36 */     World w = this.g.getRegion().getWorld();
/* 40:38 */     while (w.getBlockTypeIdAt(x, y, z) == 0)
/* 41:   */     {
/* 42:39 */       y--;
/* 43:41 */       if (y <= 0)
/* 44:   */       {
/* 45:42 */         i = this.g.getRegion().getRandomLocs();
/* 46:   */         
/* 47:44 */         x = i[0].intValue();
/* 48:45 */         y = i[1].intValue();
/* 49:46 */         z = i[2].intValue();
/* 50:   */       }
/* 51:   */     }
/* 52:50 */     y += 10;
/* 53:   */     
/* 54:52 */     Location l = new Location(w, x, y, z);
/* 55:   */     
/* 56:54 */     FallingBlock fb = l.getWorld().spawnFallingBlock(l, 33, (byte)6);
/* 57:   */     
/* 58:56 */     this.chests.add(new ChestDrop(fb));
/* 59:58 */     for (String s : this.g.getPlayers())
/* 60:   */     {
/* 61:59 */       Player p = Bukkit.getPlayer(s);
/* 62:60 */       if (p != null)
/* 63:   */       {
/* 64:61 */         Util.scm(p, "&6*&b&m                                                                             &6*");
/* 65:62 */         Util.scm(p, "&b| &3A Care-Package was just dropped near: &f" + x + "&3, &f" + y + "&3, &f" + z);
/* 66:63 */         Util.scm(p, "&6*&b&m                                                                             &6*");
/* 67:   */       }
/* 68:   */     }
/* 69:   */   }
/* 70:   */   
/* 71:   */   public void shutdown()
/* 72:   */   {
/* 73:69 */     Bukkit.getScheduler().cancelTask(this.timerID);
/* 74:70 */     for (ChestDrop cd : this.chests) {
/* 75:71 */       if (cd != null) {
/* 76:71 */         cd.remove();
/* 77:   */       }
/* 78:   */     }
/* 79:   */   }
/* 80:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.tasks.ChestDropTask
 * JD-Core Version:    0.7.0.1
 */