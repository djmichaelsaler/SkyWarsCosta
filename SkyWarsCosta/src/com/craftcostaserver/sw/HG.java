/*   1:    */ package com.craftcostaserver.sw;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.HashMap;
/*   5:    */ import java.util.List;

/*  34:    */ import org.bukkit.Bukkit;
/*  35:    */ import org.bukkit.Server;
/*  36:    */ import org.bukkit.command.PluginCommand;
/*  37:    */ import org.bukkit.entity.Player;
/*  38:    */ import org.bukkit.inventory.ItemStack;
/*  39:    */ import org.bukkit.permissions.Permission;
/*  40:    */ import org.bukkit.plugin.PluginManager;
/*  41:    */ import org.bukkit.plugin.java.JavaPlugin;

import com.craftcostaserver.sw.commands.AddSpawnCmd;
import com.craftcostaserver.sw.commands.BaseCmd;
import com.craftcostaserver.sw.commands.CreateCmd;
import com.craftcostaserver.sw.commands.DebugCmd;
import com.craftcostaserver.sw.commands.DeleteCmd;
import com.craftcostaserver.sw.commands.JoinCmd;
import com.craftcostaserver.sw.commands.KitCmd;
import com.craftcostaserver.sw.commands.LeaveCmd;
import com.craftcostaserver.sw.commands.ListCmd;
import com.craftcostaserver.sw.commands.ListGamesCmd;
import com.craftcostaserver.sw.commands.ReloadCmd;
import com.craftcostaserver.sw.commands.SetExitCmd;
import com.craftcostaserver.sw.commands.SetLobbyWallCmd;
import com.craftcostaserver.sw.commands.StartCmd;
import com.craftcostaserver.sw.commands.StopCmd;
import com.craftcostaserver.sw.commands.TeamCmd;
import com.craftcostaserver.sw.commands.ToggleCmd;
import com.craftcostaserver.sw.commands.WandCmd;
import com.craftcostaserver.sw.data.Data;
import com.craftcostaserver.sw.data.RandomItems;
import com.craftcostaserver.sw.listeners.CancelListener;
import com.craftcostaserver.sw.listeners.CommandListener;
import com.craftcostaserver.sw.listeners.GameListener;
import com.craftcostaserver.sw.listeners.WandListener;
import com.craftcostaserver.sw.managers.ItemStackManager;
import com.craftcostaserver.sw.managers.KillManager;
import com.craftcostaserver.sw.managers.KitManager;
import com.craftcostaserver.sw.managers.Manager;
/*  42:    */ 
/*  43:    */ public class HG
/*  44:    */   extends JavaPlugin
/*  45:    */ {
/*  46: 45 */   public HashMap<String, BaseCmd> cmds = new HashMap();
/*  47: 46 */   public HashMap<String, PlayerData> players = new HashMap();
/*  48: 47 */   public HashMap<String, PlayerSession> playerses = new HashMap();
/*  49: 48 */   public HashMap<Integer, ItemStack> items = new HashMap();
/*  50: 51 */   public List<Game> games = new ArrayList();
/*  51:    */   public static HG plugin;
/*  52:    */   public static Manager manager;
/*  53:    */   public static Data arenaconfig;
/*  54:    */   public static KillManager killmanager;
/*  55:    */   public static RandomItems ri;
/*  56:    */   public KitManager kit;
/*  57:    */   public ItemStackManager ism;
/*  58:    */   
/*  59:    */   public void onEnable()
/*  60:    */   {
/*  61: 64 */     new Config(this);
/*  62: 65 */     plugin = this;
/*  63: 66 */     arenaconfig = new Data(this);
/*  64: 67 */     killmanager = new KillManager();
/*  65: 68 */     this.kit = new KitManager();
/*  66: 69 */     this.ism = new ItemStackManager(this);
/*  67: 70 */     ri = new RandomItems(this);
/*  68: 71 */     manager = new Manager(this);
/*  69: 72 */     getCommand("sw").setExecutor(new CommandListener(this));
/*  70: 73 */     getServer().getPluginManager().registerEvents(new WandListener(this), this);
/*  71: 74 */     getServer().getPluginManager().registerEvents(new CancelListener(this), this);
/*  72: 75 */     getServer().getPluginManager().registerEvents(new GameListener(this), this);
/*  73: 76 */     loadCmds();
/*  74: 77 */     Util.log("SkyWarsCosta esta habilitado!");
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void onDisable()
/*  78:    */   {
/*  79: 82 */     stopAll();
/*  80: 83 */     plugin = null;
/*  81: 84 */     manager = null;
/*  82: 85 */     arenaconfig = null;
/*  83: 86 */     killmanager = null;
/*  84: 87 */     this.kit = null;
/*  85: 88 */     this.ism = null;
/*  86: 89 */     ri = null;
/*  87: 90 */     Util.log("SkyWarsCosta esta habilitado!");
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void loadCmds()
/*  91:    */   {
/*  92: 94 */     this.cmds.put("team", new TeamCmd());
/*  93: 95 */     this.cmds.put("addspawn", new AddSpawnCmd());
/*  94: 96 */     this.cmds.put("create", new CreateCmd());
/*  95: 97 */     this.cmds.put("join", new JoinCmd());
/*  96: 98 */     this.cmds.put("leave", new LeaveCmd());
/*  97: 99 */     this.cmds.put("reload", new ReloadCmd());
/*  98:100 */     this.cmds.put("setlobbywall", new SetLobbyWallCmd());
/*  99:101 */     this.cmds.put("wand", new WandCmd());
/* 100:102 */     this.cmds.put("kit", new KitCmd());
/* 101:103 */     this.cmds.put("debug", new DebugCmd());
/* 102:104 */     this.cmds.put("list", new ListCmd());
/* 103:105 */     this.cmds.put("listgames", new ListGamesCmd());
/* 104:106 */     this.cmds.put("forcestart", new StartCmd());
/* 105:107 */     this.cmds.put("stop", new StopCmd());
/* 106:108 */     this.cmds.put("toggle", new ToggleCmd());
/* 107:109 */     this.cmds.put("setexit", new SetExitCmd());
/* 108:110 */     this.cmds.put("delete", new DeleteCmd());
/* 109:113 */     for (String bc : this.cmds.keySet()) {
/* 110:114 */       getServer().getPluginManager().addPermission(new Permission("sw." + bc));
/* 111:    */     }
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void stopAll()
/* 115:    */   {
/* 116:118 */     ArrayList<String> ps = new ArrayList();
/* 117:119 */     for (Game g : this.games)
/* 118:    */     {
/* 119:120 */       g.cancelTasks();
/* 120:121 */       g.forceRollback();
/* 121:122 */       ps.addAll(g.getPlayers());
/* 122:    */     }
/* 123:124 */     for (String s : ps)
/* 124:    */     {
/* 125:125 */       Player p = Bukkit.getPlayer(s);
/* 126:126 */       if (p != null) {
/* 127:127 */         ((PlayerData)this.players.get(s)).getGame().leave(p);
/* 128:    */       }
/* 129:    */     }
/* 130:130 */     this.players.clear();
/* 131:131 */     this.games.clear();
/* 132:    */   }
/* 133:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.HG
 * JD-Core Version:    0.7.0.1
 */