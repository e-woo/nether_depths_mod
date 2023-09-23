package net.moistti.volcanic_caves.blocks;

import net.minecraft.block.Block;

public class Netherstone extends Block {
    public Netherstone(Settings settings) {
        super(settings.strength(3.5f).requiresTool());
    }
}
