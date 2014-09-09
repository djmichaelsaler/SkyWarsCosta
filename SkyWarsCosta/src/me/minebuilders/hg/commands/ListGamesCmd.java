/*  1:   */ package me.minebuilders.hg.commands;
/*  2:   */ 
/*  3:   */ import me.minebuilders.hg.Game;
/*  4:   */ import me.minebuilders.hg.HG;
/*  5:   */ import me.minebuilders.hg.Status;
/*  6:   */ import me.minebuilders.hg.Util;
/*  7:   */ 
/*  8:   */ public class ListGamesCmd
/*  9:   */   extends BaseCmd
/* 10:   */ {
/* 11:   */   public ListGamesCmd()
/* 12:   */   {
/* 13:10 */     this.forcePlayer = false;
/* 14:11 */     this.cmdName = "listgames";
/* 15:12 */     this.forceInGame = false;
/* 16:13 */     this.argLength = 1;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public boolean run()
/* 20:   */   {
/* 21:18 */     Util.scm(this.sender, "&3&l Games:");
/* 22:19 */     for (Game g : HG.plugin.games) {
/* 23:20 */       Util.scm(this.sender, " &4 - &3" + g.getName() + "&4:&3" + g.getStatus().getName());
/* 24:   */     }
/* 25:22 */     return true;
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.ListGamesCmd
 * JD-Core Version:    0.7.0.1
 */