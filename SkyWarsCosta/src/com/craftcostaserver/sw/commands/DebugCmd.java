/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.managers.Manager;
/*  5:   */ 
/*  6:   */ public class DebugCmd
/*  7:   */   extends BaseCmd
/*  8:   */ {
/*  9:   */   public DebugCmd()
/* 10:   */   {
/* 11: 8 */     this.forcePlayer = false;
/* 12: 9 */     this.cmdName = "debug";
/* 13:10 */     this.forceInGame = false;
/* 14:11 */     this.argLength = 2;
/* 15:12 */     this.usage = "<&cgame&b>";
/* 16:   */   }
/* 17:   */   
/* 18:   */   public boolean run()
/* 19:   */   {
/* 20:17 */     HG.manager.runDebugger(this.sender, this.args[1]);
/* 21:18 */     return true;
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.DebugCmd
 * JD-Core Version:    0.7.0.1
 */