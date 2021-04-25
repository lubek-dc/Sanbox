package me.lubek.sandbox;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import me.lubek.sandbox.libs.file;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class events implements Listener {
    @EventHandler
    public static void onWorldChange(PlayerChangedWorldEvent event) {
        FileConfiguration config = file.getConfiguration("LobbyControl/config.yml");
        Player p = event.getPlayer();
        if(config.getString("SandboxWorld").equals(p.getWorld().getName())) {
            PlayerInventory pi = p.getInventory();
            pi.clear();
            ItemStack KnockbackStick = new ItemStack(Material.STICK,1);
            ItemMeta KnockbackStickMeta = KnockbackStick.getItemMeta();
            KnockbackStickMeta.addEnchant(Enchantment.KNOCKBACK, 3 , true);
            KnockbackStick.setItemMeta(KnockbackStickMeta);
            pi.addItem(KnockbackStick);
        }

    }
    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        FileConfiguration config = file.getConfiguration("LobbyControl/config.yml");
        int x = p.getLocation().getBlockX();
        int y = p.getLocation().getBlockY();
        int z = p.getLocation().getBlockZ();
        if(p.getWorld().getName().equals(config.getString("SandboxWorld"))) {
            Material block = p.getWorld().getBlockAt(x,y-1,z).getType();
            if (block.equals(Material.GOLD_BLOCK)) {
                int xx = config.getInt("Sandbox-gold-X");
                int yy = config.getInt("Sandbox-gold-Y");
                int zz = config.getInt("Sandbox-gold-Z");
                p.teleport(new Location(p.getWorld(),xx,yy,zz));
            }
            if (block.equals(Material.DIAMOND_BLOCK)) {
                int xx = config.getInt("Sandbox-diamond-X");
                int yy = config.getInt("Sandbox-diamond-Y");
                int zz = config.getInt("Sandbox-diamond-Z");
                p.teleport(new Location(p.getWorld(),xx,yy,zz));
            }
            if (block.equals(Material.IRON_BLOCK)) {
                PotionEffect potionEffect = new PotionEffect(PotionEffectType.REGENERATION,20,200);
                p.addPotionEffect(potionEffect);
                p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE,10,p.getLocation().getBlockX(),p.getLocation().getBlockY(),p.getLocation().getBlockZ());
                p.setFoodLevel(20);
            }
        }
    }
}
