/*   1:    */ package me.minebuilders.hg;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import me.minebuilders.hg.managers.Manager;
/*   5:    */ import org.bukkit.Bukkit;
/*   6:    */ import org.bukkit.Chunk;
/*   7:    */ import org.bukkit.Location;
/*   8:    */ import org.bukkit.Material;
/*   9:    */ import org.bukkit.Server;
/*  10:    */ import org.bukkit.World;
/*  11:    */ import org.bukkit.block.Block;
/*  12:    */ import org.bukkit.block.BlockState;
/*  13:    */ import org.bukkit.entity.Entity;
/*  14:    */ import org.bukkit.entity.FallingBlock;
/*  15:    */ import org.bukkit.entity.HumanEntity;
/*  16:    */ import org.bukkit.entity.Player;
/*  17:    */ import org.bukkit.event.EventHandler;
/*  18:    */ import org.bukkit.event.HandlerList;
/*  19:    */ import org.bukkit.event.Listener;
/*  20:    */ import org.bukkit.event.block.Action;
/*  21:    */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*  22:    */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*  23:    */ import org.bukkit.event.player.PlayerInteractEvent;
/*  24:    */ import org.bukkit.event.world.ChunkUnloadEvent;
/*  25:    */ import org.bukkit.inventory.Inventory;
/*  26:    */ import org.bukkit.inventory.ItemStack;
/*  27:    */ import org.bukkit.plugin.PluginManager;
/*  28:    */ 
/*  29:    */ public class ChestDrop
/*  30:    */   implements Listener
/*  31:    */ {
/*  32:    */   private FallingBlock fb;
/*  33:    */   private BlockState beforeBlock;
/*  34:    */   private Player invopener;
/*  35:    */   private Chunk c;
/*  36:    */   
/*  37:    */   public ChestDrop(FallingBlock fb)
/*  38:    */   {
/*  39: 35 */     this.fb = fb;
/*  40: 36 */     this.c = fb.getLocation().getChunk();
/*  41: 37 */     this.c.load();
/*  42: 38 */     Bukkit.getPluginManager().registerEvents(this, HG.plugin);
/*  43:    */   }
/*  44:    */   
/*  45:    */   @EventHandler
/*  46:    */   public void onUnload(ChunkUnloadEvent event)
/*  47:    */   {
/*  48: 43 */     if (event.getChunk().equals(this.c)) {
/*  49: 44 */       event.setCancelled(true);
/*  50:    */     }
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void remove()
/*  54:    */   {
/*  55: 50 */     if ((this.fb != null) && (!this.fb.isDead())) {
/*  56: 50 */       this.fb.remove();
/*  57:    */     }
/*  58: 51 */     if (this.beforeBlock != null)
/*  59:    */     {
/*  60: 52 */       this.beforeBlock.update(true);
/*  61: 53 */       Block b = this.beforeBlock.getBlock();
/*  62: 54 */       if (b.getTypeId() == 33) {
/*  63: 55 */         b.setType(Material.AIR);
/*  64:    */       }
/*  65:    */     }
/*  66: 59 */     HandlerList.unregisterAll(this);
/*  67:    */   }
/*  68:    */   
/*  69:    */   @EventHandler
/*  70:    */   public void onEntityModifyBlock(EntityChangeBlockEvent event)
/*  71:    */   {
/*  72: 64 */     Entity en = event.getEntity();
/*  73: 66 */     if (!(en instanceof FallingBlock)) {
/*  74: 66 */       return;
/*  75:    */     }
/*  76: 68 */     FallingBlock fb2 = (FallingBlock)en;
/*  77: 70 */     if (fb2.equals(this.fb))
/*  78:    */     {
/*  79: 71 */       this.beforeBlock = event.getBlock().getState();
/*  80:    */       
/*  81: 73 */       Location l = this.beforeBlock.getLocation();
/*  82: 74 */       Util.shootFirework(new Location(l.getWorld(), l.getX() + 0.5D, l.getY(), l.getZ() + 0.5D));
/*  83:    */     }
/*  84:    */   }
/*  85:    */   
/*  86:    */   @EventHandler
/*  87:    */   public void onClose(InventoryCloseEvent event)
/*  88:    */   {
/*  89: 80 */     for (HumanEntity p : event.getViewers()) {
/*  90: 81 */       if (((Player)p).equals(this.invopener))
/*  91:    */       {
/*  92: 82 */         Location l = this.beforeBlock.getLocation();
/*  93: 83 */         l.getWorld().createExplosion(l.getBlockX(), l.getBlockY(), l.getBlockZ(), 1.0F, false, false);
/*  94: 84 */         remove();
/*  95: 85 */         return;
/*  96:    */       }
/*  97:    */     }
/*  98:    */   }
/*  99:    */   
/* 100:    */   @EventHandler
/* 101:    */   public void onBla(PlayerInteractEvent event)
/* 102:    */   {
/* 103: 93 */     if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) && (this.beforeBlock != null) && (event.getClickedBlock().getLocation().equals(this.beforeBlock.getLocation())))
/* 104:    */     {
/* 105: 94 */       Player p = event.getPlayer();
/* 106: 95 */       Random rg = new Random();
/* 107: 96 */       this.invopener = p;
/* 108:    */       
/* 109: 98 */       Inventory i = Bukkit.getServer().createInventory(p, 54);
/* 110: 99 */       i.clear();
/* 111:100 */       int c = rg.nextInt(Config.randomChestMaxContent) + 1;
/* 112:101 */       while (c != 0)
/* 113:    */       {
/* 114:102 */         ItemStack it = HG.manager.randomitem();
/* 115:103 */         if (it != null) {
/* 116:104 */           i.addItem(new ItemStack[] { it });
/* 117:    */         }
/* 118:106 */         c--;
/* 119:    */       }
/* 120:108 */       p.openInventory(i);
/* 121:    */     }
/* 122:    */   }
/* 123:    */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.ChestDrop
 * JD-Core Version:    0.7.0.1
 */