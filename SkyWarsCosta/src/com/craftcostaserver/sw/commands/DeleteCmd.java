/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import java.util.List;

/* 10:   */ import org.bukkit.Bukkit;
/* 11:   */ import org.bukkit.command.CommandSender;
/* 12:   */ import org.bukkit.configuration.file.FileConfiguration;
/* 13:   */ import org.bukkit.entity.Player;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.Status;
import com.craftcostaserver.sw.Util;
import com.craftcostaserver.sw.data.Data;
import com.craftcostaserver.sw.managers.Manager;
/* 14:   */ 
/* 15:   */ public class DeleteCmd
/* 16:   */   extends BaseCmd
/* 17:   */ {
/* 18:   */   public DeleteCmd()
/* 19:   */   {
/* 20:14 */     this.forcePlayer = false;
/* 21:15 */     this.cmdName = "delete";
/* 22:16 */     this.forceInGame = false;
/* 23:17 */     this.argLength = 2;
/* 24:18 */     this.usage = "<&carena-name&b>";
/* 25:   */   }
/* 26:   */   
/* 27:   */   public boolean run()
/* 28:   */   {
/* 29:23 */     Game g = HG.manager.getGame(this.args[1]);
/* 30:24 */     if (g != null) {
/* 31:   */       try
/* 32:   */       {
/* 33:26 */         Util.msg(this.sender, "&aAttempting to delete " + g.getName() + "!");
/* 34:28 */         if ((g.getStatus() == Status.BEGINNING) || (g.getStatus() == Status.RUNNING))
/* 35:   */         {
/* 36:29 */           Util.msg(this.sender, "  &7- &cGame running! &aStopping..");
/* 37:30 */           g.forceRollback();
/* 38:31 */           g.stop();
/* 39:   */         }
/* 40:33 */         if (!g.getPlayers().isEmpty())
/* 41:   */         {
/* 42:34 */           Util.msg(this.sender, "  &7- &c&cPlayers detected! &aKicking..");
/* 43:35 */           for (String s : g.getPlayers())
/* 44:   */           {
/* 45:36 */             Player p = Bukkit.getPlayer(s);
/* 46:37 */             if (p != null) {
/* 47:38 */               g.leave(p);
/* 48:   */             }
/* 49:   */           }
/* 50:   */         }
/* 51:42 */         HG.arenaconfig.getCustomConfig().set("arenas." + this.args[1], null);
/* 52:43 */         HG.arenaconfig.saveCustomConfig();
/* 53:44 */         HG.plugin.games.remove(g);
/* 54:45 */         Util.msg(this.sender, "&aSuccessfully deleted SkyWars arena!");
/* 55:   */       }
/* 56:   */       catch (Exception e)
/* 57:   */       {
/* 58:47 */         Util.msg(this.sender, "&cFailed to delete arena!");
/* 59:   */       }
/* 60:   */     } else {
/* 61:50 */       this.sender.sendMessage("This arena does not exist!");
/* 62:   */     }
/* 63:52 */     return true;
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.DeleteCmd
 * JD-Core Version:    0.7.0.1
 */