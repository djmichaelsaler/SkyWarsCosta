/*  1:   */ package me.minebuilders.hg.commands;
/*  2:   */ 
/*  3:   */ import me.minebuilders.hg.Game;
/*  4:   */ import me.minebuilders.hg.HG;
/*  5:   */ import me.minebuilders.hg.Util;
/*  6:   */ import org.bukkit.Location;
/*  7:   */ import org.bukkit.World;
/*  8:   */ import org.bukkit.configuration.file.FileConfiguration;
/*  9:   */ import org.bukkit.entity.Player;
/* 10:   */ 
/* 11:   */ public class SetExitCmd
/* 12:   */   extends BaseCmd
/* 13:   */ {
/* 14:   */   public SetExitCmd()
/* 15:   */   {
/* 16:12 */     this.forcePlayer = true;
/* 17:13 */     this.cmdName = "setexit";
/* 18:14 */     this.forceInGame = false;
/* 19:15 */     this.argLength = 1;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean run()
/* 23:   */   {
/* 24:20 */     Location l = this.player.getLocation();
/* 25:21 */     String loc = l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ() + ":" + l.getYaw() + ":" + l.getPitch();
/* 26:22 */     HG.plugin.getConfig().set("settings.globalexit", loc);
/* 27:23 */     HG.plugin.saveConfig();
/* 28:24 */     Util.msg(this.player, "&3Global Exit Spawn Set: " + loc.replace(":", "&3,&b "));
/* 29:26 */     for (Game g : HG.plugin.games) {
/* 30:27 */       g.setExit(l);
/* 31:   */     }
/* 32:28 */     return true;
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.SetExitCmd
 * JD-Core Version:    0.7.0.1
 */