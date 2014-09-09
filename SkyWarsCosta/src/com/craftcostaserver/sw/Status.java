/*  1:   */ package com.craftcostaserver.sw;
/*  2:   */ 
/*  3:   */ import org.bukkit.ChatColor;
/*  4:   */ 
/*  5:   */ public enum Status
/*  6:   */ {
/*  7: 7 */   RUNNING("" + ChatColor.GREEN + ChatColor.BOLD + "Running"),  STOPPED("" + ChatColor.DARK_RED + ChatColor.BOLD + "Stopped"),  WAITING("" + ChatColor.AQUA + ChatColor.BOLD + "Waiting..."),  BROKEN("" + ChatColor.DARK_RED + ChatColor.BOLD + "BROKEN"),  ROLLBACK("" + ChatColor.RED + ChatColor.BOLD + "Restoring..."),  NOTREADY("" + ChatColor.DARK_BLUE + ChatColor.BOLD + "NotReady"),  BEGINNING("" + ChatColor.GREEN + ChatColor.BOLD + "Running"),  COUNTDOWN("" + ChatColor.AQUA + ChatColor.BOLD + "Starting...");
/*  8:   */   
/*  9:   */   private String name;
/* 10:   */   
/* 11:   */   private Status(String name)
/* 12:   */   {
/* 13:15 */     this.name = name;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public String getName()
/* 17:   */   {
/* 18:19 */     return this.name;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.Status
 * JD-Core Version:    0.7.0.1
 */