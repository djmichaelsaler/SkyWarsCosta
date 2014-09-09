/*   1:    */ package me.minebuilders.hg.data;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import java.io.IOException;
/*   5:    */ import java.io.InputStream;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import java.util.List;
/*   8:    */ import me.minebuilders.hg.Bound;
/*   9:    */ import me.minebuilders.hg.Game;
/*  10:    */ import me.minebuilders.hg.HG;
/*  11:    */ import me.minebuilders.hg.Util;
/*  12:    */ import me.minebuilders.hg.tasks.CompassTask;
/*  13:    */ import org.bukkit.Bukkit;
/*  14:    */ import org.bukkit.Location;
/*  15:    */ import org.bukkit.Server;
/*  16:    */ import org.bukkit.block.Block;
/*  17:    */ import org.bukkit.block.Sign;
/*  18:    */ import org.bukkit.configuration.ConfigurationSection;
/*  19:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  20:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  21:    */ 
/*  22:    */ public class Data
/*  23:    */ {
/*  24: 23 */   private FileConfiguration arenadat = null;
/*  25: 24 */   private File customConfigFile = null;
/*  26:    */   private final HG plugin;
/*  27:    */   
/*  28:    */   public Data(HG plugin)
/*  29:    */   {
/*  30: 28 */     this.plugin = plugin;
/*  31: 29 */     reloadCustomConfig();
/*  32: 30 */     load();
/*  33:    */   }
/*  34:    */   
/*  35:    */   public FileConfiguration getConfig()
/*  36:    */   {
/*  37: 34 */     return this.arenadat;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void reloadCustomConfig()
/*  41:    */   {
/*  42: 38 */     if (this.customConfigFile == null) {
/*  43: 39 */       this.customConfigFile = new File(this.plugin.getDataFolder(), "arenas.yml");
/*  44:    */     }
/*  45: 41 */     this.arenadat = YamlConfiguration.loadConfiguration(this.customConfigFile);
/*  46:    */     
/*  47:    */ 
/*  48: 44 */     InputStream defConfigStream = this.plugin.getResource("arenas.yml");
/*  49: 45 */     if (defConfigStream != null)
/*  50:    */     {
/*  51: 46 */       YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/*  52: 47 */       this.arenadat.setDefaults(defConfig);
/*  53:    */     }
/*  54:    */   }
/*  55:    */   
/*  56:    */   public FileConfiguration getCustomConfig()
/*  57:    */   {
/*  58: 52 */     if (this.arenadat == null) {
/*  59: 53 */       reloadCustomConfig();
/*  60:    */     }
/*  61: 55 */     return this.arenadat;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void saveCustomConfig()
/*  65:    */   {
/*  66: 59 */     if ((this.arenadat == null) || (this.customConfigFile == null)) {
/*  67: 60 */       return;
/*  68:    */     }
/*  69:    */     try
/*  70:    */     {
/*  71: 63 */       getCustomConfig().save(this.customConfigFile);
/*  72:    */     }
/*  73:    */     catch (IOException ex)
/*  74:    */     {
/*  75: 65 */       Util.log("Could not save config to " + this.customConfigFile);
/*  76:    */     }
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void load()
/*  80:    */   {
/*  81: 70 */     int freeroam = this.plugin.getConfig().getInt("settings.free-roam");
/*  82: 71 */     if (new File(this.plugin.getDataFolder(), "arenas.yml").exists())
/*  83:    */     {
/*  84: 73 */       new CompassTask(this.plugin);
/*  85: 75 */       for (String s : this.arenadat.getConfigurationSection("arenas").getKeys(false))
/*  86:    */       {
/*  87: 76 */         boolean isReady = true;
/*  88: 77 */         List<Location> spawns = new ArrayList();
/*  89: 78 */         Sign lobbysign = null;
/*  90: 79 */         int timer = 0;
/*  91: 80 */         int minplayers = 0;
/*  92: 81 */         int maxplayers = 0;
/*  93: 82 */         Bound b = null;
/*  94:    */         try
/*  95:    */         {
/*  96: 85 */           timer = this.arenadat.getInt("arenas." + s + ".info." + "timer");
/*  97: 86 */           minplayers = this.arenadat.getInt("arenas." + s + ".info." + "min-players");
/*  98: 87 */           maxplayers = this.arenadat.getInt("arenas." + s + ".info." + "max-players");
/*  99:    */         }
/* 100:    */         catch (Exception e)
/* 101:    */         {
/* 102: 89 */           Util.warning("Unable to load infomation for arena " + s + "!");
/* 103: 90 */           isReady = false;
/* 104:    */         }
/* 105:    */         try
/* 106:    */         {
/* 107: 94 */           lobbysign = (Sign)getSLoc(this.arenadat.getString("arenas." + s + "." + "lobbysign")).getBlock().getState();
/* 108:    */         }
/* 109:    */         catch (Exception e)
/* 110:    */         {
/* 111: 96 */           Util.warning("Unable to load lobbysign for arena " + s + "!");
/* 112: 97 */           isReady = false;
/* 113:    */         }
/* 114:    */         try
/* 115:    */         {
/* 116:101 */           for (String l : this.arenadat.getStringList("arenas." + s + "." + "spawns")) {
/* 117:102 */             spawns.add(getLocFromString(l));
/* 118:    */           }
/* 119:    */         }
/* 120:    */         catch (Exception e)
/* 121:    */         {
/* 122:105 */           Util.warning("Unable to load random spawns for arena " + s + "!");
/* 123:106 */           isReady = false;
/* 124:    */         }
/* 125:    */         try
/* 126:    */         {
/* 127:110 */           b = new Bound(this.arenadat.getString("arenas." + s + ".bound." + "world"), BC(s, "x"), BC(s, "y"), BC(s, "z"), BC(s, "x2"), BC(s, "y2"), BC(s, "z2"));
/* 128:    */         }
/* 129:    */         catch (Exception e)
/* 130:    */         {
/* 131:112 */           Util.warning("Unable to load region bounds for arena " + s + "!");
/* 132:113 */           isReady = false;
/* 133:    */         }
/* 134:115 */         this.plugin.games.add(new Game(s, b, spawns, lobbysign, timer, minplayers, maxplayers, freeroam, isReady));
/* 135:    */       }
/* 136:    */     }
/* 137:    */   }
/* 138:    */   
/* 139:    */   public int BC(String s, String st)
/* 140:    */   {
/* 141:121 */     return this.arenadat.getInt("arenas." + s + ".bound." + st);
/* 142:    */   }
/* 143:    */   
/* 144:    */   public Location getLocFromString(String s)
/* 145:    */   {
/* 146:125 */     String[] h = s.split(":");
/* 147:126 */     return new Location(Bukkit.getServer().getWorld(h[0]), Integer.parseInt(h[1]) + 0.5D, Integer.parseInt(h[2]), Integer.parseInt(h[3]) + 0.5D, Float.parseFloat(h[4]), Float.parseFloat(h[5]));
/* 148:    */   }
/* 149:    */   
/* 150:    */   public Location getSLoc(String s)
/* 151:    */   {
/* 152:130 */     String[] h = s.split(":");
/* 153:131 */     return new Location(Bukkit.getServer().getWorld(h[0]), Integer.parseInt(h[1]), Integer.parseInt(h[2]), Integer.parseInt(h[3]));
/* 154:    */   }
/* 155:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.data.Data
 * JD-Core Version:    0.7.0.1
 */