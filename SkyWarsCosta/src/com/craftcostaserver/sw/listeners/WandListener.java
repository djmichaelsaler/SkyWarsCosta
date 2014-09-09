/*  1:   */ package com.craftcostaserver.sw.listeners;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;

/*  7:   */ import org.bukkit.Location;
/*  8:   */ import org.bukkit.Material;
/*  9:   */ import org.bukkit.block.Block;
/* 10:   */ import org.bukkit.entity.Player;
/* 11:   */ import org.bukkit.event.EventHandler;
/* 12:   */ import org.bukkit.event.Listener;
/* 13:   */ import org.bukkit.event.block.Action;
/* 14:   */ import org.bukkit.event.player.PlayerInteractEvent;
/* 15:   */ import org.bukkit.inventory.ItemStack;

import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.PlayerSession;
import com.craftcostaserver.sw.Util;
/* 16:   */ 
/* 17:   */ public class WandListener
/* 18:   */   implements Listener
/* 19:   */ {
/* 20:   */   public HG plugin;
/* 21:   */   
/* 22:   */   public WandListener(HG instance)
/* 23:   */   {
/* 24:19 */     this.plugin = instance;
/* 25:   */   }
/* 26:   */   
/* 27:   */   @EventHandler
/* 28:   */   public void onSelection(PlayerInteractEvent event)
/* 29:   */   {
/* 30:24 */     Player player = event.getPlayer();
/* 31:25 */     Action action = event.getAction();
/* 32:27 */     if (action.equals(Action.RIGHT_CLICK_BLOCK))
/* 33:   */     {
/* 34:28 */       if (!player.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
/* 35:28 */         return;
/* 36:   */       }
/* 37:29 */       if (!this.plugin.playerses.containsKey(player.getName())) {
/* 38:29 */         return;
/* 39:   */       }
/* 40:30 */       Location l = event.getClickedBlock().getLocation();
/* 41:31 */       PlayerSession ses = (PlayerSession)this.plugin.playerses.get(player.getName());
/* 42:32 */       ses.setLoc2(l);
/* 43:33 */       Util.msg(player, "Pos2: " + l.getX() + ", " + l.getY() + ", " + l.getZ());
/* 44:34 */       if (!ses.hasValidSelection()) {
/* 45:35 */         Util.msg(player, "Now you need to set position 1!");
/* 46:   */       }
/* 47:37 */       event.setCancelled(true);
/* 48:   */     }
/* 49:38 */     else if (action.equals(Action.LEFT_CLICK_BLOCK))
/* 50:   */     {
/* 51:39 */       if (!player.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
/* 52:39 */         return;
/* 53:   */       }
/* 54:40 */       if (!this.plugin.playerses.containsKey(player.getName())) {
/* 55:40 */         return;
/* 56:   */       }
/* 57:41 */       Location l = event.getClickedBlock().getLocation();
/* 58:42 */       PlayerSession ses = (PlayerSession)this.plugin.playerses.get(player.getName());
/* 59:43 */       ses.setLoc1(l);
/* 60:44 */       Util.msg(player, "Pos1: " + l.getX() + ", " + l.getY() + ", " + l.getZ());
/* 61:45 */       if (!ses.hasValidSelection()) {
/* 62:46 */         Util.msg(player, "Now you need to set position 2!");
/* 63:   */       }
/* 64:   */     }
/* 65:   */   }
/* 66:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.listeners.WandListener
 * JD-Core Version:    0.7.0.1
 */