/*   1:    */ package me.minebuilders.hg;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Collections;
/*   5:    */ import java.util.HashMap;
/*   6:    */ import java.util.List;
import java.util.Map;
/*   7:    */ import java.util.Map.Entry;

/*   8:    */ import me.minebuilders.hg.managers.KitManager;
/*   9:    */ import me.minebuilders.hg.managers.Manager;
/*  10:    */ import me.minebuilders.hg.mobhandler.Spawner;
/*  11:    */ import me.minebuilders.hg.tasks.ChestDropTask;
/*  12:    */ import me.minebuilders.hg.tasks.FreeRoamTask;
/*  13:    */ import me.minebuilders.hg.tasks.StartingTask;
/*  14:    */ import me.minebuilders.hg.tasks.TimerTask;
/*  15:    */ import net.milkbowl.vault.economy.Economy;

/*  16:    */ import org.bukkit.Bukkit;
/*  17:    */ import org.bukkit.ChatColor;
/*  18:    */ import org.bukkit.GameMode;
/*  19:    */ import org.bukkit.Location;
/*  20:    */ import org.bukkit.Material;
/*  21:    */ import org.bukkit.Server;
/*  22:    */ import org.bukkit.World;
/*  23:    */ import org.bukkit.block.Block;
/*  24:    */ import org.bukkit.block.BlockFace;
/*  25:    */ import org.bukkit.block.BlockState;
/*  26:    */ import org.bukkit.block.Sign;
/*  27:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  28:    */ import org.bukkit.entity.Player;
/*  29:    */ import org.bukkit.potion.PotionEffect;
/*  30:    */ import org.bukkit.potion.PotionEffectType;
/*  31:    */ 
/*  32:    */ public class Game
/*  33:    */ {
/*  34:    */   private String name;
/*  35:    */   private List<Location> spawns;
/*  36:    */   private Bound b;
/*  37: 32 */   private List<String> players = new ArrayList<String>();
/*  38: 33 */   private ArrayList<Location> chests = new ArrayList<Location>();
/*  39: 34 */   private List<BlockState> blocks = new ArrayList<BlockState>();
/*  40:    */   private Location exit;
/*  41:    */   private Status status;
/*  42:    */   private int minplayers;
/*  43:    */   private int maxplayers;
/*  44:    */   private int time;
/*  45:    */   private Sign s;
/*  46:    */   private Sign s1;
/*  47:    */   private Sign s2;
/*  48:    */   private int roamtime;
/*  49:    */   private SBDisplay sb;
/*  50:    */   private Spawner spawner;
/*  51:    */   private FreeRoamTask freeroam;
/*  52:    */   private StartingTask starting;
/*  53:    */   private TimerTask timer;
/*  54:    */   private ChestDropTask chestdrop;
/*  55:    */   
/*  56:    */   public Game(String s, Bound bo, List<Location> spawns, Sign lobbysign, int timer, int minplayers, int maxplayers, int roam, boolean isready)
/*  57:    */   {
/*  58: 54 */     this.name = s;
/*  59: 55 */     this.b = bo;
/*  60: 56 */     this.spawns = spawns;
/*  61: 57 */     this.s = lobbysign;
/*  62: 58 */     this.time = timer;
/*  63: 59 */     this.minplayers = minplayers;
/*  64: 60 */     this.maxplayers = maxplayers;
/*  65: 61 */     this.roamtime = roam;
/*  66: 63 */     if (isready) {
/*  67: 63 */       this.status = Status.STOPPED;
/*  68:    */     } else {
/*  69: 64 */       this.status = Status.BROKEN;
/*  70:    */     }
/*  71: 66 */     setChests();
/*  72: 67 */     setLobbyBlock(lobbysign);
/*  73:    */     
/*  74: 69 */     this.sb = new SBDisplay(this);
/*  75:    */   }
/*  76:    */   
/*  77:    */   public Game(String s, Bound c, int timer, int minplayers, int maxplayers, int roam)
/*  78:    */   {
/*  79: 73 */     this.name = s;
/*  80: 74 */     this.time = timer;
/*  81: 75 */     this.minplayers = minplayers;
/*  82: 76 */     this.maxplayers = maxplayers;
/*  83: 77 */     this.roamtime = roam;
/*  84: 78 */     this.spawns = new ArrayList();
/*  85: 79 */     this.b = c;
/*  86: 80 */     this.status = Status.NOTREADY;
/*  87: 81 */     setChests();
/*  88: 82 */     this.sb = new SBDisplay(this);
/*  89:    */   }
/*  90:    */   
/*  91:    */   public Bound getRegion()
/*  92:    */   {
/*  93: 86 */     return this.b;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void forceRollback()
/*  97:    */   {
/*  98: 90 */     Collections.reverse(this.blocks);
/*  99: 91 */     for (BlockState st : this.blocks) {
/* 100: 92 */       st.update(true);
/* 101:    */     }
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void setStatus(Status st)
/* 105:    */   {
/* 106: 97 */     this.status = st;
/* 107: 98 */     updateLobbyBlock();
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void addState(BlockState s)
/* 111:    */   {
/* 112:102 */     if (s.getType() != Material.AIR) {
/* 113:103 */       this.blocks.add(s);
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void recordBlockBreak(Block bl)
/* 118:    */   {
/* 119:108 */     Block top = bl.getRelative(BlockFace.UP);
/* 120:110 */     if ((!top.getType().isSolid()) || (!top.getType().isBlock())) {
/* 121:111 */       addState(bl.getRelative(BlockFace.UP).getState());
/* 122:    */     }
/* 123:114 */     for (BlockFace bf : Util.faces)
/* 124:    */     {
/* 125:115 */       Block rel = bl.getRelative(bf);
/* 126:117 */       if (Util.isAttached(bl, rel)) {
/* 127:118 */         addState(rel.getState());
/* 128:    */       }
/* 129:    */     }
/* 130:121 */     addState(bl.getState());
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void recordBlockPlace(BlockState bs)
/* 134:    */   {
/* 135:125 */     this.blocks.add(bs);
/* 136:    */   }
/* 137:    */   
/* 138:    */   public Status getStatus()
/* 139:    */   {
/* 140:129 */     return this.status;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public List<BlockState> getBlocks()
/* 144:    */   {
/* 145:133 */     Collections.reverse(this.blocks);
/* 146:134 */     return this.blocks;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void resetBlocks()
/* 150:    */   {
/* 151:138 */     this.blocks.clear();
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void setChests()
/* 155:    */   {
/* 156:142 */     this.chests.clear();
/* 157:143 */     for (Location bl : this.b.getBlocks(Material.CHEST)) {
/* 158:144 */       this.chests.add(bl);
/* 159:    */     }
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void msgAllMulti(String[] sta)
/* 163:    */   {
/* 164:149 */     for (String s : sta) {
/* 165:150 */       for (String st : this.players)
/* 166:    */       {
/* 167:151 */         Player p = Bukkit.getPlayer(st);
/* 168:152 */         if (p != null) {
/* 169:153 */           Util.msg(p, s);
/* 170:    */         }
/* 171:    */       }
/* 172:    */     }
/* 173:    */   }
/* 174:    */   
/* 175:    */   public List<String> getPlayers()
/* 176:    */   {
/* 177:159 */     return this.players;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public String getName()
/* 181:    */   {
/* 182:163 */     return this.name;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public boolean isInRegion(Location l)
/* 186:    */   {
/* 187:167 */     return this.b.isInRegion(l);
/* 188:    */   }
/* 189:    */   
/* 190:    */   public List<Location> getSpawns()
/* 191:    */   {
/* 192:171 */     return this.spawns;
/* 193:    */   }
/* 194:    */   
/* 195:    */   public int getRoamTime()
/* 196:    */   {
/* 197:175 */     return this.roamtime;
/* 198:    */   }
/* 199:    */   
/* 200:    */   public void join(Player p)
/* 201:    */   {
/* 202:179 */     if ((this.status != Status.WAITING) && (this.status != Status.STOPPED) && (this.status != Status.COUNTDOWN))
/* 203:    */     {
/* 204:180 */       p.sendMessage(ChatColor.RED + "This arena is not ready! Please wait before joining!");
/* 205:    */     }
/* 206:181 */     else if (this.maxplayers <= this.players.size())
/* 207:    */     {
/* 208:182 */       p.sendMessage(ChatColor.RED + this.name + " is currently full!");
/* 209:    */     }
/* 210:    */     else
/* 211:    */     {
/* 212:184 */       if (p.isInsideVehicle()) {
/* 213:185 */         p.leaveVehicle();
/* 214:    */       }
/* 215:187 */       this.players.add(p.getName());
/* 216:188 */       HG.plugin.players.put(p.getName(), new PlayerData(p, this));
/* 217:189 */       p.teleport(pickSpawn());
/* 218:190 */       heal(p);
/* 219:191 */       freeze(p);
/* 220:192 */       if ((this.players.size() >= this.minplayers) && (this.status.equals(Status.WAITING))) {
/* 221:193 */         startPreGame();
/* 222:194 */       } else if (this.status == Status.WAITING) {
/* 223:195 */         msgDef("&4(&3" + p.getName() + "&b Has joined the game" + (this.minplayers - this.players.size() <= 0 ? "!" : new StringBuilder(": ").append(this.minplayers - this.players.size()).append(" players to start!").toString()) + "&4)");
/* 224:    */       }
/* 225:197 */       kitHelp(p);
/* 226:198 */       if (this.players.size() == 1) {
/* 227:199 */         this.status = Status.WAITING;
/* 228:    */       }
/* 229:200 */       updateLobbyBlock();
/* 230:201 */       this.sb.setSB(p);
/* 231:202 */       this.sb.setAlive();
/* 232:    */     }
/* 233:    */   }
/* 234:    */   
/* 235:    */   public void kitHelp(Player p)
/* 236:    */   {
/* 237:207 */     String kit = HG.plugin.kit.getKitList();
/* 238:208 */     Util.scm(p, "&8     ");
/* 239:209 */     Util.scm(p, "&9&l>----------[&b&lWelcome to HungerGames&9&l]----------<");
/* 240:210 */     Util.scm(p, "&9&l - &bPick a kit using &c/hg kit <kit-name>");
/* 241:211 */     Util.scm(p, "&9&lKits:&b" + kit);
/* 242:212 */     Util.scm(p, "&9&l>------------------------------------------<");
/* 243:    */   }
/* 244:    */   
/* 245:    */   public void respawnAll()
/* 246:    */   {
/* 247:216 */     for (String st : this.players)
/* 248:    */     {
/* 249:217 */       Player p = Bukkit.getPlayer(st);
/* 250:218 */       if (p != null) {
/* 251:219 */         p.teleport(pickSpawn());
/* 252:    */       }
/* 253:    */     }
/* 254:    */   }
/* 255:    */   
/* 256:    */   public void startPreGame()
/* 257:    */   {
/* 258:224 */     setStatus(Status.COUNTDOWN);
/* 259:225 */     this.starting = new StartingTask(this);
/* 260:226 */     updateLobbyBlock();
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void startFreeRoam()
/* 264:    */   {
/* 265:230 */     this.status = Status.BEGINNING;
/* 266:231 */     HG.manager.restoreChests(this);
/* 267:232 */     this.b.removeEntities();
/* 268:233 */     this.freeroam = new FreeRoamTask(this);
/* 269:    */   }
/* 270:    */   
/* 271:    */   public void startGame()
/* 272:    */   {
/* 273:237 */     this.status = Status.RUNNING;
/* 274:238 */     if (Config.spawnmobs) {
/* 275:238 */       this.spawner = new Spawner(this, Config.spawnmobsinterval);
/* 276:    */     }
/* 277:239 */     if (Config.randomChest) {
/* 278:239 */       this.chestdrop = new ChestDropTask(this);
/* 279:    */     }
/* 280:240 */     this.timer = new TimerTask(this, this.time);
/* 281:241 */     updateLobbyBlock();
/* 282:    */   }
/* 283:    */   
/* 284:    */   public void addSpawn(Location l)
/* 285:    */   {
/* 286:246 */     this.spawns.add(l);
/* 287:    */   }
/* 288:    */   
/* 289:    */   public Location pickSpawn()
/* 290:    */   {
/* 291:250 */     int spawn = this.players.size() - 1;
/* 292:251 */     if (containsPlayer((Location)this.spawns.get(spawn))) {
/* 293:252 */       for (Location l : this.spawns) {
/* 294:253 */         if (!containsPlayer(l)) {
/* 295:254 */           return l;
/* 296:    */         }
/* 297:    */       }
/* 298:    */     }
/* 299:258 */     return (Location)this.spawns.get(spawn);
/* 300:    */   }
/* 301:    */   
/* 302:    */   public boolean containsPlayer(Location l)
/* 303:    */   {
/* 304:262 */     if (l == null) {
/* 305:262 */       return false;
/* 306:    */     }
/* 307:264 */     for (String s : this.players)
/* 308:    */     {
/* 309:265 */       Player p = Bukkit.getPlayer(s);
/* 310:266 */       if ((p != null) && (p.getLocation().getBlock().equals(l.getBlock()))) {
/* 311:267 */         return true;
/* 312:    */       }
/* 313:    */     }
/* 314:269 */     return false;
/* 315:    */   }
/* 316:    */   
/* 317:    */   public void msgAll(String s)
/* 318:    */   {
/* 319:273 */     for (String st : this.players)
/* 320:    */     {
/* 321:274 */       Player p = Bukkit.getPlayer(st);
/* 322:275 */       if (p != null) {
/* 323:276 */         Util.msg(p, s);
/* 324:    */       }
/* 325:    */     }
/* 326:    */   }
/* 327:    */   
/* 328:    */   public void msgDef(String s)
/* 329:    */   {
/* 330:281 */     for (String st : this.players)
/* 331:    */     {
/* 332:282 */       Player p = Bukkit.getPlayer(st);
/* 333:283 */       if (p != null) {
/* 334:284 */         Util.scm(p, s);
/* 335:    */       }
/* 336:    */     }
/* 337:    */   }
/* 338:    */   
/* 339:    */   public void updateLobbyBlock()
/* 340:    */   {
/* 341:289 */     this.s1.setLine(1, this.status.getName());
/* 342:290 */     this.s2.setLine(1, "" + ChatColor.BOLD + this.players.size() + "/" + this.maxplayers);
/* 343:291 */     this.s1.update(true);
/* 344:292 */     this.s2.update(true);
/* 345:    */   }
/* 346:    */   
/* 347:    */   public void heal(Player p)
/* 348:    */   {
/* 349:296 */     for (PotionEffect ef : p.getActivePotionEffects()) {
/* 350:297 */       p.removePotionEffect(ef.getType());
/* 351:    */     }
/* 352:299 */     p.setHealth(20.0D);
/* 353:300 */     p.setFoodLevel(20);
/* 354:301 */     p.setFireTicks(0);
/* 355:    */   }
/* 356:    */   
/* 357:    */   public void freeze(Player p)
/* 358:    */   {
/* 359:305 */     p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 23423525, -10));
/* 360:306 */     p.setWalkSpeed(1.0E-004F);
/* 361:307 */     p.setFoodLevel(1);
/* 362:308 */     p.setAllowFlight(false);
/* 363:309 */     p.setFlying(false);
/* 364:310 */     p.setGameMode(GameMode.SURVIVAL);
/* 365:    */   }
/* 366:    */   
/* 367:    */   public void unFreeze(Player p)
/* 368:    */   {
/* 369:314 */     p.removePotionEffect(PotionEffectType.JUMP);
/* 370:315 */     p.setWalkSpeed(0.2F);
/* 371:    */   }
/* 372:    */   
/* 373:    */   public boolean setLobbyBlock(Sign sign)
/* 374:    */   {
/* 375:    */     try
/* 376:    */     {
/* 377:320 */       this.s = sign;
/* 378:321 */       Block c = this.s.getBlock();
/* 379:    */       
/* 380:323 */       BlockFace face = Util.getSignFace(c.getData());
/* 381:324 */       this.s1 = ((Sign)c.getRelative(face).getState());
/* 382:325 */       this.s2 = ((Sign)this.s1.getBlock().getRelative(face).getState());
/* 383:    */       
/* 384:327 */       this.s.setLine(0, "" + ChatColor.DARK_BLUE + ChatColor.BOLD + "HungerGames");
/* 385:328 */       this.s.setLine(1, ChatColor.BOLD + this.name);
/* 386:329 */       this.s.setLine(2, ChatColor.BOLD + "Click To Join");
/* 387:330 */       this.s1.setLine(0, "" + ChatColor.DARK_BLUE + ChatColor.BOLD + "Game Status");
/* 388:331 */       this.s1.setLine(1, this.status.getName());
/* 389:332 */       this.s2.setLine(0, "" + ChatColor.DARK_BLUE + ChatColor.BOLD + "Alive");
/* 390:333 */       this.s2.setLine(1, "" + ChatColor.BOLD + 0 + "/" + this.maxplayers);
/* 391:334 */       this.s.update(true);
/* 392:335 */       this.s1.update(true);
/* 393:336 */       this.s2.update(true);
/* 394:    */     }
/* 395:    */     catch (Exception e)
/* 396:    */     {
/* 397:337 */       return false;
/* 398:    */     }
/* 399:    */     try
/* 400:    */     {
/* 401:339 */       String[] h = HG.plugin.getConfig().getString("settings.globalexit").split(":");
/* 402:340 */       this.exit = new Location(Bukkit.getServer().getWorld(h[0]), Integer.parseInt(h[1]) + 0.5D, Integer.parseInt(h[2]) + 0.1D, Integer.parseInt(h[3]) + 0.5D, Float.parseFloat(h[4]), Float.parseFloat(h[5]));
/* 403:    */     }
/* 404:    */     catch (Exception e)
/* 405:    */     {
/* 406:342 */       this.exit = this.s.getWorld().getSpawnLocation();
/* 407:    */     }
/* 408:344 */     return true;
/* 409:    */   }
/* 410:    */   
/* 411:    */   public void setExit(Location l)
/* 412:    */   {
/* 413:348 */     this.exit = l;
/* 414:    */   }
/* 415:    */   
/* 416:    */   public void cancelTasks()
/* 417:    */   {
/* 418:352 */     if (this.spawner != null) {
/* 419:352 */       this.spawner.stop();
/* 420:    */     }
/* 421:353 */     if (this.timer != null) {
/* 422:353 */       this.timer.stop();
/* 423:    */     }
/* 424:354 */     if (this.starting != null) {
/* 425:354 */       this.starting.stop();
/* 426:    */     }
/* 427:355 */     if (this.freeroam != null) {
/* 428:355 */       this.freeroam.stop();
/* 429:    */     }
/* 430:356 */     if (this.chestdrop != null) {
/* 431:356 */       this.chestdrop.shutdown();
/* 432:    */     }
/* 433:    */   }
/* 434:    */   
/* 435:    */   public void stop()
/* 436:    */   {
/* 437:360 */     List<String> win = new ArrayList();
/* 438:361 */     cancelTasks();
/* 439:362 */     for (String s : this.players)
/* 440:    */     {
/* 441:363 */       Player p = Bukkit.getPlayer(s);
/* 442:364 */       if (p != null)
/* 443:    */       {
/* 444:365 */         heal(p);
/* 445:366 */         exit(p);
/* 446:367 */         ((PlayerData)HG.plugin.players.get(p.getName())).restore(p);
/* 447:368 */         HG.plugin.players.remove(p.getName());
/* 448:369 */         win.add(p.getName());
/* 449:370 */         this.sb.restoreSB(p);
/* 450:    */       }
/* 451:    */     }
/* 452:373 */     this.players.clear();
/* 453:375 */     if ((!win.isEmpty()) && (Config.giveReward))
/* 454:    */     {
/* 455:376 */       double db = Config.cash / win.size();
/* 456:378 */       for (String s : win)
/* 457:    */       {
/* 458:379 */         Vault.economy.depositPlayer(s, db);
/* 459:380 */         Player p = Bukkit.getPlayer(s);
/* 460:381 */         if (p != null) {
/* 461:382 */           Util.msg(p, "&aYou won " + db + " for winning HungerGames!");
/* 462:    */         }
/* 463:    */       }
/* 464:    */     }
/* 465:386 */     Util.broadcast("&l&3" + Util.translateStop(win) + " &l&bWon HungerGames at arena " + this.name + "!");
/* 466:387 */     if (!this.blocks.isEmpty())
/* 467:    */     {
/* 468:388 */       new Rollback(this);
/* 469:    */     }
/* 470:    */     else
/* 471:    */     {
/* 472:390 */       this.status = Status.STOPPED;
/* 473:391 */       updateLobbyBlock();
/* 474:    */     }
/* 475:393 */     this.b.removeEntities();
/* 476:394 */     this.sb.resetAlive();
/* 477:    */   }
/* 478:    */   
/* 479:    */   public void leave(Player p)
/* 480:    */   {
/* 481:398 */     this.players.remove(p.getName());
/* 482:399 */     unFreeze(p);
/* 483:400 */     heal(p);
/* 484:401 */     exit(p);
/* 485:402 */     ((PlayerData)HG.plugin.players.get(p.getName())).restore(p);
/* 486:403 */     HG.plugin.players.remove(p.getName());
/* 487:404 */     if ((this.status == Status.RUNNING) || (this.status == Status.BEGINNING))
/* 488:    */     {
/* 489:405 */       if (isGameOver()) {
/* 490:406 */         stop();
/* 491:    */       }
/* 492:    */     }
/* 493:408 */     else if (this.status == Status.WAITING) {
/* 494:409 */       msgDef("&3&l" + p.getName() + "&l&c Has left the game" + (this.minplayers - this.players.size() <= 0 ? "!" : new StringBuilder(": ").append(this.minplayers - this.players.size()).append(" players to start!").toString()));
/* 495:    */     }
/* 496:411 */     updateLobbyBlock();
/* 497:412 */     this.sb.restoreSB(p);
/* 498:413 */     this.sb.setAlive();
/* 499:    */   }
/* 500:    */   
/* 501:    */   public boolean isGameOver()
/* 502:    */   {
/* 503:417 */     if (this.players.size() <= 1) {
/* 504:417 */       return true;
/* 505:    */     }
/* 506:418 */     for (Map.Entry<String, PlayerData> f : HG.plugin.players.entrySet())
/* 507:    */     {
/* 508:420 */       Team t = ((PlayerData)f.getValue()).getTeam();
/* 509:422 */       if ((t != null) && (t.getPlayers().size() >= this.players.size()))
/* 510:    */       {
/* 511:423 */         List<String> ps = t.getPlayers();
/* 512:424 */         for (String s : this.players) {
/* 513:425 */           if (!ps.contains(s)) {
/* 514:426 */             return false;
/* 515:    */           }
/* 516:    */         }
/* 517:429 */         return true;
/* 518:    */       }
/* 519:    */     }
/* 520:432 */     return false;
/* 521:    */   }
/* 522:    */   
/* 523:    */   public void addChests(Location b)
/* 524:    */   {
/* 525:436 */     this.chests.add(b);
/* 526:    */   }
/* 527:    */   
/* 528:    */   public ArrayList<Location> getChests()
/* 529:    */   {
/* 530:440 */     return this.chests;
/* 531:    */   }
/* 532:    */   
/* 533:    */   public void exit(Player p)
/* 534:    */   {
/* 535:444 */     Util.clearInv(p);
/* 536:445 */     if (this.exit == null) {
/* 537:446 */       p.teleport(this.s.getWorld().getSpawnLocation());
/* 538:    */     } else {
/* 539:448 */       p.teleport(this.exit);
/* 540:    */     }
/* 541:    */   }
/* 542:    */   
/* 543:    */   public int getMaxPlayers()
/* 544:    */   {
/* 545:453 */     return this.maxplayers;
/* 546:    */   }
/* 547:    */   
/* 548:    */   public boolean isLobbyValid()
/* 549:    */   {
/* 550:    */     try
/* 551:    */     {
/* 552:458 */       if (((this.s instanceof Sign)) && ((this.s1 instanceof Sign)) && ((this.s2 instanceof Sign))) {
/* 553:459 */         return true;
/* 554:    */       }
/* 555:    */     }
/* 556:    */     catch (Exception e)
/* 557:    */     {
/* 558:462 */       return false;
/* 559:    */     }
/* 560:464 */     return false;
/* 561:    */   }
/* 562:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.Game
 * JD-Core Version:    0.7.0.1
 */