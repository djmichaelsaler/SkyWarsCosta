/*  1:   */ package com.craftcostaserver.sw.tasks;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import com.craftcostaserver.sw.Game;
/*  5:   */ import com.craftcostaserver.sw.HG;
/*  6:   */ import com.craftcostaserver.sw.PlayerData;
/*  7:   */ import org.bukkit.Bukkit;
/*  8:   */ import org.bukkit.ChatColor;
/*  9:   */ import org.bukkit.Location;
/* 10:   */ import org.bukkit.Material;
/* 11:   */ import org.bukkit.entity.Player;
/* 12:   */ import org.bukkit.inventory.ItemStack;
/* 13:   */ import org.bukkit.inventory.PlayerInventory;
/* 14:   */ import org.bukkit.inventory.meta.ItemMeta;
/* 15:   */ import org.bukkit.scheduler.BukkitScheduler;
/* 16:   */ 
/* 17:   */ public class CompassTask
/* 18:   */   implements Runnable
/* 19:   */ {
/* 20:   */   private HG plugin;
/* 21:   */   
/* 22:   */   public CompassTask(HG plugin)
/* 23:   */   {
/* 24:20 */     this.plugin = plugin;
/* 25:21 */     Bukkit.getScheduler().scheduleSyncRepeatingTask(HG.plugin, this, 25L, 25L);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void run()
/* 29:   */   {
/* 30:26 */     for (Player p : ) {
/* 31:28 */       if (p.getInventory().contains(Material.COMPASS))
/* 32:   */       {
/* 33:29 */         PlayerData pd = (PlayerData)this.plugin.players.get(p.getName());
/* 34:31 */         if (pd != null)
/* 35:   */         {
/* 36:33 */           String[] st = getNearestPlayer(p, pd);
/* 37:34 */           String info = "" + ChatColor.WHITE + ChatColor.BOLD + "Nearest Player: " + ChatColor.RED + st[0] + "    " + ChatColor.WHITE + ChatColor.BOLD + "Distance: " + ChatColor.RED + st[1];
/* 38:36 */           for (ItemStack it : p.getInventory()) {
/* 39:37 */             if ((it != null) && (it.getType() == Material.COMPASS))
/* 40:   */             {
/* 41:38 */               ItemMeta im = it.getItemMeta();
/* 42:39 */               im.setDisplayName(info);
/* 43:40 */               it.setItemMeta(im);
/* 44:   */             }
/* 45:   */           }
/* 46:   */         }
/* 47:   */       }
/* 48:   */     }
/* 49:   */   }
/* 50:   */   
/* 51:   */   private int cal(int i)
/* 52:   */   {
/* 53:50 */     if (i < 0) {
/* 54:51 */       return -i;
/* 55:   */     }
/* 56:53 */     return i;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public String[] getNearestPlayer(Player p, PlayerData pd)
/* 60:   */   {
/* 61:58 */     Game g = pd.getGame();
/* 62:   */     
/* 63:60 */     int x = p.getLocation().getBlockX();
/* 64:61 */     int y = p.getLocation().getBlockY();
/* 65:62 */     int z = p.getLocation().getBlockZ();
/* 66:   */     
/* 67:64 */     int i = 200000;
/* 68:   */     
/* 69:66 */     Player player = null;
/* 70:68 */     for (String s : g.getPlayers())
/* 71:   */     {
/* 72:70 */       Player p2 = Bukkit.getPlayer(s);
/* 73:72 */       if ((p2 != null) && (!p2.equals(p)) && (!pd.isOnTeam(s)))
/* 74:   */       {
/* 75:74 */         Location l = p2.getLocation();
/* 76:   */         
/* 77:76 */         int c = cal((int)(x - l.getX())) + cal((int)(y - l.getY())) + cal((int)(z - l.getZ()));
/* 78:78 */         if (i > c)
/* 79:   */         {
/* 80:79 */           player = p2;
/* 81:80 */           i = c;
/* 82:   */         }
/* 83:   */       }
/* 84:   */     }
/* 85:84 */     if (player != null) {
/* 86:84 */       p.setCompassTarget(player.getLocation());
/* 87:   */     }
/* 88:86 */     return new String[] { player == null ? "none" : player.getName(), String.valueOf(i) };
/* 89:   */   }
/* 90:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.tasks.CompassTask
 * JD-Core Version:    0.7.0.1
 */