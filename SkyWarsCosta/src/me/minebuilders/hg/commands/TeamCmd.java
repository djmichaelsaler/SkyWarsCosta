/*   1:    */ package me.minebuilders.hg.commands;
/*   2:    */ 
/*   3:    */ import java.util.HashMap;
/*   4:    */ import java.util.Iterator;
/*   5:    */ import java.util.List;
/*   6:    */ import me.minebuilders.hg.Config;
/*   7:    */ import me.minebuilders.hg.Game;
/*   8:    */ import me.minebuilders.hg.HG;
/*   9:    */ import me.minebuilders.hg.PlayerData;
/*  10:    */ import me.minebuilders.hg.Team;
/*  11:    */ import me.minebuilders.hg.Util;
/*  12:    */ import org.bukkit.Bukkit;
/*  13:    */ import org.bukkit.ChatColor;
/*  14:    */ import org.bukkit.entity.Player;
/*  15:    */ 
/*  16:    */ public class TeamCmd
/*  17:    */   extends BaseCmd
/*  18:    */ {
/*  19:    */   public TeamCmd()
/*  20:    */   {
/*  21: 17 */     this.forcePlayer = true;
/*  22: 18 */     this.cmdName = "team";
/*  23: 19 */     this.forceInGame = true;
/*  24: 20 */     this.argLength = 2;
/*  25: 21 */     this.usage = "<&cinvite&b/&caccept&b>";
/*  26:    */   }
/*  27:    */   
/*  28:    */   public boolean run()
/*  29:    */   {
/*  30: 26 */     PlayerData pd = (PlayerData)HG.plugin.players.get(this.player.getName());
/*  31: 27 */     Game g = pd.getGame();
/*  32: 29 */     if (this.args[1].equalsIgnoreCase("invite"))
/*  33:    */     {
/*  34: 31 */       if (this.args.length >= 3)
/*  35:    */       {
/*  36: 33 */         Player p = Bukkit.getPlayer(this.args[2]);
/*  37: 35 */         if ((p == null) || (!g.getPlayers().contains(p.getName())))
/*  38:    */         {
/*  39: 36 */           Util.msg(this.player, "&c" + this.args[2] + " Is not available!");
/*  40: 37 */           return true;
/*  41:    */         }
/*  42: 40 */         if (pd.getTeam() != null)
/*  43:    */         {
/*  44: 42 */           Team t = pd.getTeam();
/*  45: 44 */           if (!t.getLeader().equalsIgnoreCase(this.player.getName()))
/*  46:    */           {
/*  47: 45 */             Util.msg(this.player, "&cOnly the leader can invite other players!");
/*  48: 46 */             return true;
/*  49:    */           }
/*  50: 48 */           if (t.isOnTeam(p.getName()))
/*  51:    */           {
/*  52: 49 */             Util.msg(this.player, "&c" + this.args[2] + " &3is already on a team!");
/*  53: 50 */             return true;
/*  54:    */           }
/*  55: 53 */           if (t.getPlayers().size() + t.getPenders().size() >= Config.maxTeam)
/*  56:    */           {
/*  57: 54 */             Util.msg(this.player, "&cYou've hit the max team limit!");
/*  58: 55 */             return true;
/*  59:    */           }
/*  60: 58 */           ((PlayerData)HG.plugin.players.get(p.getName())).setTeam(t);
/*  61: 59 */           t.invite(p);
/*  62: 60 */           Util.msg(this.player, "&c" + p.getName() + " &3Has been invited!");
/*  63: 61 */           return true;
/*  64:    */         }
/*  65: 64 */         if (((PlayerData)HG.plugin.players.get(p.getName())).isOnTeam(p.getName()))
/*  66:    */         {
/*  67: 65 */           Util.msg(this.player, "&c" + this.args[2] + " &3is already on a team!");
/*  68: 66 */           return true;
/*  69:    */         }
/*  70: 69 */         Team t = new Team(this.player.getName());
/*  71: 70 */         ((PlayerData)HG.plugin.players.get(p.getName())).setTeam(t);
/*  72: 71 */         pd.setTeam(t);
/*  73: 72 */         t.invite(p);
/*  74: 73 */         Util.msg(this.player, "&c" + p.getName() + " &3Has been invited!");
/*  75: 74 */         return true;
/*  76:    */       }
/*  77: 76 */       Util.msg(this.player, "&cWrong Usage: &3/hg &bteam invite <&cname&b>");
/*  78:    */     }
/*  79: 78 */     else if (this.args[1].equalsIgnoreCase("accept"))
/*  80:    */     {
/*  81: 80 */       Team t = ((PlayerData)HG.plugin.players.get(this.player.getName())).getTeam();
/*  82: 82 */       if (t == null)
/*  83:    */       {
/*  84: 83 */         Util.msg(this.player, "&cYou have no pending invites...");
/*  85: 84 */         return true;
/*  86:    */       }
/*  87: 86 */       if (t.getPenders().contains(this.player.getName()))
/*  88:    */       {
/*  89: 88 */         t.acceptInvite(this.player);
/*  90: 89 */         Iterator localIterator = t.getPlayers().iterator();
/*  91: 89 */         if (localIterator.hasNext())
/*  92:    */         {
/*  93: 89 */           String s = (String)localIterator.next();
/*  94: 90 */           Player p = Bukkit.getPlayer(s);
/*  95: 92 */           if (p != null)
/*  96:    */           {
/*  97: 93 */             Util.scm(p, "&6*&b&m                                                                             &6*");
/*  98: 94 */             Util.scm(p, ChatColor.WHITE + this.player.getName() + " &3Just joined your team!");
/*  99: 95 */             Util.scm(p, "&6*&b&m                                                                             &6*");
/* 100:    */           }
/* 101: 97 */           return true;
/* 102:    */         }
/* 103:100 */         return true;
/* 104:    */       }
/* 105:    */     }
/* 106:    */     else
/* 107:    */     {
/* 108:103 */       Util.scm(this.player, "&c" + this.args[1] + " is not a valid command!");
/* 109:104 */       Util.scm(this.player, "&cValid arguments: &6invite&c, &6accept ");
/* 110:    */     }
/* 111:106 */     return true;
/* 112:    */   }
/* 113:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.TeamCmd
 * JD-Core Version:    0.7.0.1
 */