/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;

/*  9:   */ import org.bukkit.ChatColor;
/* 10:   */ import org.bukkit.entity.Player;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.PlayerData;
import com.craftcostaserver.sw.Status;
import com.craftcostaserver.sw.managers.KitManager;
/* 11:   */ 
/* 12:   */ public class KitCmd
/* 13:   */   extends BaseCmd
/* 14:   */ {
/* 15:   */   public KitCmd()
/* 16:   */   {
/* 17: 9 */     this.forcePlayer = true;
/* 18:10 */     this.cmdName = "kit";
/* 19:11 */     this.forceInGame = true;
/* 20:12 */     this.argLength = 2;
/* 21:13 */     this.usage = "<&ckit&b>";
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean run()
/* 25:   */   {
/* 26:18 */     Status st = ((PlayerData)HG.plugin.players.get(this.player.getName())).getGame().getStatus();
/* 27:19 */     if ((st == Status.WAITING) || (st == Status.COUNTDOWN)) {
/* 28:20 */       HG.plugin.kit.setkit(this.player, this.args[1]);
/* 29:   */     } else {
/* 30:22 */       this.player.sendMessage(ChatColor.RED + "You can't change your kit while the game is running!");
/* 31:   */     }
/* 32:24 */     return true;
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.KitCmd
 * JD-Core Version:    0.7.0.1
 */