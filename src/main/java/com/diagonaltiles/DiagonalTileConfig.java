package com.diagonaltiles;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

import java.awt.Color;

@ConfigGroup("diagonaltiles")
public interface DiagonalTileConfig extends Config
{
    @ConfigSection(
        name = "Tile Settings",
        description = "Settings for diagonal tile indicators",
        position = 0
    )
    String tileSection = "tileSection";

    @ConfigItem(
        keyName = "tileRange",
        name = "Tile Range",
        description = "The range of diagonal tiles to display (1 = only adjacent diagonals, higher = further out)",
        position = 1,
        section = tileSection
    )
    @Range(min = 1, max = 10)
    default int tileRange()
    {
        return 1;
    }

    @Alpha
    @ConfigItem(
        keyName = "fillColor",
        name = "Fill Color",
        description = "The fill color of the diagonal tile indicators",
        position = 2,
        section = tileSection
    )
    default Color fillColor()
    {
        return new Color(0, 255, 255, 50);
    }

    @Alpha
    @ConfigItem(
        keyName = "borderColor",
        name = "Border Color",
        description = "The border color of the diagonal tile indicators",
        position = 3,
        section = tileSection
    )
    default Color borderColor()
    {
        return new Color(0, 255, 255, 255);
    }

    @ConfigItem(
        keyName = "borderWidth",
        name = "Border Width",
        description = "The width of the tile border",
        position = 4,
        section = tileSection
    )
    @Range(min = 1, max = 5)
    default int borderWidth()
    {
        return 2;
    }

    @ConfigItem(
        keyName = "showFill",
        name = "Show Fill",
        description = "Whether to show the fill color on tiles",
        position = 5,
        section = tileSection
    )
    default boolean showFill()
    {
        return true;
    }

    @ConfigItem(
        keyName = "showBorder",
        name = "Show Border",
        description = "Whether to show the border on tiles",
        position = 6,
        section = tileSection
    )
    default boolean showBorder()
    {
        return true;
    }
}
