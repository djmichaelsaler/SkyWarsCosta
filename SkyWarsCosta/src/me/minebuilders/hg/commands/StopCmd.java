/*  1:   */ package me.minebuilders.hg.commands;
/*  2:   */ 
/*  3:   */ import me.minebuilders.hg.Game;
/*  4:   */ import me.minebuilders.hg.HG;
/*  5:   */ import me.minebuilders.hg.Util;
/*  6:   */ import me.minebuilders.hg.managers.Manager;
/*  7:   */ import org.bukkit.ChatColor;
/*  8:   */ import org.bukkit.command.CommandSender;
/*  9:   */ 
/* 10:   */ public class StopCmd
/* 11:   */   extends BaseCmd
/* 12:   */ {
/* 13:   */   public StopCmd()
/* 14:   */   {
/* 15:12 */     this.forcePlayer = false;
/* 16:13 */     this.cmdName = "stop";
/* 17:14 */     this.forceInGame = false;
/* 18:15 */     this.argLength = 2;
/* 19:16 */     this.usage = "<&cgame&b>";
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean run()
/* 23:   */   {
/* 24:21 */     Game g = HG.manager.getGame(this.args[1]);
/* 25:22 */     if (g != null)
/* 26:   */     {
/* 27:23 */       g.stop();
/* 28:24 */       Util.scm(this.sender, "&3" + this.args[1] + "&b Has been stopped!");
/* 29:   */     }
/* 30:   */     else
/* 31:   */     {
/* 32:26 */       this.sender.sendMessage(ChatColor.RED + "This game does not exist!");
/* 33:   */     }
/* 34:28 */     return true;
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.StopCmd
 * JD-Core Version:    0.7.0.1
 */