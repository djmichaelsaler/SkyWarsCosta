/*  1:   */ package me.minebuilders.hg.commands;
/*  2:   */ 
/*  3:   */ import me.minebuilders.hg.Game;
/*  4:   */ import me.minebuilders.hg.HG;
/*  5:   */ import me.minebuilders.hg.Status;
/*  6:   */ import me.minebuilders.hg.Util;
/*  7:   */ import me.minebuilders.hg.managers.Manager;
/*  8:   */ import org.bukkit.ChatColor;
/*  9:   */ import org.bukkit.command.CommandSender;
/* 10:   */ 
/* 11:   */ public class ToggleCmd
/* 12:   */   extends BaseCmd
/* 13:   */ {
/* 14:   */   public ToggleCmd()
/* 15:   */   {
/* 16:13 */     this.forcePlayer = false;
/* 17:14 */     this.cmdName = "toggle";
/* 18:15 */     this.forceInGame = false;
/* 19:16 */     this.argLength = 2;
/* 20:17 */     this.usage = "<&cgame&b>";
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean run()
/* 24:   */   {
/* 25:22 */     Game g = HG.manager.getGame(this.args[1]);
/* 26:23 */     if (g != null)
/* 27:   */     {
/* 28:24 */       if ((g.getStatus() == Status.NOTREADY) || (g.getStatus() == Status.BROKEN))
/* 29:   */       {
/* 30:25 */         g.setStatus(Status.WAITING);
/* 31:26 */         Util.scm(this.sender, "&3" + this.args[1] + "&b is now unlocked!");
/* 32:   */       }
/* 33:   */       else
/* 34:   */       {
/* 35:28 */         g.setStatus(Status.NOTREADY);
/* 36:29 */         Util.scm(this.sender, "&3" + this.args[1] + "&b is now &4LOCKED&b!");
/* 37:   */       }
/* 38:   */     }
/* 39:   */     else {
/* 40:32 */       this.sender.sendMessage(ChatColor.RED + "This game does not exist!");
/* 41:   */     }
/* 42:34 */     return true;
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.ToggleCmd
 * JD-Core Version:    0.7.0.1
 */