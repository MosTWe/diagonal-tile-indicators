package com.diagonaltiles;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DiagonalTilePluginTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(DiagonalTilePlugin.class);
        RuneLite.main(args);
    }
}
