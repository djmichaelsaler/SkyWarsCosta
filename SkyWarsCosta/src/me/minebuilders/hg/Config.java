/*  1:   */ package me.minebuilders.hg;
/*  2:   */ 
/*  3:   */ import java.io.File;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import java.util.List;
/*  6:   */ import org.bukkit.Color;
/*  7:   */ import org.bukkit.FireworkEffect;
/*  8:   */ import org.bukkit.FireworkEffect.Builder;
/*  9:   */ import org.bukkit.FireworkEffect.Type;
/* 10:   */ import org.bukkit.Material;
/* 11:   */ import org.bukkit.configuration.Configuration;
/* 12:   */ import org.bukkit.configuration.ConfigurationOptions;
/* 13:   */ import org.bukkit.configuration.file.FileConfiguration;
/* 14:   */ import org.bukkit.inventory.ItemStack;
/* 15:   */ import org.bukkit.inventory.meta.FireworkMeta;
/* 16:   */ 
/* 17:   */ public class Config
/* 18:   */ {
/* 19:   */   public static boolean spawnmobs;
/* 20:   */   public static int spawnmobsinterval;
/* 21:   */   public static int freeroam;
/* 22:   */   public static int trackingstickuses;
/* 23:   */   public static int playersfortrackingstick;
/* 24:   */   public static int maxchestcontent;
/* 25:   */   public static boolean teleAtEnd;
/* 26:   */   public static int maxTeam;
/* 27:   */   public static boolean giveReward;
/* 28:   */   public static int cash;
/* 29:   */   public static boolean breakblocks;
/* 30:   */   public static List<Integer> blocks;
/* 31:   */   public static boolean randomChest;
/* 32:   */   public static int randomChestInterval;
/* 33:   */   public static int randomChestMaxContent;
/* 34:   */   public static ItemStack firework;
/* 35:   */   private static Configuration config;
/* 36:   */   
/* 37:   */   public Config(HG plugin)
/* 38:   */   {
/* 39:46 */     if (!new File(plugin.getDataFolder(), "config.yml").exists())
/* 40:   */     {
/* 41:47 */       Util.log("Config not found. Generating default config!");
/* 42:48 */       plugin.saveDefaultConfig();
/* 43:   */     }
/* 44:51 */     config = plugin.getConfig().getRoot();
/* 45:52 */     config.options().copyDefaults(true);
/* 46:53 */     plugin.reloadConfig();
/* 47:54 */     config = plugin.getConfig();
/* 48:   */     
/* 49:56 */     spawnmobs = config.getBoolean("settings.spawn-mobs");
/* 50:57 */     spawnmobsinterval = config.getInt("settings.spawn-mobs-interval") * 20;
/* 51:58 */     freeroam = config.getInt("settings.free-roam");
/* 52:59 */     trackingstickuses = config.getInt("settings.trackingstick-uses");
/* 53:60 */     playersfortrackingstick = config.getInt("settings.players-for-trackingstick");
/* 54:61 */     maxchestcontent = config.getInt("settings.max-chestcontent");
/* 55:62 */     teleAtEnd = config.getBoolean("settings.teleport-at-end");
/* 56:63 */     maxTeam = config.getInt("settings.max-team-size");
/* 57:64 */     giveReward = config.getBoolean("reward.enabled");
/* 58:65 */     cash = config.getInt("reward.cash");
/* 59:66 */     maxTeam = config.getInt("settings.max-team-size");
/* 60:67 */     giveReward = config.getBoolean("reward.enabled");
/* 61:68 */     cash = config.getInt("reward.cash");
/* 62:69 */     breakblocks = config.getBoolean("rollback.allow-block-break");
/* 63:70 */     blocks = config.getIntegerList("rollback.editable-blocks");
/* 64:71 */     randomChest = config.getBoolean("random-chest.enabled");
/* 65:72 */     randomChestInterval = config.getInt("random-chest.interval") * 20;
/* 66:73 */     randomChestMaxContent = config.getInt("random-chest.max-chestcontent");
/* 67:75 */     if (giveReward) {
/* 68:   */       try
/* 69:   */       {
/* 70:77 */         Vault.setupEconomy();
/* 71:   */       }
/* 72:   */       catch (NoClassDefFoundError e)
/* 73:   */       {
/* 74:79 */         Util.log("Unable to setup vault! Rewards will not be given out..");
/* 75:80 */         giveReward = false;
/* 76:   */       }
/* 77:   */     }
/* 78:84 */     ItemStack i = new ItemStack(Material.FIREWORK, 64);
/* 79:85 */     FireworkMeta fm = (FireworkMeta)i.getItemMeta();
/* 80:86 */     List<Color> c = new ArrayList();
/* 81:87 */     c.add(Color.ORANGE);
/* 82:88 */     c.add(Color.RED);
/* 83:89 */     FireworkEffect e = FireworkEffect.builder().flicker(true).withColor(c).withFade(c).with(FireworkEffect.Type.BALL_LARGE).trail(true).build();
/* 84:90 */     fm.addEffect(e);
/* 85:91 */     fm.setPower(3);
/* 86:92 */     i.setItemMeta(fm);
/* 87:   */     
/* 88:94 */     firework = i;
/* 89:   */   }
/* 90:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.Config
 * JD-Core Version:    0.7.0.1
 */