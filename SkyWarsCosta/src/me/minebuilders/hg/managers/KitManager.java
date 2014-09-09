/*  1:   */ package me.minebuilders.hg.managers;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import me.minebuilders.hg.Util;
/*  5:   */ import me.minebuilders.hg.data.KitEntry;
/*  6:   */ import org.bukkit.ChatColor;
/*  7:   */ import org.bukkit.entity.Player;
/*  8:   */ 
/*  9:   */ public class KitManager
/* 10:   */ {
/* 11:13 */   public HashMap<String, KitEntry> kititems = new HashMap();
/* 12:   */   
/* 13:   */   public void setkit(Player p, String path)
/* 14:   */   {
/* 15:16 */     if (!this.kititems.containsKey(path))
/* 16:   */     {
/* 17:17 */       Util.scm(p, ChatColor.RED + path + " Doesn't exist!");
/* 18:18 */       Util.scm(p, "&9&lKits:&b" + getKitList());
/* 19:   */     }
/* 20:19 */     else if (!((KitEntry)this.kititems.get(path)).hasKitPermission(p))
/* 21:   */     {
/* 22:20 */       Util.msg(p, ChatColor.RED + "You don't have permission to use this kit!");
/* 23:   */     }
/* 24:   */     else
/* 25:   */     {
/* 26:22 */       ((KitEntry)this.kititems.get(path)).setInventoryContent(p);
/* 27:   */     }
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String getKitList()
/* 31:   */   {
/* 32:27 */     String kits = "";
/* 33:28 */     for (String s : this.kititems.keySet()) {
/* 34:29 */       kits = kits + ", " + s;
/* 35:   */     }
/* 36:31 */     return kits.substring(1);
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.managers.KitManager
 * JD-Core Version:    0.7.0.1
 */