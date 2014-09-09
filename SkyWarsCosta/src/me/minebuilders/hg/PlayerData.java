/*  1:   */ package me.minebuilders.hg;
/*  2:   */ 
/*  3:   */ import org.bukkit.GameMode;
/*  4:   */ import org.bukkit.entity.Player;
/*  5:   */ import org.bukkit.inventory.ItemStack;
/*  6:   */ import org.bukkit.inventory.PlayerInventory;
/*  7:   */ 
/*  8:   */ public class PlayerData
/*  9:   */ {
/* 10:   */   private ItemStack[] inv;
/* 11:   */   private ItemStack[] equip;
/* 12:   */   private int exp;
/* 13:   */   private GameMode mode;
/* 14:   */   private Team team;
/* 15:   */   private Game game;
/* 16:   */   
/* 17:   */   public PlayerData(Player p, Game game)
/* 18:   */   {
/* 19:22 */     this.game = game;
/* 20:23 */     this.inv = p.getInventory().getContents();
/* 21:24 */     this.equip = p.getInventory().getArmorContents();
/* 22:25 */     this.exp = ((int)p.getExp());
/* 23:26 */     this.mode = p.getGameMode();
/* 24:27 */     Util.clearInv(p);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void restore(Player p)
/* 28:   */   {
/* 29:32 */     Util.clearInv(p);
/* 30:33 */     p.setExp(0.0F);
/* 31:34 */     p.giveExp(this.exp);
/* 32:35 */     p.getInventory().setContents(this.inv);
/* 33:36 */     p.getInventory().setArmorContents(this.equip);
/* 34:37 */     p.setGameMode(this.mode);
/* 35:38 */     p.updateInventory();
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean isOnTeam(String s)
/* 39:   */   {
/* 40:42 */     return (this.team != null) && (this.team.isOnTeam(s));
/* 41:   */   }
/* 42:   */   
/* 43:   */   public Game getGame()
/* 44:   */   {
/* 45:46 */     return this.game;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public Team getTeam()
/* 49:   */   {
/* 50:50 */     return this.team;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void setTeam(Team team)
/* 54:   */   {
/* 55:54 */     this.team = team;
/* 56:   */   }
/* 57:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.PlayerData
 * JD-Core Version:    0.7.0.1
 */