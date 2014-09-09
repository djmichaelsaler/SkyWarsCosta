/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;

/*  8:   */ import org.bukkit.entity.Player;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.PlayerData;
import com.craftcostaserver.sw.Util;
/*  9:   */ 
/* 10:   */ public class ListCmd
/* 11:   */   extends BaseCmd
/* 12:   */ {
/* 13:   */   public ListCmd()
/* 14:   */   {
/* 15:10 */     this.forcePlayer = true;
/* 16:11 */     this.cmdName = "list";
/* 17:12 */     this.forceInGame = true;
/* 18:13 */     this.argLength = 1;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean run()
/* 22:   */   {
/* 23:18 */     String p = "";
/* 24:19 */     Game g = ((PlayerData)HG.plugin.players.get(this.player.getName())).getGame();
/* 25:20 */     for (String s : g.getPlayers()) {
/* 26:21 */       p = p + "&3, &b" + s;
/* 27:   */     }
/* 28:23 */     p = p.substring(3);
/* 29:24 */     Util.scm(this.player, "&3Players:" + p);
/* 30:25 */     return true;
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.ListCmd
 * JD-Core Version:    0.7.0.1
 */