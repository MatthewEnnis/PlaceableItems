package com.stuntmania.PlaceableItems.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.SaddleStandTileEntity;

public class SaddleStandBlock extends BlockContainer {

	private IIcon icon;
	
	public SaddleStandBlock(Material p_i45386_1_) {
		super(p_i45386_1_);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new SaddleStandTileEntity();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon(PlaceableItems.MODID + ":saddleStand");
	}
	
	@Override
	public IIcon getIcon(int face, int meta) {
		return icon; //TODO FIX BROKEN PARTICLE EFFECTS
	}
}