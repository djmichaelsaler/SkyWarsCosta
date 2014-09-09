/*  1:   */ package com.craftcostaserver.sw;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.Random;
/*  5:   */ import org.bukkit.Bukkit;
/*  6:   */ import org.bukkit.Location;
/*  7:   */ import org.bukkit.Material;
/*  8:   */ import org.bukkit.World;
/*  9:   */ import org.bukkit.block.Block;
/* 10:   */ import org.bukkit.entity.Entity;
/* 11:   */ import org.bukkit.entity.Player;
/* 12:   */ 
/* 13:   */ public class Bound
/* 14:   */ {
/* 15:   */   private int x;
/* 16:   */   private int y;
/* 17:   */   private int z;
/* 18:   */   private int x2;
/* 19:   */   private int y2;
/* 20:   */   private int z2;
/* 21:   */   private String world;
/* 22:   */   
/* 23:   */   public Bound(String world, int x, int y, int z, int x2, int y2, int z2)
/* 24:   */   {
/* 25:25 */     this.world = world;
/* 26:26 */     this.x = Math.min(x, x2);
/* 27:27 */     this.y = Math.min(y, y2);
/* 28:28 */     this.z = Math.min(z, z2);
/* 29:29 */     this.x2 = Math.max(x, x2);
/* 30:30 */     this.y2 = Math.max(y, y2);
/* 31:31 */     this.z2 = Math.max(z, z2);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public Integer[] getRandomLocs()
/* 35:   */   {
/* 36:35 */     Random r = new Random();
/* 37:36 */     return new Integer[] { Integer.valueOf(r.nextInt(this.x2 - this.x + 1) + this.x), Integer.valueOf(this.y2), Integer.valueOf(r.nextInt(this.z2 - this.z + 1) + this.z) };
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean isInRegion(Location loc)
/* 41:   */   {
/* 42:40 */     if (!loc.getWorld().getName().equals(this.world)) {
/* 43:40 */       return false;
/* 44:   */     }
/* 45:41 */     int cx = loc.getBlockX();
/* 46:42 */     int cy = loc.getBlockY();
/* 47:43 */     int cz = loc.getBlockZ();
/* 48:44 */     if ((cx >= this.x) && (cx <= this.x2) && (cy >= this.y) && (cy <= this.y2) && (cz >= this.z) && (cz <= this.z2)) {
/* 49:45 */       return true;
/* 50:   */     }
/* 51:47 */     return false;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void removeEntities()
/* 55:   */   {
/* 56:51 */     for (Entity e : Bukkit.getWorld(this.world).getEntities()) {
/* 57:52 */       if ((isInRegion(e.getLocation())) && (!(e instanceof Player))) {
/* 58:53 */         e.remove();
/* 59:   */       }
/* 60:   */     }
/* 61:   */   }
/* 62:   */   
/* 63:   */   public ArrayList<Location> getBlocks(Material type)
/* 64:   */   {
/* 65:59 */     World w = Bukkit.getWorld(this.world);
/* 66:60 */     ArrayList<Location> array = new ArrayList();
/* 67:61 */     for (int x3 = this.x; x3 <= this.x2; x3++) {
/* 68:62 */       for (int y3 = this.y; y3 <= this.y2; y3++) {
/* 69:63 */         for (int z3 = this.z; z3 <= this.z2; z3++)
/* 70:   */         {
/* 71:64 */           Block b = w.getBlockAt(x3, y3, z3);
/* 72:65 */           if (b.getType() == type) {
/* 73:66 */             array.add(b.getLocation());
/* 74:   */           }
/* 75:   */         }
/* 76:   */       }
/* 77:   */     }
/* 78:71 */     return array;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public World getWorld()
/* 82:   */   {
/* 83:75 */     return Bukkit.getWorld(this.world);
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.Bound
 * JD-Core Version:    0.7.0.1
 */