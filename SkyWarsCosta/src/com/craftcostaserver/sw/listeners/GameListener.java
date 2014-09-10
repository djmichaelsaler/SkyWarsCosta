/*   1:    */ package com.craftcostaserver.sw.listeners;
/*   2:    */ 
/*   3:    */ import java.util.HashMap;
/*   4:    */ import java.util.List;

/*  14:    */ import org.bukkit.Bukkit;
/*  15:    */ import org.bukkit.ChatColor;
/*  16:    */ import org.bukkit.Location;
/*  17:    */ import org.bukkit.Material;
/*  18:    */ import org.bukkit.World;
/*  19:    */ import org.bukkit.block.Block;
/*  20:    */ import org.bukkit.block.Sign;
/*  21:    */ import org.bukkit.entity.Entity;
/*  22:    */ import org.bukkit.entity.LivingEntity;
/*  23:    */ import org.bukkit.entity.Player;
/*  24:    */ import org.bukkit.entity.Projectile;
/*  25:    */ import org.bukkit.event.EventHandler;
/*  26:    */ import org.bukkit.event.EventPriority;
/*  27:    */ import org.bukkit.event.Listener;
/*  28:    */ import org.bukkit.event.block.Action;
/*  29:    */ import org.bukkit.event.block.BlockBreakEvent;
/*  30:    */ import org.bukkit.event.block.BlockPlaceEvent;
/*  31:    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*  32:    */ import org.bukkit.event.entity.EntityDamageEvent;
/*  33:    */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*  34:    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*  35:    */ import org.bukkit.event.player.PlayerDropItemEvent;
/*  36:    */ import org.bukkit.event.player.PlayerInteractEvent;
/*  37:    */ import org.bukkit.event.player.PlayerQuitEvent;
/*  38:    */ import org.bukkit.inventory.ItemStack;
/*  39:    */ import org.bukkit.inventory.PlayerInventory;
/*  40:    */ import org.bukkit.inventory.meta.ItemMeta;
/*  41:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  42:    */ import org.bukkit.util.Vector;

