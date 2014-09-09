/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ /*  8:   */ import org.bukkit.Location;
/*  9:   */ import org.bukkit.Material;
/* 10:   */ import org.bukkit.World;
/* 11:   */ import org.bukkit.block.Block;
/* 12:   */ import org.bukkit.block.Sign;
/* 13:   */ import org.bukkit.configuration.file.FileConfiguration;
/* 14:   */ import org.bukkit.entity.Player;

import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.Util;
import com.craftcostaserver.sw.data.Data;
import com.craftcostaserver.sw.managers.Manager;
/* 15:   */ 
/* 16:   */ public class SetLobbyWallCmd
/* 17:   */   extends BaseCmd
/* 18:   */ {
/* 19:   */   public SetLobbyWallCmd()
/* 20:   */   {
/* 21:15 */     this.forcePlayer = true;
/* 22:16 */     this.cmdName = "setlobbywall";
/* 23:17 */     this.forceInGame = false;
/* 24:18 */     this.argLength = 2;
/* 25:19 */     this.usage = "<&carena-name&b>";
/* 26:   */   }
/* 27:   */   
/* 28:   */   public boolean run()
/* 29:   */   {
/* 30:24 */     Game g = HG.manager.getGame(this.args[1]);
/* 31:25 */     if (g != null)
/* 32:   */     {
/* 33:27 */       Block b = this.player.getTargetBlock(null, 6);
/* 34:28 */       if ((b.getType() == Material.WALL_SIGN) && (g.setLobbyBlock((Sign)b.getState())))
/* 35:   */       {
/* 36:29 */         Location l = b.getLocation();
/* 37:30 */         HG.arenaconfig.getCustomConfig().set("arenas." + this.args[1] + "." + "lobbysign", l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ());
/* 38:31 */         HG.arenaconfig.saveCustomConfig();
/* 39:32 */         Util.msg(this.player, "&aThe lobbyWallSign has been set!");
/* 40:33 */         HG.manager.checkGame(g, this.player);
/* 41:   */       }
/* 42:   */       else
/* 43:   */       {
/* 44:35 */         Util.msg(this.player, "&cThese signs aren't in correct format!");
/* 45:36 */         Util.msg(this.player, "&cformat: &6[sign] &c[sign] [sign]");
/* 46:   */       }
/* 47:   */     }
/* 48:   */     else
/* 49:   */     {
/* 50:39 */       this.player.sendMessage("This arena does not exist!");
/* 51:   */     }
/* 52:41 */     return true;
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.SetLobbyWallCmd
 * JD-Core Version:    0.7.0.1
 */