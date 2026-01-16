package com.diagonaltiles;

import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@PluginDescriptor(
    name = "Diagonal Tile Indicators",
    description = "Displays tile indicators on diagonal tiles from the player's true tile position",
    tags = {"tile", "indicator", "diagonal", "overlay", "highlight"}
)
public class DiagonalTilePlugin extends Plugin
{
    private static final Logger log = LoggerFactory.getLogger(DiagonalTilePlugin.class);

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private DiagonalTileOverlay overlay;

    @Override
    protected void startUp() throws Exception
    {
        log.info("Diagonal Tile Indicators started");
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        log.info("Diagonal Tile Indicators stopped");
        overlayManager.remove(overlay);
    }

    @Provides
    DiagonalTileConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(DiagonalTileConfig.class);
    }
}
