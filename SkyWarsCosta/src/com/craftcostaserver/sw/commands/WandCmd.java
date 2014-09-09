/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;

/*  7:   */ import org.bukkit.Material;
/*  8:   */ import org.bukkit.entity.Player;
/*  9:   */ import org.bukkit.inventory.ItemStack;
/* 10:   */ import org.bukkit.inventory.PlayerInventory;

import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.PlayerSession;
import com.craftcostaserver.sw.Util;
/* 11:   */ 
/* 12:   */ public class WandCmd
/* 13:   */   extends BaseCmd
/* 14:   */ {
/* 15:   */   public WandCmd()
/* 16:   */   {
/* 17:13 */     this.forcePlayer = true;
/* 18:14 */     this.cmdName = "wand";
/* 19:15 */     this.argLength = 1;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean run()
/* 23:   */   {
/* 24:20 */     if (HG.plugin.playerses.containsKey(this.player.getName()))
/* 25:   */     {
/* 26:21 */       HG.plugin.playerses.remove(this.player.getName());
/* 27:22 */       Util.msg(this.player, "Wand disabled!");
/* 28:   */     }
/* 29:   */     else
/* 30:   */     {
/* 31:24 */       this.player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BLAZE_ROD, 1) });
/* 32:25 */       HG.plugin.playerses.put(this.player.getName(), new PlayerSession(null, null));
/* 33:26 */       Util.msg(this.player, "Wand enabled!");
/* 34:   */     }
/* 35:28 */     return true;
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.WandCmd
 * JD-Core Version:    0.7.0.1
 */