import com.craftcostaserver.sw.Config;
import com.craftcostaserver.sw.Game;
import com.craftcostaserver.sw.HG;
import com.craftcostaserver.sw.PlayerData;
import com.craftcostaserver.sw.Status;
import com.craftcostaserver.sw.Team;
import com.craftcostaserver.sw.Util;
import com.craftcostaserver.sw.managers.KillManager;
import com.craftcostaserver.sw.managers.Manager;
/*  43:    */ 
/*  44:    */ public class GameListener
/*  45:    */   implements Listener
/*  46:    */ {
/*  47:    */   private HG plugin;
/*  48: 41 */   private String tsn = ChatColor.GOLD + "TrackingStick " + ChatColor.GREEN + "Uses: ";
/*  49:    */   private ItemStack trackingStick;
/*  50:    */   
/*  51:    */   public GameListener(HG plugin)
/*  52:    */   {
/*  53: 45 */     this.plugin = plugin;
/*  54: 46 */     ItemStack it = new ItemStack(Material.STICK, 1);
/*  55: 47 */     ItemMeta im = it.getItemMeta();
/*  56: 48 */     im.setDisplayName(this.tsn + Config.trackingstickuses);
/*  57: 49 */     it.setItemMeta(im);
/*  58: 50 */     this.trackingStick = it;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void dropInv(Player p)
/*  62:    */   {
/*  63: 54 */     PlayerInventory inv = p.getInventory();
/*  64: 55 */     Location l = p.getLocation();
/*  65: 56 */     for (ItemStack i : inv.getContents()) {
/*  66: 57 */       if ((i != null) && (i.getType() != Material.AIR)) {
/*  67: 58 */         l.getWorld().dropItemNaturally(l, i);
/*  68:    */       }
/*  69:    */     }
/*  70: 60 */     for (ItemStack i : inv.getArmorContents()) {
/*  71: 61 */       if ((i != null) && (i.getType() != Material.AIR)) {
/*  72: 62 */         l.getWorld().dropItemNaturally(l, i);
/*  73:    */       }
/*  74:    */     }
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void checkStick(Game g)
/*  78:    */   {
/*  79: 67 */     if (Config.playersfortrackingstick == g.getPlayers().size()) {
/*  80: 68 */       for (String r : g.getPlayers())
/*  81:    */       {
/*  82: 69 */         Player p = Bukkit.getPlayer(r);
/*  83: 70 */         if (p != null)
/*  84:    */         {
/*  85: 71 */           Util.scm(p, "&a&l[]------------------------------------------[]");
/*  86: 72 */           Util.scm(p, "&a&l |&3&l   You have been given a player-tracking stick! &a&l |");
/*  87: 73 */           Util.scm(p, "&a&l |&3&l   Swing the stick to track players!                &a&l |");
/*  88: 74 */           Util.scm(p, "&a&l[]------------------------------------------[]");
/*  89: 75 */           p.getInventory().addItem(new ItemStack[] { this.trackingStick });
/*  90:    */         }
/*  91:    */       }
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   @EventHandler
/*  96:    */   public void onDIe(PlayerDeathEvent event)
/*  97:    */   {
/*  98: 83 */     final Player p = event.getEntity();
/*  99:    */     
/* 100: 85 */     PlayerData pd = (PlayerData)this.plugin.players.get(p.getName());
/* 101: 87 */     if (pd != null)
/* 102:    */     {
/* 103: 88 */       final Game g = pd.getGame();
/* 104:    */       
/* 105: 90 */       p.setHealth(20.0D);
/* 106:    */       
/* 107: 92 */       LivingEntity killer = p.getKiller();
/* 108: 94 */       if (killer != null) {
/* 109: 95 */         g.msgDef("&l&d" + HG.killmanager.getKillString(p.getName(), killer));
/* 110:    */       } else {
/* 111: 97 */         g.msgDef("&d" + HG.killmanager.getDeathString(p.getLastDamageCause().getCause(), p.getName()));
/* 112:    */       }
/* 113: 99 */       event.setDeathMessage(null);
/* 114:    */       
/* 115:101 */       event.getDrops().clear();
/* 116:    */       
/* 117:103 */       dropInv(p);
/* 118:104 */       g.exit(p);
/* 119:    */       
/* 120:106 */       Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
/* 121:    */       {
/* 122:    */         public void run()
/* 123:    */         {
/* 124:110 */           g.leave(p);
/* 125:111 */           GameListener.this.checkStick(g);
/* 126:    */         }
/* 127:113 */       }, 10L);
/* 128:    */     }
/* 129:    */   }
/* 130:    */   
/* 131:    */   @EventHandler
/* 132:    */   public void onSprint(FoodLevelChangeEvent event)
/* 133:    */   {
/* 134:119 */     Player p = (Player)event.getEntity();
/* 135:120 */     if (this.plugin.players.containsKey(p.getName()))
/* 136:    */     {
/* 137:121 */       Status st = ((PlayerData)this.plugin.players.get(p.getName())).getGame().getStatus();
/* 138:122 */       if ((st == Status.WAITING) || (st == Status.COUNTDOWN))
/* 139:    */       {
/* 140:123 */         event.setFoodLevel(1);
/* 141:124 */         event.setCancelled(true);
/* 142:    */       }
/* 143:    */     }
/* 144:    */   }
/* 145:    */   
/* 146:    */   public void useTrackStick(Player p)
/* 147:    */   {
/* 148:131 */     ItemStack i = p.getItemInHand();
/* 149:132 */     ItemMeta im = i.getItemMeta();
/* 150:133 */     if ((im.getDisplayName() != null) && (im.getDisplayName().startsWith(this.tsn)))
/* 151:    */     {
/* 152:134 */       int uses = 0;
/* 153:135 */       uses = Integer.parseInt(im.getDisplayName().replace(this.tsn, ""));
/* 154:136 */       if (uses == 0)
/* 155:    */       {
/* 156:137 */         p.sendMessage(ChatColor.RED + "This trackingstick is out of uses!");
/* 157:    */       }
/* 158:    */       else
/* 159:    */       {
/* 160:139 */         boolean foundno = true;
/* 161:140 */         for (Entity e : p.getNearbyEntities(120.0D, 50.0D, 120.0D)) {
/* 162:141 */           if ((e instanceof Player))
/* 163:    */           {
/* 164:142 */             im.setDisplayName(this.tsn + (uses - 1));
/* 165:143 */             foundno = false;
/* 166:144 */             Location l = e.getLocation();
/* 167:145 */             int range = (int)p.getLocation().distance(l);
/* 168:146 */             Util.msg(p, "&3" + ((Player)e).getName() + "&b is " + range + " blocks away from you:&3 " + getDirection(p.getLocation().getBlock(), l.getBlock()));
/* 169:147 */             i.setItemMeta(im);
/* 170:148 */             p.updateInventory();
/* 171:149 */             return;
/* 172:    */           }
/* 173:    */         }
/* 174:152 */         if (foundno) {
/* 175:153 */           Util.msg(p, ChatColor.RED + "Couldn't locate any nearby players!");
/* 176:    */         }
/* 177:    */       }
/* 178:    */     }
/* 179:    */   }
/* 180:    */   
/* 181:    */   public String getDirection(Block block, Block block1)
/* 182:    */   {
/* 183:160 */     Vector bv = block.getLocation().toVector();
/* 184:161 */     Vector bv2 = block1.getLocation().toVector();
/* 185:162 */     float y = (float)angle(bv.getX(), bv.getZ(), bv2.getX(), bv2.getZ());
/* 186:163 */     float cal = y * 10.0F;
/* 187:164 */     int c = (int)cal;
/* 188:165 */     if ((c <= 1) && (c >= -1)) {
/* 189:166 */       return "South";
/* 190:    */     }
/* 191:167 */     if ((c > -14) && (c < -1)) {
/* 192:168 */       return "SouthWest";
/* 193:    */     }
/* 194:169 */     if ((c >= -17) && (c <= -14)) {
/* 195:170 */       return "West";
/* 196:    */     }
/* 197:171 */     if ((c > -29) && (c < -17)) {
/* 198:172 */       return "NorthWest";
/* 199:    */     }
/* 200:173 */     if ((c > 17) && (c < 29)) {
/* 201:174 */       return "NorthEast";
/* 202:    */     }
/* 203:175 */     if ((c <= 17) && (c >= 14)) {
/* 204:176 */       return "East";
/* 205:    */     }
/* 206:177 */     if ((c > 1) && (c < 14)) {
/* 207:178 */       return "SouthEast";
/* 208:    */     }
/* 209:179 */     if ((c <= 29) && (c >= -29)) {
/* 210:180 */       return "North";
/* 211:    */     }
/* 212:182 */     return "UnKnown";
/* 213:    */   }
/* 214:    */   
/* 215:    */   public double angle(double d, double e, double f, double g)
/* 216:    */   {
/* 217:189 */     int x = (int)(f - d);
/* 218:190 */     int z = (int)(g - e);
/* 219:    */     
/* 220:192 */     double yaw = Math.atan2(x, z);
/* 221:193 */     return yaw;
/* 222:    */   }
/* 223:    */   
/* 224:    */   @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=false)
/* 225:    */   public void onAttack(EntityDamageByEntityEvent event)
/* 226:    */   {
/* 227:198 */     Entity defender = event.getEntity();
/* 228:199 */     Entity damager = event.getDamager();
/* 229:201 */     if ((damager instanceof Projectile)) {
/* 230:202 */       damager = (Entity)((Projectile)damager).getShooter();
/* 231:    */     }
/* 232:205 */     if (((defender instanceof Player)) && (damager != null))
/* 233:    */     {
/* 234:206 */       Player p = (Player)defender;
/* 235:207 */       PlayerData pd = (PlayerData)this.plugin.players.get(p.getName());
/* 236:209 */       if (pd != null)
/* 237:    */       {
/* 238:210 */         Game g = pd.getGame();
/* 239:212 */         if (g.getStatus() != Status.RUNNING)
/* 240:    */         {
/* 241:213 */           event.setCancelled(true);
/* 242:    */         }
/* 243:214 */         else if ((pd.isOnTeam(p.getName())) && ((damager instanceof Player)) && (pd.getTeam().isOnTeam(((Player)damager).getName())))
/* 244:    */         {
/* 245:215 */           Util.scm((Player)damager, "&c" + p.getName() + " is on your team!");
/* 246:216 */           event.setCancelled(true);
/* 247:    */         }
/* 248:217 */         else if (event.isCancelled())
/* 249:    */         {
/* 250:217 */           event.setCancelled(false);
/* 251:    */         }
/* 252:    */       }
/* 253:    */     }
/* 254:    */   }
/* 255:    */   
/* 256:    */   @EventHandler
/* 257:    */   public void onItemUseAttempt(PlayerInteractEvent event)
/* 258:    */   {
/* 259:224 */     Player p = event.getPlayer();
/* 260:225 */     if ((event.getAction() != Action.PHYSICAL) && (this.plugin.players.containsKey(p.getName())))
/* 261:    */     {
/* 262:226 */       Status st = ((PlayerData)this.plugin.players.get(p.getName())).getGame().getStatus();
/* 263:227 */       if ((st == Status.WAITING) || (st == Status.COUNTDOWN))
/* 264:    */       {
/* 265:228 */         event.setCancelled(true);
/* 266:229 */         p.sendMessage(ChatColor.RED + "You cannot interact until the game has started!");
/* 267:    */       }
/* 268:    */     }
/* 269:    */   }
/* 270:    */   
/* 271:    */   @EventHandler
/* 272:    */   public void onPlayerClickLobby(PlayerInteractEvent event)
/* 273:    */   {
/* 274:236 */     Player p = event.getPlayer();
/* 275:237 */     if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
/* 276:    */     {
/* 277:238 */       Block b = event.getClickedBlock();
/* 278:239 */       if (b.getType().equals(Material.WALL_SIGN))
/* 279:    */       {
/* 280:240 */         Sign sign = (Sign)b.getState();
/* 281:241 */         if (sign.getLine(0).equals("" + ChatColor.DARK_BLUE + ChatColor.BOLD + "SkyWarsCosta"))
/* 282:    */         {
/* 283:242 */           Game game = HG.manager.getGame(sign.getLine(1).substring(2));
/* 284:243 */           if (game == null)
/* 285:    */           {
/* 286:244 */             Util.msg(p, ChatColor.RED + "This arena does not exist!");
/* 287:245 */             return;
/* 288:    */           }
/* 289:247 */           if (p.getItemInHand().getType() == Material.AIR) {
/* 290:248 */             game.join(p);
/* 291:    */           } else {
/* 292:250 */             Util.msg(p, ChatColor.RED + "Click the sign with your hand!");
/* 293:    */           }
/* 294:    */         }
/* 295:    */       }
/* 296:    */     }
/* 297:255 */     else if ((event.getAction().equals(Action.LEFT_CLICK_AIR)) && 
/* 298:256 */       (p.getItemInHand().getType().equals(Material.STICK)) && (this.plugin.players.containsKey(p.getName())))
/* 299:    */     {
/* 300:257 */       useTrackStick(p);
/* 301:    */     }
/* 302:    */   }
/* 303:    */   
/* 304:    */   @EventHandler
/* 305:    */   public void blockPlace(BlockPlaceEvent event)
/* 306:    */   {
/* 307:265 */     Player p = event.getPlayer();
/* 308:266 */     Block b = event.getBlock();
/* 309:268 */     if (HG.manager.isInRegion(b.getLocation()))
/* 310:    */     {
/* 311:270 */       if ((Config.breakblocks) && (this.plugin.players.containsKey(p.getName())))
/* 312:    */       {
/* 313:272 */         Game g = ((PlayerData)this.plugin.players.get(p.getName())).getGame();
/* 314:274 */         if ((g.getStatus() == Status.RUNNING) || (g.getStatus() == Status.BEGINNING))
/* 315:    */         {
/* 316:275 */           if (!Config.blocks.contains(Integer.valueOf(b.getType().getId())))
/* 317:    */           {
/* 318:276 */             p.sendMessage(ChatColor.RED + "You cannot edit this block type!");
/* 319:277 */             event.setCancelled(true);
/* 320:278 */             return;
/* 321:    */           }
/* 322:280 */           g.recordBlockPlace(event.getBlockReplacedState());
/* 323:281 */           return;
/* 324:    */         }
/* 325:284 */         p.sendMessage(ChatColor.RED + "The game is not running!");
/* 326:285 */         event.setCancelled(true);
/* 327:286 */         return;
/* 328:    */       }
/* 329:288 */       if ((p.hasPermission("sw.create")) && (HG.manager.getGame(b.getLocation()).getStatus() != Status.RUNNING))
/* 330:    */       {
/* 331:289 */         if (b.getType() == Material.CHEST) {
/* 332:290 */           HG.manager.getGame(b.getLocation()).addChests(b.getLocation());
/* 333:    */         }
/* 334:    */       }
/* 335:    */       else {
/* 336:293 */         event.setCancelled(true);
/* 337:    */       }
/* 338:    */     }
/* 339:    */   }
/* 340:    */   
/* 341:    */   @EventHandler
/* 342:    */   public void blockBreak(BlockBreakEvent event)
/* 343:    */   {
/* 344:301 */     Player p = event.getPlayer();
/* 345:302 */     Block b = event.getBlock();
/* 346:303 */     if (HG.manager.isInRegion(b.getLocation()))
/* 347:    */     {
/* 348:304 */       if ((Config.breakblocks) && (this.plugin.players.containsKey(p.getName())))
/* 349:    */       {
/* 350:305 */         Game g = ((PlayerData)this.plugin.players.get(p.getName())).getGame();
/* 351:306 */         if (g.getStatus() == Status.RUNNING)
/* 352:    */         {
/* 353:307 */           if (!Config.blocks.contains(Integer.valueOf(b.getType().getId())))
/* 354:    */           {
/* 355:308 */             p.sendMessage(ChatColor.RED + "You cannot edit this block type!");
/* 356:309 */             event.setCancelled(true);
/* 357:310 */             return;
/* 358:    */           }
/* 359:312 */           g.recordBlockBreak(b);
/* 360:313 */           return;
/* 361:    */         }
/* 362:316 */         p.sendMessage(ChatColor.RED + "The game is not running!");
/* 363:317 */         event.setCancelled(true);
/* 364:318 */         return;
/* 365:    */       }
/* 366:320 */       if ((p.hasPermission("sw.create")) && (HG.manager.getGame(b.getLocation()).getStatus() != Status.RUNNING)) {
/* 367:321 */         return;
/* 368:    */       }
/* 369:323 */       event.setCancelled(true);
/* 370:    */     }
/* 371:    */   }
/* 372:    */   
/* 373:    */   @EventHandler
/* 374:    */   public void onDrop(PlayerDropItemEvent event)
/* 375:    */   {
/* 376:330 */     Player p = event.getPlayer();
/* 377:331 */     if ((this.plugin.players.containsKey(p.getName())) && (((PlayerData)this.plugin.players.get(p.getName())).getGame().getStatus() == Status.WAITING)) {
/* 378:332 */       event.setCancelled(true);
/* 379:    */     }
/* 380:    */   }
/* 381:    */   
/* 382:    */   @EventHandler
/* 383:    */   public void onlogout(PlayerQuitEvent event)
/* 384:    */   {
/* 385:338 */     Player player = event.getPlayer();
/* 386:339 */     if (this.plugin.players.containsKey(player.getName())) {
/* 387:340 */       ((PlayerData)this.plugin.players.get(player.getName())).getGame().leave(player);
/* 388:    */     }
/* 389:    */   }
/* 390:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.listeners.GameListener
 * JD-Core Version:    0.7.0.1
 */