/*   1:    */ package com.craftcostaserver.sw.managers;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.HashMap;
/*   5:    */ import java.util.List;
/*   6:    */ import java.util.Random;

/*  15:    */ import org.bukkit.Location;
/*  16:    */ import org.bukkit.Material;
/*  17:    */ import org.bukkit.block.Block;
/*  18:    */ import org.bukkit.block.Sign;
/*  19:    */ import org.bukkit.command.CommandSender;
/*  20:    */ import org.bukkit.configuration.Configuration;
/*  21:    */ import org.bukkit.entity.Player;
/*  22:    */ import org.bukkit.inventory.Inventory;
/*  23:    */ import org.bukkit.inventory.InventoryHolder;
/*  24:    */ import org.bukkit.inventory.ItemStack;

import com.craftcostaserver.sw.Bound;
import com.craftcostaserver.sw.Config;
import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.Status;
import com.craftcostaserver.sw.Util;
import com.craftcostaserver.sw.data.Data;
import com.craftcostaserver.sw.data.RandomItems;
/*  25:    */ 
/*  26:    */ public class Manager
/*  27:    */ {
/*  28:    */   private HG plugin;
/*  29: 29 */   private Random rg = new Random();
/*  30:    */   
/*  31:    */   public Manager(HG p)
/*  32:    */   {
/*  33: 32 */     this.plugin = p;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void runDebugger(CommandSender sender, String s)
/*  37:    */   {
/*  38: 36 */     Configuration arenadat = HG.arenaconfig.getCustomConfig();
/*  39: 37 */     boolean isReady = true;
/*  40: 38 */     List<Location> spawns = new ArrayList();
/*  41: 39 */     Sign lobbysign = null;
/*  42: 40 */     int timer = 0;
/*  43: 41 */     int minplayers = 0;
/*  44: 42 */     int maxplayers = 0;
/*  45:    */     try
/*  46:    */     {
/*  47: 45 */       timer = arenadat.getInt("arenas." + s + ".info." + "timer");
/*  48: 46 */       minplayers = arenadat.getInt("arenas." + s + ".info." + "min-players");
/*  49: 47 */       maxplayers = arenadat.getInt("arenas." + s + ".info." + "max-players");
/*  50:    */     }
/*  51:    */     catch (Exception e)
/*  52:    */     {
/*  53: 49 */       Util.scm(sender, "&cUnable to load infomation for arena " + s + "!");
/*  54: 50 */       isReady = false;
/*  55:    */     }
/*  56:    */     try
/*  57:    */     {
/*  58: 54 */       lobbysign = (Sign)HG.arenaconfig.getSLoc(arenadat.getString("arenas." + s + "." + "lobbysign")).getBlock().getState();
/*  59:    */     }
/*  60:    */     catch (Exception e)
/*  61:    */     {
/*  62: 56 */       Util.scm(sender, "&cUnable to load lobbysign for arena " + s + "!");
/*  63: 57 */       isReady = false;
/*  64:    */     }
/*  65:    */     try
/*  66:    */     {
/*  67: 61 */       for (String l : arenadat.getStringList("arenas." + s + "." + "spawns")) {
/*  68: 62 */         spawns.add(HG.arenaconfig.getLocFromString(l));
/*  69:    */       }
/*  70: 64 */       int count = arenadat.getStringList("arenas." + s + "." + "spawns").size();
/*  71: 65 */       if (count < maxplayers)
/*  72:    */       {
/*  73: 66 */         Util.scm(sender, "&cYou need to add " + (maxplayers - count) + " more spawns!");
/*  74: 67 */         isReady = false;
/*  75:    */       }
/*  76:    */     }
/*  77:    */     catch (Exception e)
/*  78:    */     {
/*  79: 70 */       Util.scm(sender, "&cUnable to load random spawns for arena " + s + "!");
/*  80: 71 */       isReady = false;
/*  81:    */     }
/*  82:    */     try
/*  83:    */     {
/*  84: 76 */       e = new Bound(arenadat.getString("arenas." + s + ".bound." + "world"), HG.arenaconfig.BC(s, "x"), HG.arenaconfig.BC(s, "y"), HG.arenaconfig.BC(s, "z"), HG.arenaconfig.BC(s, "x2"), HG.arenaconfig.BC(s, "y2"), HG.arenaconfig.BC(s, "z2"));
/*  85:    */     }
/*  86:    */     catch (Exception e)
/*  87:    */     {
/*  88: 78 */       Util.scm(sender, "&cUnable to load region bounds for arena " + s + "!");
/*  89: 79 */       isReady = false;
/*  90:    */     }
/*  91: 81 */     if (isReady)
/*  92:    */     {
/*  93: 82 */       Util.scm(sender, "&2&l---= &a&lYour hungergames arena is ready to run! &2&l=---");
/*  94: 83 */       Util.scm(sender, "&2Spawns:&a " + spawns.size());
/*  95: 84 */       Util.scm(sender, "&2Lobby:&a z:" + lobbysign.getX() + ", y:" + lobbysign.getY() + ", z:" + lobbysign.getZ());
/*  96: 85 */       Util.scm(sender, "&2Timer:&a " + timer);
/*  97: 86 */       Util.scm(sender, "&2MinPlayers:&a " + minplayers);
/*  98: 87 */       Util.scm(sender, "&2MaxPlayers:&a " + maxplayers);
/*  99:    */     }
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void checkGame(Game g, Player p)
/* 103:    */   {
/* 104: 92 */     if (g.getSpawns().size() < g.getMaxPlayers())
/* 105:    */     {
/* 106: 93 */       Util.scm(p, "&cYou still need &7" + (g.getMaxPlayers() - g.getSpawns().size()) + " &c more spawns!");
/* 107:    */     }
/* 108: 94 */     else if (g.getStatus() == Status.BROKEN)
/* 109:    */     {
/* 110: 95 */       Util.scm(p, "&cYour arena is marked as broken! use &7/hg debug &c to check for errors!");
/* 111: 96 */       Util.scm(p, "&cIf no errors are found, please use &7/hg toggle " + g.getName() + "&c!");
/* 112:    */     }
/* 113: 97 */     else if (!g.isLobbyValid())
/* 114:    */     {
/* 115: 98 */       Util.scm(p, "&cYour LobbyWall is invalid! Please reset them!");
/* 116: 99 */       Util.scm(p, "&cSet lobbywall: &7/hg setlobbywall " + g.getName());
/* 117:    */     }
/* 118:    */     else
/* 119:    */     {
/* 120:101 */       Util.scm(p, "&aYour HungerGames arena is ready to run!");
/* 121:102 */       g.setStatus(Status.WAITING);
/* 122:    */     }
/* 123:    */   }
/* 124:    */   
/* 125:    */   public void restoreChests(Game arena)
/* 126:    */   {
/* 127:107 */     ArrayList<Location> chests = arena.getChests();
/* 128:108 */     for (Location l : chests)
/* 129:    */     {
/* 130:109 */       Block b = l.getBlock();
/* 131:110 */       if (b.getType().equals(Material.CHEST))
/* 132:    */       {
/* 133:111 */         Inventory i = ((InventoryHolder)b.getState()).getInventory();
/* 134:112 */         i.clear();
/* 135:113 */         int c = this.rg.nextInt(Config.maxchestcontent) + 1;
/* 136:114 */         while (c != 0)
/* 137:    */         {
/* 138:115 */           ItemStack it = randomitem();
/* 139:116 */           i.setItem(this.rg.nextInt(27), it);
/* 140:117 */           c--;
/* 141:    */         }
/* 142:    */       }
/* 143:    */     }
/* 144:    */   }
/* 145:    */   
/* 146:    */   public ItemStack randomitem()
/* 147:    */   {
/* 148:124 */     return (ItemStack)this.plugin.items.get(Integer.valueOf(this.rg.nextInt(HG.ri.size)));
/* 149:    */   }
/* 150:    */   
/* 151:    */   public boolean isInRegion(Location l)
/* 152:    */   {
/* 153:128 */     for (Game g : HG.plugin.games) {
/* 154:129 */       if (g.isInRegion(l)) {
/* 155:130 */         return true;
/* 156:    */       }
/* 157:    */     }
/* 158:132 */     return false;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public Game getGame(Location l)
/* 162:    */   {
/* 163:136 */     for (Game g : HG.plugin.games) {
/* 164:137 */       if (g.isInRegion(l)) {
/* 165:138 */         return g;
/* 166:    */       }
/* 167:    */     }
/* 168:140 */     return null;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public Game getGame(String s)
/* 172:    */   {
/* 173:144 */     for (Game g : HG.plugin.games) {
/* 174:145 */       if (g.getName().equalsIgnoreCase(s)) {
/* 175:146 */         return g;
/* 176:    */       }
/* 177:    */     }
/* 178:149 */     return null;
/* 179:    */   }
/* 180:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.managers.Manager
 * JD-Core Version:    0.7.0.1
 */