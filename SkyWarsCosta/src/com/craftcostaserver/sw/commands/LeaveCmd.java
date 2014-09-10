/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;

/*  8:   */ import org.bukkit.entity.Player;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.PlayerData;
import com.craftcostaserver.sw.Util;
/*  9:   */ 
/* 10:   */ public class LeaveCmd
/* 11:   */   extends BaseCmd
/* 12:   */ {
/* 13:   */   public LeaveCmd()
/* 14:   */   {
/* 15: 9 */     this.forcePlayer = true;
/* 16:10 */     this.cmdName = "leave";
/* 17:11 */     this.forceInGame = true;
/* 18:12 */     this.argLength = 1;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean run()
/* 22:   */   {
/* 23:17 */     ((PlayerData)HG.plugin.players.get(this.player.getName())).getGame().leave(this.player);
/* 24:18 */     Util.msg(this.player, "&cYou left SkyWars!");
/* 25:19 */     return true;
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.LeaveCmd
 * JD-Core Version:    0.7.0.1
 */