/*   1:    */ package com.craftcostaserver.sw.data;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import java.io.IOException;
/*   5:    */ import java.io.InputStream;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import java.util.HashMap;
/*   8:    */ import java.util.List;

/*  12:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  13:    */ import org.bukkit.configuration.file.YamlConfiguration;

import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.Util;
import com.craftcostaserver.sw.managers.ItemStackManager;
/*  14:    */ 
/*  15:    */ public class RandomItems
/*  16:    */ {
/*  17: 16 */   private FileConfiguration item = null;
/*  18: 17 */   private File customConfigFile = null;
/*  19: 18 */   public int size = 0;
/*  20:    */   private final HG plugin;
/*  21:    */   
/*  22:    */   public RandomItems(HG plugin)
/*  23:    */   {
/*  24: 22 */     this.plugin = plugin;
/*  25: 23 */     reloadCustomConfig();
/*  26: 24 */     load();
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void reloadCustomConfig()
/*  30:    */   {
/*  31: 28 */     if (this.customConfigFile == null) {
/*  32: 29 */       this.customConfigFile = new File(this.plugin.getDataFolder(), "items.yml");
/*  33:    */     }
/*  34: 31 */     this.item = YamlConfiguration.loadConfiguration(this.customConfigFile);
/*  35:    */     
/*  36:    */ 
/*  37: 34 */     InputStream defConfigStream = this.plugin.getResource("items.yml");
/*  38: 35 */     if (defConfigStream != null)
/*  39:    */     {
/*  40: 36 */       YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/*  41:    */       
/*  42: 38 */       this.item.setDefaults(defConfig);
/*  43:    */     }
/*  44:    */   }
/*  45:    */   
/*  46:    */   public FileConfiguration getCustomConfig()
/*  47:    */   {
/*  48: 43 */     if (this.item == null) {
/*  49: 44 */       reloadCustomConfig();
/*  50:    */     }
/*  51: 46 */     return this.item;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void saveCustomConfig()
/*  55:    */   {
/*  56: 50 */     if ((this.item == null) || (this.customConfigFile == null)) {
/*  57: 51 */       return;
/*  58:    */     }
/*  59:    */     try
/*  60:    */     {
/*  61: 54 */       getCustomConfig().save(this.customConfigFile);
/*  62:    */     }
/*  63:    */     catch (IOException ex)
/*  64:    */     {
/*  65: 56 */       Util.log("Could not save config to " + this.customConfigFile);
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void load()
/*  70:    */   {
/*  71: 61 */     this.size = 0;
/*  72: 62 */     if (this.item.getStringList("items").isEmpty())
/*  73:    */     {
/*  74: 63 */       setDefaultss();
/*  75: 64 */       saveCustomConfig();
/*  76: 65 */       reloadCustomConfig();
/*  77: 66 */       Util.log("generating defaults for random items!");
/*  78:    */     }
/*  79: 68 */     for (String s : this.item.getStringList("items"))
/*  80:    */     {
/*  81: 69 */       String[] amount = s.split(" ");
/*  82: 70 */       for (String p : amount) {
/*  83: 71 */         if (p.startsWith("x:"))
/*  84:    */         {
/*  85: 72 */           int c = Integer.parseInt(p.replace("x:", ""));
/*  86: 73 */           while (c != 0)
/*  87:    */           {
/*  88: 74 */             c--;
/*  89: 75 */             this.plugin.items.put(Integer.valueOf(this.plugin.items.size() + 1), this.plugin.ism.getItem(s.replace("x:", ""), true));
/*  90: 76 */             this.size += 1;
/*  91:    */           }
/*  92:    */         }
/*  93:    */         else
/*  94:    */         {
/*  95: 79 */           this.plugin.items.put(Integer.valueOf(this.plugin.items.size() + 1), this.plugin.ism.getItem(s, true));
/*  96:    */         }
/*  97:    */       }
/*  98: 80 */       this.size += 1;
/*  99:    */     }
/* 100: 82 */     Util.log(this.plugin.items.size() + " Random items have been loaded!");
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void setDefaultss()
/* 104:    */   {
/* 105: 86 */     ArrayList<String> s = new ArrayList();
/* 106: 87 */     s.add("272 1 x:5");
/* 107: 88 */     s.add("283 1");
/* 108: 89 */     s.add("282 1 x:2");
/* 109: 90 */     s.add("291 1");
/* 110: 91 */     s.add("298 1 x:2");
/* 111: 92 */     s.add("299 1 x:2");
/* 112: 93 */     s.add("300 1 x:2");
/* 113: 94 */     s.add("306 1 x:2");
/* 114: 95 */     s.add("307 1 x:2");
/* 115: 96 */     s.add("308 1 x:2");
/* 116: 97 */     s.add("309 1 x:2");
/* 117: 98 */     s.add("261 1 x:3");
/* 118: 99 */     s.add("262 20 x:2");
/* 119:100 */     s.add("335 1 x:2");
/* 120:101 */     s.add("346 1");
/* 121:102 */     s.add("345 1");
/* 122:103 */     s.add("280 1 name:&6TrackingStick_&aUses:_5");
/* 123:104 */     s.add("314 1");
/* 124:105 */     s.add("315 1");
/* 125:106 */     s.add("352 1 x:2");
/* 126:107 */     s.add("316 1");
/* 127:108 */     s.add("317 1");
/* 128:109 */     s.add("276 1 name:&6Death_Dealer");
/* 129:110 */     s.add("322 1");
/* 130:111 */     s.add("303 1 x:1");
/* 131:112 */     s.add("304 1 x:1");
/* 132:113 */     s.add("357 2 x:3");
/* 133:114 */     s.add("360 1 x:4");
/* 134:115 */     s.add("364 1 x:2");
/* 135:116 */     s.add("368 1 x:2");
/* 136:117 */     s.add("373:8194 1 x:2");
/* 137:118 */     s.add("373:8197 1 x:2");
/* 138:119 */     s.add("373:16420 1");
/* 139:120 */     s.add("373:16385 1 x:2");
/* 140:121 */     s.add("260 2 x:5");
/* 141:122 */     this.item.set("items", s);
/* 142:    */   }
/* 143:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.data.RandomItems
 * JD-Core Version:    0.7.0.1
 */