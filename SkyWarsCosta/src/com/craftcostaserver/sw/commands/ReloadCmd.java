/*  1:   */ package com.craftcostaserver.sw.commands;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;

import com.craftcostaserver.sw.Config;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.Util;
import com.craftcostaserver.sw.data.Data;
import com.craftcostaserver.sw.data.RandomItems;
import com.craftcostaserver.sw.managers.ItemStackManager;
import com.craftcostaserver.sw.managers.KitManager;
/* 11:   */ 
/* 12:   */ public class ReloadCmd
/* 13:   */   extends BaseCmd
/* 14:   */ {
/* 15:   */   public ReloadCmd()
/* 16:   */   {
/* 17:10 */     this.forcePlayer = true;
/* 18:11 */     this.cmdName = "reload";
/* 19:12 */     this.argLength = 1;
/* 20:13 */     this.forceInRegion = false;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean run()
/* 24:   */   {
/* 25:18 */     Util.msg(this.player, "&l&aAttempting to for reload Hungergames!");
/* 26:19 */     HG.plugin.stopAll();
/* 27:20 */     HG.arenaconfig.saveCustomConfig();
/* 28:21 */     HG.arenaconfig.reloadCustomConfig();
/* 29:22 */     HG.arenaconfig.load();
/* 30:23 */     Util.msg(this.player, "&6 - &eArenas have been reloaded!");
/* 31:24 */     HG.plugin.kit.kititems.clear();
/* 32:25 */     HG.plugin.ism.setkits();
/* 33:26 */     Util.msg(this.player, "&6 - &eKits have been reloaded!");
/* 34:27 */     HG.plugin.items.clear();
/* 35:28 */     HG.ri.load();
/* 36:29 */     Util.msg(this.player, "&6 - &eRandomItems have been reloaded!");
/* 37:30 */     new Config(HG.plugin);
/* 38:31 */     Util.msg(this.player, "&6 - &eConfig values have been reloaded!");
/* 39:   */     
/* 40:33 */     Util.msg(this.player, "&l&a- Successfully reloaded HungerGames -");
/* 41:34 */     return true;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.commands.ReloadCmd
 * JD-Core Version:    0.7.0.1
 */