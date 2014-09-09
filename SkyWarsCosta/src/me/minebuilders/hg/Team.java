/*  1:   */ package me.minebuilders.hg;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ import org.bukkit.entity.Player;
/*  6:   */ 
/*  7:   */ public class Team
/*  8:   */ {
/*  9:   */   private String leader;
/* 10:11 */   private List<String> players = new ArrayList();
/* 11:12 */   private List<String> pending = new ArrayList();
/* 12:   */   
/* 13:   */   public Team(String leader)
/* 14:   */   {
/* 15:15 */     this.leader = leader;
/* 16:16 */     this.players.add(leader);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void invite(Player p)
/* 20:   */   {
/* 21:21 */     Util.scm(p, "&6*&b&m                                                                             &6*");
/* 22:22 */     Util.scm(p, "| &f" + this.leader + " &3Just invited you to a &fTeam&3!");
/* 23:23 */     Util.scm(p, "| &3Type &f/hg team accept &3To join!");
/* 24:24 */     Util.scm(p, "&6*&b&m                                                                             &6*");
/* 25:25 */     this.pending.add(p.getName());
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void acceptInvite(Player p)
/* 29:   */   {
/* 30:29 */     this.pending.remove(p.getName());
/* 31:30 */     this.players.add(p.getName());
/* 32:31 */     Util.msg(p, "&3You successfully joined this team!");
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean isOnTeam(String p)
/* 36:   */   {
/* 37:35 */     return this.players.contains(p);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean isPending(String p)
/* 41:   */   {
/* 42:39 */     return this.pending.contains(p);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public List<String> getPlayers()
/* 46:   */   {
/* 47:43 */     return this.players;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public List<String> getPenders()
/* 51:   */   {
/* 52:47 */     return this.pending;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public String getLeader()
/* 56:   */   {
/* 57:51 */     return this.leader;
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.Team
 * JD-Core Version:    0.7.0.1
 */