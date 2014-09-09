/*  1:   */ package me.minebuilders.hg.listeners;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import me.minebuilders.hg.Game;
/*  5:   */ import me.minebuilders.hg.HG;
/*  6:   */ import me.minebuilders.hg.PlayerData;
/*  7:   */ import me.minebuilders.hg.Status;
/*  8:   */ import me.minebuilders.hg.Util;
/*  9:   */ import org.bukkit.Bukkit;
/* 10:   */ import org.bukkit.ChatColor;
/* 11:   */ import org.bukkit.Server;
/* 12:   */ import org.bukkit.entity.Player;
/* 13:   */ import org.bukkit.event.EventHandler;
/* 14:   */ import org.bukkit.event.EventPriority;
/* 15:   */ import org.bukkit.event.Listener;
/* 16:   */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/* 17:   */ 
/* 18:   */ public class CancelListener
/* 19:   */   implements Listener
/* 20:   */ {
/* 21:   */   public HG plugin;
/* 22:   */   
/* 23:   */   public CancelListener(HG instance)
/* 24:   */   {
/* 25:20 */     this.plugin = instance;
/* 26:   */   }
/* 27:   */   
/* 28:   */   @EventHandler(priority=EventPriority.LOWEST)
/* 29:   */   public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
/* 30:   */   {
/* 31:25 */     Player player = event.getPlayer();
/* 32:26 */     String[] st = event.getMessage().split(" ");
/* 33:27 */     if ((this.plugin.players.containsKey(player.getName())) && (!st[0].equalsIgnoreCase("/login")))
/* 34:   */     {
/* 35:28 */       if (st[0].equalsIgnoreCase("/hg"))
/* 36:   */       {
/* 37:29 */         if ((st.length >= 2) && (st[1].equalsIgnoreCase("kit")) && (((PlayerData)this.plugin.players.get(player.getName())).getGame().getStatus() == Status.RUNNING))
/* 38:   */         {
/* 39:30 */           event.setMessage("/");
/* 40:31 */           event.setCancelled(true);
/* 41:32 */           Util.msg(player, ChatColor.RED + "You can't choose a kit while the game is running!");
/* 42:   */         }
/* 43:34 */         return;
/* 44:   */       }
/* 45:36 */       event.setMessage("/");
/* 46:37 */       event.setCancelled(true);
/* 47:38 */       Util.msg(player, ChatColor.RED + "You can not use non-hungergames commands while ingame!");
/* 48:   */     }
/* 49:39 */     else if (("/tp".equalsIgnoreCase(st[0])) && (st.length >= 2))
/* 50:   */     {
/* 51:40 */       Player p = Bukkit.getServer().getPlayer(st[1]);
/* 52:41 */       if ((p != null) && 
/* 53:42 */         (this.plugin.players.containsKey(p.getName())))
/* 54:   */       {
/* 55:43 */         player.sendMessage(ChatColor.RED + "This player is currently playing Hungergames!");
/* 56:44 */         event.setMessage("/");
/* 57:45 */         event.setCancelled(true);
/* 58:   */       }
/* 59:   */     }
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\Administrador\Downloads\Hungergames.jar
 * Qualified Name:     me.minebuilders.hg.listeners.CancelListener
 * JD-Core Version:    0.7.0.1
 */