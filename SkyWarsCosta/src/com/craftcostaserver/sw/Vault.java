/*  1:   */ package com.craftcostaserver.sw;
/*  2:   */ 
/*  3:   */ import net.milkbowl.vault.economy.Economy;
/*  4:   */ import org.bukkit.Bukkit;
/*  5:   */ import org.bukkit.Server;
/*  6:   */ import org.bukkit.plugin.RegisteredServiceProvider;
/*  7:   */ import org.bukkit.plugin.ServicesManager;
/*  8:   */ 
/*  9:   */ public class Vault
/* 10:   */ {
/* 11:10 */   public static Economy economy = null;
/* 12:   */   
/* 13:   */   public static boolean setupEconomy()
/* 14:   */   {
/* 15:13 */     RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
/* 16:14 */     if (economyProvider != null) {
/* 17:15 */       economy = (Economy)economyProvider.getProvider();
/* 18:   */     }
/* 19:18 */     return economy != null;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.Vault
 * JD-Core Version:    0.7.0.1
 */