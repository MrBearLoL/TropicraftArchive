package net.tropicraft.core.common.biome.decorators;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.tropicraft.core.common.biome.BiomeGenTropicraft;
import net.tropicraft.core.common.worldgen.WorldGenTallTree;
import net.tropicraft.core.common.worldgen.WorldGenTualang;
import net.tropicraft.core.common.worldgen.WorldGenUndergrowth;
import net.tropicraft.core.common.worldgen.WorldGenUpTree;

public class BiomeDecoratorRainforest extends BiomeDecoratorTropicraft {

	private static final int COFFEE_PLANT_AMOUNT = 2;
	private static final int ALTAR_CHANCE = 70;
	private static final int TALL_TREE_CHANCE = 2;
	private static final int UP_TREE_CHANCE = 2;
	private static final int UNDERGROWTH_AMOUNT = 15;
	private static final int SMALL_TUALANG_AMOUNT = 4;
	private static final int LARGE_TUALANG_AMOUNT = 2;
	private static final int HOME_TREE_RARITY = 80;
	
	public BiomeDecoratorRainforest() {

	}

    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos)
    {
        if (this.decorating)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            this.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(worldIn.getWorldInfo().getGeneratorOptions()).build();
            this.chunkPos = pos;
            this.genDecorations(biome, worldIn, random);
            this.decorating = false;
        }
    }
    
    public void genDecorations(Biome biome, World world, Random rand) {
    	int x = chunkPos.getX();
		int z = chunkPos.getZ();
		int i = 0; int k = 0;

		if (BiomeGenTropicraft.DISABLEDECORATION) {
			System.out.println("decoration disabled via BiomeGenTropics.DISABLEDECORATION, " + this);
			return;
		}
//
//		if(rand.nextInt(HOME_TREE_RARITY) == 0)
//		{
//			int cx = x/* * 16*/;
//		//	System.out.println("Trying!!!");
//			int cz = z/* * 16*/;
//			int xx = rand.nextInt(16) + cx + 8;
//			int zz= rand.nextInt(16) + cz + 8;
//			new WorldGenHomeTree(world, rand).generate(xx, 0, zz);
//		}
//
//		if(rand.nextInt(ALTAR_CHANCE) == 0) {
//			new WorldGenForestAltarRuin(world, rand).generate(randCoord(rand, x, 16), 0, randCoord(rand, x, 16));
//		}
//
		if (rand.nextInt(TALL_TREE_CHANCE) == 0) {
			i = randCoord(rand, x, 16);
			k = randCoord(rand, z, 16);
			new WorldGenTallTree(world, rand).generate(new BlockPos(i, getTerrainHeightAt(world, i, k), k));
		}

		if (rand.nextInt(UP_TREE_CHANCE) == 0) {
			i = randCoord(rand, x, 16);
			k = randCoord(rand, z, 16);
			new WorldGenUpTree(world, rand).generate(new BlockPos(i, getTerrainHeightAt(world, i, k), k));			
		}

		for (int a = 0; a < SMALL_TUALANG_AMOUNT; a++) {
			i = randCoord(rand, x, 16);
			k = randCoord(rand, z, 16);
			new WorldGenTualang(world, rand, 16, 9).generate(new BlockPos(i, getTerrainHeightAt(world, i, k), k));
		}

		for (int a = 0; a < LARGE_TUALANG_AMOUNT; a++) {
			i = randCoord(rand, x, 16);
			k = randCoord(rand, z, 16);
			new WorldGenTualang(world, rand, 25, 11).generate(new BlockPos(i, getTerrainHeightAt(world, i, k), k));
		}
//
		for (int a = 0; a < UNDERGROWTH_AMOUNT; a++) {
			i = randCoord(rand, x, 16);
			k = randCoord(rand, z, 16);
			new WorldGenUndergrowth(world, rand).generate(new BlockPos(i, getTerrainHeightAt(world, i, k), k));
		}
//
//		for(int a = 0; a < COFFEE_PLANT_AMOUNT; a++) {
//			int i = randCoord(rand, x, 16);
//			int k = randCoord(rand, z, 16);
//			new WorldGenCoffeePlant(world, rand).generate(i, getTerrainHeightAt(world, i, k), k);
//		}
    }
}
