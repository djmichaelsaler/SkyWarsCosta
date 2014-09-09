/*  1:   */ package com.craftcostaserver.sw;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import java.util.List;
/*  5:   */ import org.bukkit.Bukkit;
/*  6:   */ import org.bukkit.ChatColor;
/*  7:   */ import org.bukkit.entity.Player;
/*  8:   */ import org.bukkit.scoreboard.DisplaySlot;
/*  9:   */ import org.bukkit.scoreboard.Objective;
/* 10:   */ import org.bukkit.scoreboard.Score;
/* 11:   */ import org.bukkit.scoreboard.Scoreboard;
/* 12:   */ import org.bukkit.scoreboard.ScoreboardManager;
/* 13:   */ 
/* 14:   */ public class SBDisplay
/* 15:   */ {
/* 16:   */   private ScoreboardManager manager;
/* 17:   */   private Scoreboard board;
/* 18:   */   private Objective ob;
/* 19:19 */   private HashMap<String, Scoreboard> score = new HashMap();
/* 20:   */   private Game g;
/* 21:   */   
/* 22:   */   public SBDisplay(Game g)
/* 23:   */   {
/* 24:23 */     this.manager = Bukkit.getScoreboardManager();
/* 25:24 */     this.board = this.manager.getNewScoreboard();
/* 26:25 */     this.ob = this.board.registerNewObjective(ChatColor.GREEN + "Players-Alive:", "dummy");
/* 27:26 */     this.ob.setDisplaySlot(DisplaySlot.SIDEBAR);
/* 28:27 */     this.ob.setDisplayName("" + ChatColor.BLUE + ChatColor.BOLD + "HungerGames");
/* 29:28 */     this.g = g;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void setAlive()
/* 33:   */   {
/* 34:32 */     Score score = this.ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Players-Alive:"));
/* 35:33 */     score.setScore(this.g.getPlayers().size());
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void resetAlive()
/* 39:   */   {
/* 40:37 */     this.board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Players-Alive:"));
/* 41:38 */     this.score.clear();
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void setSB(Player p)
/* 45:   */   {
/* 46:42 */     this.score.put(p.getName(), p.getScoreboard());
/* 47:43 */     p.setScoreboard(this.board);
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void restoreSB(Player p)
/* 51:   */   {
/* 52:47 */     if (this.score.get(p.getName()) == null)
/* 53:   */     {
/* 54:48 */       p.setScoreboard(this.manager.getNewScoreboard());
/* 55:   */     }
/* 56:   */     else
/* 57:   */     {
/* 58:50 */       p.setScoreboard((Scoreboard)this.score.get(p.getName()));
/* 59:51 */       this.score.remove(p.getName());
/* 60:   */     }
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.SBDisplay
 * JD-Core Version:    0.7.0.1
 */