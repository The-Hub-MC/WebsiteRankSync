package com.TheHubMC.WebsiteRank;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultPerms
{
  WebsiteRank plugin;
  
  public VaultPerms(WebsiteRank plugin)
  {
    this.plugin = plugin;
  }
  
  public static Permission perms = null;
  
  public boolean setupVault()
  {
    if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
      return false;
    }
    RegisteredServiceProvider<Permission> rsp = Bukkit.getServer()
      .getServicesManager().getRegistration(Permission.class);
    if (rsp == null) {
      return false;
    }
    perms = (Permission)rsp.getProvider();
    return perms != null;
  }
  
  public String getVaultVersion()
  {
    return Bukkit.getServer().getPluginManager().getPlugin("Vault").getDescription().getVersion();
  }
  
  public String[] getGroups(Player p)
  {
    return perms.getPlayerGroups(p);
  }
  
  public String getGroup(Player p)
  {
    return perms.getPrimaryGroup(p);
  }
  
  public boolean hasPerm(Player p, String perm)
  {
    return perms.has(p, perm);
  }
  
  public String[] getServerGroups()
  {
    return perms.getGroups();
  }
  
  public boolean isValidGroup(String group)
  {
    boolean isValid = false;
    String[] arrayOfString;
    int j = (arrayOfString = getServerGroups()).length;
    for (int i = 0; i < j; i++)
    {
      String g = arrayOfString[i];
      if (g.equals(group))
      {
        isValid = true;
        break;
      }
    }
    return isValid;
  }
}
