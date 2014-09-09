/*   1:    */ package com.craftcostaserver.sw.managers;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.HashMap;
/*   5:    */ import com.craftcostaserver.sw.HG;
/*   6:    */ import com.craftcostaserver.sw.Util;
/*   7:    */ import com.craftcostaserver.sw.data.KitEntry;
/*   8:    */ import org.bukkit.ChatColor;
/*   9:    */ import org.bukkit.DyeColor;
/*  10:    */ import org.bukkit.configuration.Configuration;
/*  11:    */ import org.bukkit.configuration.ConfigurationSection;
/*  12:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  13:    */ import org.bukkit.enchantments.Enchantment;
/*  14:    */ import org.bukkit.inventory.ItemStack;
/*  15:    */ import org.bukkit.inventory.meta.ItemMeta;
/*  16:    */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*  17:    */ import org.bukkit.potion.PotionEffect;
/*  18:    */ import org.bukkit.potion.PotionEffectType;
/*  19:    */ 
/*  20:    */ public class ItemStackManager
/*  21:    */ {
/*  22:    */   private HG plugin;
/*  23:    */   
/*  24:    */   public ItemStackManager(HG p)
/*  25:    */   {
/*  26: 24 */     this.plugin = p;
/*  27: 25 */     setkits();
/*  28:    */   }
/*  29:    */   
/*  30:    */   public void setkits()
/*  31:    */   {
/*  32: 29 */     Configuration config = this.plugin.getConfig();
/*  33: 30 */     for (String path : config.getConfigurationSection("kits").getKeys(false)) {
/*  34:    */       try
/*  35:    */       {
/*  36: 32 */         ArrayList<ItemStack> stack = new ArrayList();
/*  37: 33 */         ArrayList<PotionEffect> potions = new ArrayList();
/*  38: 34 */         String perm = null;
/*  39: 36 */         for (String item : config.getStringList("kits." + path + ".items")) {
/*  40: 37 */           stack.add(getItem(item, true));
/*  41:    */         }
/*  42: 39 */         for (String pot : this.plugin.getConfig().getStringList("kits." + path + ".potion-effects"))
/*  43:    */         {
/*  44: 40 */           String[] poti = pot.split("\\.");
/*  45: 41 */           PotionEffectType e = PotionEffectType.getByName(poti[0]);
/*  46: 42 */           if (poti[2].equalsIgnoreCase("forever"))
/*  47:    */           {
/*  48: 43 */             potions.add(e.createEffect(2147483647, Integer.parseInt(poti[1])));
/*  49:    */           }
/*  50:    */           else
/*  51:    */           {
/*  52: 45 */             Integer dur = Integer.valueOf(Integer.parseInt(poti[2]) * 20);
/*  53: 46 */             potions.add(e.createEffect(dur.intValue(), Integer.parseInt(poti[1])));
/*  54:    */           }
/*  55:    */         }
/*  56: 50 */         ItemStack helm = getItem(config.getString("kits." + path + ".helmet"), false);
/*  57: 51 */         ItemStack ches = getItem(config.getString("kits." + path + ".chestplate"), false);
/*  58: 52 */         ItemStack leg = getItem(config.getString("kits." + path + ".leggings"), false);
/*  59: 53 */         ItemStack boot = getItem(config.getString("kits." + path + ".boots"), false);
/*  60: 55 */         if ((this.plugin.getConfig().getString("kits." + path + ".permission") != null) && (!this.plugin.getConfig().getString("kits." + path + ".permission").equals("none"))) {
/*  61: 56 */           perm = this.plugin.getConfig().getString("kits." + path + ".permission");
/*  62:    */         }
/*  63: 58 */         this.plugin.kit.kititems.put(path, new KitEntry((ItemStack[])stack.toArray(new ItemStack[0]), helm, boot, ches, leg, perm, potions));
/*  64:    */       }
/*  65:    */       catch (Exception e)
/*  66:    */       {
/*  67: 60 */         Util.log("-------------------------------------------");
/*  68: 61 */         Util.log("WARNING: Unable to load kit " + path + "!");
/*  69: 62 */         Util.log("-------------------------------------------");
/*  70:    */       }
/*  71:    */     }
/*  72:    */   }
/*  73:    */   
/*  74:    */   public ItemStack getItem(String args, boolean isItem)
/*  75:    */   {
/*  76: 68 */     int amount = 1;
/*  77: 69 */     if (isItem)
/*  78:    */     {
/*  79: 70 */       String a = args.split(" ")[1];
/*  80: 71 */       if (Util.isInt(a)) {
/*  81: 72 */         amount = Integer.parseInt(a);
/*  82:    */       }
/*  83:    */     }
/*  84: 75 */     ItemStack item = itemStringToStack(args.split(" ")[0], Integer.valueOf(amount));
/*  85: 76 */     String[] ags = args.split(" ");
/*  86: 77 */     for (String s : ags)
/*  87:    */     {
/*  88:    */       int level;
/*  89:    */       //Enchantment e;
/*  90: 78 */       if (s.startsWith("enchant:"))
/*  91:    */       {
/*  92: 79 */         s = s.replace("enchant:", "").toUpperCase();
/*  93: 80 */         String[] d = s.split(":");
/*  94: 81 */         level = 1;
/*  95: 82 */         if ((d.length != 1) && (Util.isInt(d[1]))) {
/*  96: 83 */           level = Integer.parseInt(d[1]);
/*  97:    */         }
/*  98: 85 */         for (Enchantment es : Enchantment.values()) {
/*  99: 86 */           if (es.getName().equalsIgnoreCase(d[0])) {
/* 100: 87 */             item.addUnsafeEnchantment(es, level);
/* 101:    */           }
/* 102:    */         }
/* 103:    */       }
/* 104:    */       else
/* 105:    */       {
/* 106:    */         LeatherArmorMeta lam;
/* 107: 89 */         if (s.startsWith("color:"))
/* 108:    */         {
/* 109:    */           try
/* 110:    */           {
/* 111: 91 */             s = s.replace("color:", "").toUpperCase();
/* 112: 92 */             for (DyeColor c : DyeColor.values()) {
/* 113: 93 */               if (c.name().equalsIgnoreCase(s))
/* 114:    */               {
/* 115: 94 */                 lam = (LeatherArmorMeta)item.getItemMeta();
/* 116: 95 */                 lam.setColor(c.getColor());
/* 117: 96 */                 item.setItemMeta(lam);
/* 118:    */               }
/* 119:    */             }
/* 120:    */           }
/* 121:    */           catch (Exception localException) {}
/* 122:    */         }
/* 123:100 */         else if (s.startsWith("name:"))
/* 124:    */         {
/* 125:101 */           s = s.replace("name:", "").replace("_", " ");
/* 126:102 */           s = ChatColor.translateAlternateColorCodes('&', s);
/* 127:103 */           ItemMeta im = item.getItemMeta();
/* 128:104 */           im.setDisplayName(s);
/* 129:105 */           item.setItemMeta(im);
/* 130:    */         }
/* 131:106 */         else if (s.startsWith("lore:"))
/* 132:    */         {
/* 133:107 */           s = s.replace("lore:", "").replace("_", " ");
/* 134:108 */           s = ChatColor.translateAlternateColorCodes('&', s);
/* 135:109 */           ItemMeta meta = item.getItemMeta();
/* 136:110 */           ArrayList<String> lore = new ArrayList();
/* 137:111 */           for (String w : s.split(":")) {
/* 138:112 */             lore.add(w);
/* 139:    */           }
/* 140:113 */           meta.setLore(lore);
/* 141:114 */           item.setItemMeta(meta);
/* 142:    */         }
/* 143:    */       }
/* 144:    */     }
/* 145:117 */     return item;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public ItemStack itemStringToStack(String item, Integer amount)
/* 149:    */   {
/* 150:122 */     String[] itemArr = item.split(":");
/* 151:123 */     if (itemArr.length > 1) {
/* 152:124 */       return new ItemStack(Integer.parseInt(itemArr[0]), amount.intValue(), itemArr[1].length() <= 3 ? Byte.parseByte(itemArr[1]) : (short)Integer.parseInt(itemArr[1]));
/* 153:    */     }
/* 154:125 */     return new ItemStack(Integer.parseInt(itemArr[0]), amount.intValue());
/* 155:    */   }
/* 156:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.managers.ItemStackManager
 * JD-Core Version:    0.7.0.1
 */