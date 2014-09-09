/*  1:   */ package me.minebuilders.hg.listeners;
/*  2:   */ 
/*  3:   */ import java.util.Collection;
/*  4:   */ import java.util.HashMap;
/*  5:   */ import me.minebuilders.hg.HG;
/*  6:   */ import me.minebuilders.hg.Util;
/*  7:   */ import me.minebuilders.hg.commands.BaseCmd;
/*  8:   */ import org.bukkit.command.Command;
/*  9:   */ import org.bukkit.command.CommandExecutor;
/* 10:   */ import org.bukkit.command.CommandSender;
/* 11:   */ 
/* 12:   */ public class CommandListener
/* 13:   */   implements CommandExecutor
/* 14:   */ {
/* 15:   */   private final HG p;
/* 16:   */   
/* 17:   */   public CommandListener(HG plugin)
/* 18:   */   {
/* 19:16 */     this.p = plugin;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean onCommand(CommandSender s, Command command, String label, String[] args)
/* 23:   */   {
/* 24:20 */     if ((args.length == 0) || (!this.p.cmds.containsKey(args[0])))
/* 25:   */     {
/* 26:21 */       Util.scm(s, "&6*&1&m                           &6*( &3Hungergames &6)*&1&m                           &6*");
/* 27:22 */       for (BaseCmd cmd : (BaseCmd[])this.p.cmds.values().toArray(new BaseCmd[0])) {
/* 28:23 */         if (s.hasPermission("hg." + cmd.cmdName)) {
/* 29:23 */           Util.scm(s, "  &4- " + cmd.sendHelpLine());
/* 30:   */         }
/* 31:   */       }
/* 32:25 */       Util.scm(s, "&6*&1&m                                                                             &6*");
/* 33:   */     }
/* 34:   */     else
/* 35:   */     {
/* 36:26 */       ((BaseCmd)this.p.cmds.get(args[0])).processCmd(this.p, s, args);
/* 37:   */     }
/* 38:27 */     return true;
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.listeners.CommandListener
 * JD-Core Version:    0.7.0.1
 */