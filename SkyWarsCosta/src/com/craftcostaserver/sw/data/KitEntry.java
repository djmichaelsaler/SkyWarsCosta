/*  1:   */ package com.craftcostaserver.sw.data;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.HashMap;

/*  9:   */ import org.bukkit.entity.Player;
/* 10:   */ import org.bukkit.inventory.ItemStack;
/* 11:   */ import org.bukkit.inventory.PlayerInventory;
/* 12:   */ import org.bukkit.potion.PotionEffect;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.PlayerData;
import com.craftcostaserver.sw.Util;
/* 13:   */ 
/* 14:   */ public class KitEntry
/* 15:   */ {
/* 16:   */   private ItemStack helm;
/* 17:   */   private String perm;
/* 18:   */   private ItemStack boots;
/* 19:   */   private ItemStack chestplate;
/* 20:   */   private ItemStack pants;
/* 21:   */   private ItemStack[] inventoryContents;
/* 22:   */   private ArrayList<PotionEffect> posions;
/* 23:   */   
/* 24:   */   public KitEntry(ItemStack[] ic, ItemStack h, ItemStack b, ItemStack c, ItemStack p, String per, ArrayList<PotionEffect> po)
/* 25:   */   {
/* 26:23 */     this.inventoryContents = ic;
/* 27:24 */     this.helm = h;
/* 28:25 */     this.boots = b;
/* 29:26 */     this.chestplate = c;
/* 30:27 */     this.pants = p;
/* 31:28 */     this.perm = per;
/* 32:29 */     this.posions = po;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean hasKitPermission(Player p)
/* 36:   */   {
/* 37:33 */     if ((this.perm != null) && (!p.hasPermission(this.perm))) {
/* 38:34 */       return false;
/* 39:   */     }
/* 40:36 */     return true;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void setInventoryContent(Player p)
/* 44:   */   {
/* 45:41 */     Util.clearInv(p);
/* 46:42 */     p.getInventory().setHelmet(this.helm);
/* 47:43 */     p.getInventory().setChestplate(this.chestplate);
/* 48:44 */     p.getInventory().setLeggings(this.pants);
/* 49:45 */     p.getInventory().setBoots(this.boots);
/* 50:46 */     p.getInventory().setContents(this.inventoryContents);
/* 51:48 */     for (PotionEffect effect : p.getActivePotionEffects()) {
/* 52:49 */       p.removePotionEffect(effect.getType());
/* 53:   */     }
/* 54:51 */     p.addPotionEffects(this.posions);
/* 55:52 */     ((PlayerData)HG.plugin.players.get(p.getName())).getGame().freeze(p);
/* 56:53 */     p.updateInventory();
/* 57:   */   }
/* 58:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.data.KitEntry
 * JD-Core Version:    0.7.0.1
 */