/*
 * This file is part of NoNetherFun.
 *
 * NoNetherFun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoNetherFun is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NoNetherFun.  If not, see <https://www.gnu.org/licenses/>.
 */

package lol.hyper.nonetherfun;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoNetherFun extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block placed = event.getBlock();
        if (placed.getWorld().getEnvironment() == World.Environment.NETHER) {
            if (placed.getY() >= 128) {
                Bukkit.getLogger()
                        .info(event.getPlayer().getName() + " tried placing a block above the nether roof. Location: "
                                + event.getPlayer().getLocation());
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block placed = event.getBlockPlaced();
        if (placed.getWorld().getEnvironment() == World.Environment.NETHER) {
            if (placed.getY() >= 128) {
                Bukkit.getLogger()
                        .info(event.getPlayer().getName() + " tried breaking a block above the nether roof. Location: "
                                + event.getPlayer().getLocation());
                event.setCancelled(true);
            }
        }
    }
}
