package com.promc.nodamageindicator;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class NoDamageIndicator extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("NoDamageIndicator Enabled. Designed by ProMC");
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(
                new PacketAdapter(this, ListenerPriority.HIGH, PacketType.Play.Server.WORLD_PARTICLES) {
                    public void onPacketSending(PacketEvent event) {
                        PacketContainer packet = event.getPacket();
                        if (event.getPacketType() == PacketType.Play.Server.WORLD_PARTICLES) {
                            if ((packet.getNewParticles().read(0)).getParticle() == Particle.DAMAGE_INDICATOR) {
                                event.setCancelled(true);
                            }
                        }
                    }
                });
    }

    @Override
    public void onDisable() {
    }
}
