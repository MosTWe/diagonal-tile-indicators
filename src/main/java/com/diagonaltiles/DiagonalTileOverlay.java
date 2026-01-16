package com.diagonaltiles;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

import javax.inject.Inject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;

public class DiagonalTileOverlay extends Overlay
{
    private static final int[][] DIAGONAL_OFFSETS = {
        {1, 1},   // NE
        {-1, 1},  // NW
        {1, -1},  // SE
        {-1, -1}  // SW
    };

    private final Client client;
    private final DiagonalTileConfig config;

    @Inject
    public DiagonalTileOverlay(Client client, DiagonalTileConfig config)
    {
        this.client = client;
        this.config = config;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.MED);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        Player localPlayer = client.getLocalPlayer();
        if (localPlayer == null)
        {
            return null;
        }

        WorldPoint playerLocation = localPlayer.getWorldLocation();
        if (playerLocation == null)
        {
            return null;
        }

        int range = config.tileRange();

        for (int distance = 1; distance <= range; distance++)
        {
            for (int[] offset : DIAGONAL_OFFSETS)
            {
                int dx = offset[0] * distance;
                int dy = offset[1] * distance;

                WorldPoint diagonalPoint = new WorldPoint(
                    playerLocation.getX() + dx,
                    playerLocation.getY() + dy,
                    playerLocation.getPlane()
                );

                renderTile(graphics, diagonalPoint);
            }
        }

        return null;
    }

    private void renderTile(Graphics2D graphics, WorldPoint worldPoint)
    {
        LocalPoint localPoint = LocalPoint.fromWorld(client, worldPoint);
        if (localPoint == null)
        {
            return;
        }

        Polygon tilePoly = Perspective.getCanvasTilePoly(client, localPoint);
        if (tilePoly == null)
        {
            return;
        }

        if (config.showFill())
        {
            Color fillColor = config.fillColor();
            graphics.setColor(fillColor);
            graphics.fill(tilePoly);
        }

        if (config.showBorder())
        {
            Color borderColor = config.borderColor();
            Stroke originalStroke = graphics.getStroke();
            graphics.setStroke(new BasicStroke(config.borderWidth()));
            graphics.setColor(borderColor);
            graphics.draw(tilePoly);
            graphics.setStroke(originalStroke);
        }
    }
}
