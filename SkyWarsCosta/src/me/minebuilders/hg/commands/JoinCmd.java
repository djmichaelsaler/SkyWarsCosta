/*  1:   */ package me.minebuilders.hg.commands;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import java.util.List;
/*  5:   */ import me.minebuilders.hg.Game;
/*  6:   */ import me.minebuilders.hg.HG;
/*  7:   */ import me.minebuilders.hg.Util;
/*  8:   */ import me.minebuilders.hg.managers.Manager;
/*  9:   */ import org.bukkit.entity.Player;
/* 10:   */ 
/* 11:   */ public class JoinCmd
/* 12:   */   extends BaseCmd
/* 13:   */ {
/* 14:   */   public JoinCmd()
/* 15:   */   {
/* 16:10 */     this.forcePlayer = true;
/* 17:11 */     this.cmdName = "join";
/* 18:12 */     this.forceInGame = false;
/* 19:13 */     this.argLength = 2;
/* 20:14 */     this.usage = "<&carena-name&b>";
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean run()
/* 24:   */   {
/* 25:20 */     if (HG.plugin.players.containsKey(this.player.getName()))
/* 26:   */     {
/* 27:21 */       Util.msg(this.player, "&cYou're already in a game!");
/* 28:   */     }
/* 29:   */     else
/* 30:   */     {
/* 31:23 */       Game g = HG.manager.getGame(this.args[1]);
/* 32:24 */       if ((g != null) && (!g.getPlayers().contains(this.player.getName()))) {
/* 33:25 */         g.join(this.player);
/* 34:   */       } else {
/* 35:27 */         Util.msg(this.player, "&cThis arena does not exist!");
/* 36:   */       }
/* 37:   */     }
/* 38:30 */     return true;
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.JoinCmd
 * JD-Core Version:    0.7.0.1
 */