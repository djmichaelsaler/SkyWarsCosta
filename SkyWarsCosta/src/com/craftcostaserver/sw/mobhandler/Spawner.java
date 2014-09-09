/*   1:    */ package com.craftcostaserver.sw.mobhandler;
/*   2:    */ 
/*   3:    */ import java.util.Random;

/*   6:    */ import org.bukkit.Bukkit;
/*   7:    */ import org.bukkit.Location;
/*   8:    */ import org.bukkit.Material;
/*   9:    */ import org.bukkit.World;
/*  10:    */ import org.bukkit.entity.EntityType;
/*  11:    */ import org.bukkit.entity.Player;
/*  12:    */ import org.bukkit.scheduler.BukkitScheduler;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
/*  13:    */ 
/*  14:    */ public class Spawner
/*  15:    */   implements Runnable
/*  16:    */ {
/*  17:    */   private Game g;
/*  18:    */   private int id;
/*  19: 19 */   private Random rg = new Random();
/*  20:    */   
/*  21:    */   public Spawner(Game game, int i)
/*  22:    */   {
/*  23: 22 */     this.g = game;
/*  24: 23 */     this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(HG.plugin, this, i, i);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void run()
/*  28:    */   {
/*  29: 28 */     for (String s : this.g.getPlayers())
/*  30:    */     {
/*  31: 29 */       Player p = Bukkit.getPlayer(s);
/*  32: 30 */       if (p != null)
/*  33:    */       {
/*  34: 31 */         Location l = p.getLocation();
/*  35: 32 */         World w = l.getWorld();
/*  36: 33 */         int x = l.getBlockX();
/*  37: 34 */         int z = l.getBlockZ();
/*  38: 35 */         int y = l.getBlockY();
/*  39:    */         
/*  40: 37 */         int ran1 = getRandomNumber();
/*  41: 38 */         int ran2 = getRandomNumber();
/*  42:    */         
/*  43: 40 */         x += ran1;
/*  44: 41 */         z += ran2;
/*  45:    */         
/*  46: 43 */         l = getSafeLoc(w, x, y, z);
/*  47: 45 */         if ((l != null) && (this.g.isInRegion(l))) {
/*  48: 46 */           w.spawnEntity(l, pickRandomMob(!isDay(w), this.rg.nextInt(10)));
/*  49:    */         }
/*  50:    */       }
/*  51:    */     }
/*  52:    */   }
/*  53:    */   
/*  54:    */   public boolean isDay(World w)
/*  55:    */   {
/*  56: 52 */     long time = w.getTime();
/*  57: 53 */     return (time < 12300L) || (time > 23850L);
/*  58:    */   }
/*  59:    */   
/*  60:    */   private EntityType pickRandomMob(boolean isNight, int x)
/*  61:    */   {
/*  62: 57 */     if (isNight)
/*  63:    */     {
/*  64: 58 */       if (x < 5) {
/*  65: 59 */         return EntityType.ZOMBIE;
/*  66:    */       }
/*  67: 60 */       if (x < 8) {
/*  68: 61 */         return EntityType.SKELETON;
/*  69:    */       }
/*  70: 63 */       return EntityType.CREEPER;
/*  71:    */     }
/*  72: 65 */     if (x < 3) {
/*  73: 66 */       return EntityType.COW;
/*  74:    */     }
/*  75: 67 */     if (x < 6) {
/*  76: 68 */       return EntityType.PIG;
/*  77:    */     }
/*  78: 69 */     if (x < 7) {
/*  79: 70 */       return EntityType.CHICKEN;
/*  80:    */     }
/*  81: 71 */     if (x < 8) {
/*  82: 72 */       return EntityType.SHEEP;
/*  83:    */     }
/*  84: 73 */     if (x < 9) {
/*  85: 74 */       return EntityType.SPIDER;
/*  86:    */     }
/*  87: 76 */     return EntityType.CREEPER;
/*  88:    */   }
/*  89:    */   
/*  90:    */   private int getRandomNumber()
/*  91:    */   {
/*  92: 81 */     int r = this.rg.nextInt(25) - this.rg.nextInt(25);
/*  93: 82 */     if ((r <= 6) && (r >= -6)) {
/*  94: 83 */       return getRandomNumber();
/*  95:    */     }
/*  96: 85 */     return r;
/*  97:    */   }
/*  98:    */   
/*  99:    */   private Location getSafeLoc(World w, int x, int y, int z)
/* 100:    */   {
/* 101: 90 */     int trys = 30;
/* 102: 92 */     while (trys > 0)
/* 103:    */     {
/* 104: 94 */       trys--;
/* 105:    */       
/* 106: 96 */       Material m = Material.getMaterial(w.getBlockTypeIdAt(x, y, z));
/* 107: 98 */       if (m.isSolid())
/* 108:    */       {
/* 109: 99 */         y++;
/* 110:    */       }
/* 111:100 */       else if (w.getBlockTypeIdAt(x, y - 1, z) == 0)
/* 112:    */       {
/* 113:101 */         y--;
/* 114:    */       }
/* 115:102 */       else if (Material.getMaterial(w.getBlockTypeIdAt(x, y + 1, z)).isSolid())
/* 116:    */       {
/* 117:103 */         int ran1 = getRandomNumber();
/* 118:104 */         int ran2 = getRandomNumber();
/* 119:    */         
/* 120:106 */         x += ran1;
/* 121:107 */         z += ran2;
/* 122:    */       }
/* 123:    */       else
/* 124:    */       {
/* 125:109 */         return new Location(w, x, y, z);
/* 126:    */       }
/* 127:    */     }
/* 128:112 */     return null;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public void stop()
/* 132:    */   {
/* 133:116 */     Bukkit.getScheduler().cancelTask(this.id);
/* 134:    */   }
/* 135:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.mobhandler.Spawner
 * JD-Core Version:    0.7.0.1
 */