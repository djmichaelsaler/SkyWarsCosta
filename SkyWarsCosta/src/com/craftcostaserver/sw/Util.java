/*   1:    */ package com.craftcostaserver.sw;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.List;
/*   5:    */ import java.util.logging.Logger;
/*   6:    */ import org.bukkit.Bukkit;
/*   7:    */ import org.bukkit.ChatColor;
/*   8:    */ import org.bukkit.Color;
/*   9:    */ import org.bukkit.FireworkEffect;
/*  10:    */ import org.bukkit.FireworkEffect.Builder;
/*  11:    */ import org.bukkit.FireworkEffect.Type;
/*  12:    */ import org.bukkit.Location;
/*  13:    */ import org.bukkit.Server;
/*  14:    */ import org.bukkit.World;
/*  15:    */ import org.bukkit.block.Block;
/*  16:    */ import org.bukkit.block.BlockFace;
/*  17:    */ import org.bukkit.block.BlockState;
/*  18:    */ import org.bukkit.command.CommandSender;
/*  19:    */ import org.bukkit.entity.Firework;
/*  20:    */ import org.bukkit.entity.Player;
/*  21:    */ import org.bukkit.inventory.EntityEquipment;
/*  22:    */ import org.bukkit.inventory.PlayerInventory;
/*  23:    */ import org.bukkit.inventory.meta.FireworkMeta;
/*  24:    */ import org.bukkit.material.Attachable;
/*  25:    */ import org.bukkit.material.MaterialData;
/*  26:    */ 
/*  27:    */ public class Util
/*  28:    */ {
/*  29: 24 */   public static final BlockFace[] faces = { BlockFace.EAST, BlockFace.WEST, BlockFace.NORTH, BlockFace.SOUTH };
/*  30: 26 */   private static final Logger log = Logger.getLogger("Minecraft");
/*  31:    */   
/*  32:    */   public static void log(String s)
/*  33:    */   {
/*  34: 28 */     log.info("[HungerGames] " + s);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public static void warning(String s)
/*  38:    */   {
/*  39: 30 */     log.warning("[HungerGames] " + s);
/*  40:    */   }
/*  41:    */   
/*  42:    */   public static boolean hp(Player p, String s)
/*  43:    */   {
/*  44: 33 */     if (p.hasPermission("hg." + s)) {
/*  45: 34 */       return true;
/*  46:    */     }
/*  47: 36 */     return false;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public static void msg(CommandSender sender, String s)
/*  51:    */   {
/*  52: 40 */     sender.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.DARK_AQUA + "HungerGames" + ChatColor.DARK_RED + "] " + ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', s));
/*  53:    */   }
/*  54:    */   
/*  55:    */   public static void scm(CommandSender sender, String s)
/*  56:    */   {
/*  57: 44 */     sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
/*  58:    */   }
/*  59:    */   
/*  60:    */   public static void broadcast(String s)
/*  61:    */   {
/*  62: 48 */     Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "[" + ChatColor.DARK_AQUA + "HungerGames" + ChatColor.DARK_RED + "] " + ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', s));
/*  63:    */   }
/*  64:    */   
/*  65:    */   public static boolean isInt(String str)
/*  66:    */   {
/*  67:    */     try
/*  68:    */     {
/*  69: 53 */       Integer.parseInt(str);
/*  70:    */     }
/*  71:    */     catch (NumberFormatException e)
/*  72:    */     {
/*  73: 54 */       return false;
/*  74:    */     }
/*  75: 55 */     return true;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public static BlockFace getSignFace(int face)
/*  79:    */   {
/*  80: 59 */     switch (face)
/*  81:    */     {
/*  82:    */     case 2: 
/*  83: 60 */       return BlockFace.WEST;
/*  84:    */     case 4: 
/*  85: 61 */       return BlockFace.SOUTH;
/*  86:    */     case 3: 
/*  87: 62 */       return BlockFace.EAST;
/*  88:    */     case 5: 
/*  89: 63 */       return BlockFace.NORTH;
/*  90:    */     }
/*  91: 65 */     return BlockFace.WEST;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public static void clearInv(Player p)
/*  95:    */   {
/*  96: 71 */     p.getInventory().clear();
/*  97: 72 */     p.getEquipment().clear();
/*  98: 73 */     p.getInventory().setHelmet(null);
/*  99: 74 */     p.getInventory().setChestplate(null);
/* 100: 75 */     p.getInventory().setLeggings(null);
/* 101: 76 */     p.getInventory().setBoots(null);
/* 102: 77 */     p.updateInventory();
/* 103:    */   }
/* 104:    */   
/* 105:    */   public static String translateStop(List<String> win)
/* 106:    */   {
/* 107: 81 */     String bc = null;
/* 108: 82 */     int count = 0;
/* 109: 83 */     for (String s : win)
/* 110:    */     {
/* 111: 84 */       count++;
/* 112: 85 */       if (count == 1) {
/* 113: 85 */         bc = s;
/* 114: 86 */       } else if (count == win.size()) {
/* 115: 86 */         bc = bc + ", and " + s;
/* 116:    */       } else {
/* 117: 87 */         bc = bc + ", " + s;
/* 118:    */       }
/* 119:    */     }
/* 120: 89 */     return bc;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public static void shootFirework(Location l)
/* 124:    */   {
/* 125: 93 */     Firework fw = (Firework)l.getWorld().spawn(l, Firework.class);
/* 126: 94 */     FireworkMeta fm = fw.getFireworkMeta();
/* 127: 95 */     List<Color> c = new ArrayList();
/* 128: 96 */     c.add(Color.ORANGE);
/* 129: 97 */     c.add(Color.RED);
/* 130: 98 */     FireworkEffect e = FireworkEffect.builder().flicker(true).withColor(c).withFade(c).with(FireworkEffect.Type.BALL_LARGE).trail(true).build();
/* 131: 99 */     fm.addEffect(e);
/* 132:100 */     fm.setPower(2);
/* 133:101 */     fw.setFireworkMeta(fm);
/* 134:    */   }
/* 135:    */   
/* 136:    */   public static boolean isAttached(Block base, Block attached)
/* 137:    */   {
/* 138:105 */     MaterialData bs = attached.getState().getData();
/* 139:107 */     if (!(bs instanceof Attachable)) {
/* 140:107 */       return false;
/* 141:    */     }
/* 142:109 */     Attachable at = (Attachable)bs;
/* 143:111 */     if (attached.getRelative(at.getAttachedFace()).equals(base)) {
/* 144:112 */       return true;
/* 145:    */     }
/* 146:114 */     return false;
/* 147:    */   }
/* 148:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.Util
 * JD-Core Version:    0.7.0.1
 */