/*  1:   */ package me.minebuilders.hg;
/*  2:   */ 
/*  3:   */ import org.bukkit.Location;
/*  4:   */ 
/*  5:   */ public class PlayerSession
/*  6:   */ {
/*  7:   */   private Location loc1;
/*  8:   */   private Location loc2;
/*  9:   */   
/* 10:   */   public PlayerSession(Location l1, Location l2)
/* 11:   */   {
/* 12:12 */     setInfo(l1, l2);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setLoc1(Location loc1)
/* 16:   */   {
/* 17:16 */     this.loc1 = loc1;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void setLoc2(Location loc2)
/* 21:   */   {
/* 22:20 */     this.loc2 = loc2;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public Location getLoc1()
/* 26:   */   {
/* 27:24 */     return this.loc1;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public Location getLoc2()
/* 31:   */   {
/* 32:28 */     return this.loc2;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setInfo(Location l1, Location l2)
/* 36:   */   {
/* 37:32 */     setLoc1(l1);
/* 38:33 */     setLoc2(l2);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public boolean hasValidSelection()
/* 42:   */   {
/* 43:37 */     if ((this.loc1 == null) || (this.loc2 == null)) {
/* 44:37 */       return false;
/* 45:   */     }
/* 46:38 */     return true;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public String getInvalidLoc()
/* 50:   */   {
/* 51:42 */     if (this.loc1 == null) {
/* 52:42 */       return "Position 1";
/* 53:   */     }
/* 54:43 */     return "Position 2";
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.PlayerSession
 * JD-Core Version:    0.7.0.1
 */