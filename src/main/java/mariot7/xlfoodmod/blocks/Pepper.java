package mariot7.xlfoodmod.blocks;

import java.util.Random;

import mariot7.xlfoodmod.Main;
import mariot7.xlfoodmod.config.Configurationxlfoodmod;
import mariot7.xlfoodmod.init.ItemListxlfoodmod;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Pepper extends BlockCrops {
	
	protected String name;
	
	public Pepper(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	public Item getSeed() {
		return ItemListxlfoodmod.pepper_seeds;
	}
	
	public Item getCrop() {
		return ItemListxlfoodmod.pepper;
	}
	
	@Override
    public int quantityDropped(Random rand) {
		return rand.nextInt(3) + 1;
    }
	
	public void registerItemModel(Item itemBlock) {
		Main.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
	
	@Override
	protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.FARMLAND;
    }
	
	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), EnumFacing.UP, this);
    }
	
	
}
