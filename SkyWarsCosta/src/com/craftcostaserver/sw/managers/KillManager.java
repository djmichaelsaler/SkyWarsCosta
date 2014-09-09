/*  1:   */ package com.craftcostaserver.sw.managers;
/*  2:   */ 
/*  3:   */ import org.bukkit.Material;
/*  4:   */ import org.bukkit.entity.Entity;
/*  5:   */ import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
/*  6:   */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*  7:   */ import org.bukkit.inventory.ItemStack;
/*  8:   */ 
/*  9:   */ public class KillManager
/* 10:   */ {
/* 11:   */   public String getDeathString(EntityDamageEvent.DamageCause dc, String name)
/* 12:   */   {
/* 13:10 */     switch (dc)
/* 14:   */     {
/* 15:   */     case LIGHTNING: 
/* 16:11 */       return name + " Was blown to bits!";
/* 17:   */     case LAVA: 
/* 18:12 */       return name + " Was blown to bits!";
/* 19:   */     case WITHER: 
/* 20:13 */       return name + " Was killed by an unknown cause!";
/* 21:   */     case ENTITY_ATTACK: 
/* 22:14 */       return name + " Hit the ground too hard!";
/* 23:   */     case THORNS: 
/* 24:15 */       return name + " Was smashed by a block!";
/* 25:   */     case ENTITY_EXPLOSION: 
/* 26:16 */       return name + " Was Burned alive!";
/* 27:   */     case FALL: 
/* 28:17 */       return name + " Was Burned alive!";
/* 29:   */     case CUSTOM: 
/* 30:18 */       return name + " Got hit by a projectile!";
/* 31:   */     case FIRE: 
/* 32:19 */       return name + " Fell into a pit of lava!";
/* 33:   */     case SUFFOCATION: 
/* 34:20 */       return name + " Was destroyed by magic!";
/* 35:   */     case POISON: 
/* 36:21 */       return name + " Couldn't handle hungergames!";
/* 37:   */     }
/* 38:22 */     return name + " Was killed by " + dc.toString().toLowerCase();
/* 39:   */   }
/* 40:   */   
/* 41:   */   public String getKillString(String name, Entity e)
/* 42:   */   {
/* 43:27 */     switch (e.getType())
/* 44:   */     {
/* 45:   */     case WITHER_SKULL: 
/* 46:28 */       return "&3" + name + " &b&lWas killed by &3" + ((Player)e).getName() + " &busing a(n) &3" + ((Player)e).getItemInHand().getType().name().toLowerCase() + "&b!";
/* 47:   */     case MAGMA_CUBE: 
/* 48:29 */       return name + " Was ripped to bits by a Zombie!";
/* 49:   */     case ITEM_FRAME: 
/* 50:30 */       return name + " Was shot in the face by a skeleton";
/* 51:   */     case CAVE_SPIDER: 
/* 52:31 */       return name + " Was shot in the face by a skeleton";
/* 53:   */     case LEASH_HITCH: 
/* 54:32 */       return name + " Was eaten alive by a Spider!";
/* 55:   */     }
/* 56:33 */     return name + " Spontaneously died!";
/* 57:   */   }
/* 58:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.managers.KillManager
 * JD-Core Version:    0.7.0.1
 */