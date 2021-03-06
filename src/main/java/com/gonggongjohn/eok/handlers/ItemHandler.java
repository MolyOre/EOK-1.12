package com.gonggongjohn.eok.handlers;

import java.util.ArrayList;
import java.util.List;

import com.gonggongjohn.eok.items.*;
import net.minecraft.item.Item;

public class ItemHandler {
	public static final List<Item> items = new ArrayList<Item>();

	public static final Item papyrus = new ItemPapyrus();
	public static final Item refractingTelescope = new ItemRefractingTelescope();
	public static final Item flintFragment = new ItemFlintFragment();
	public static final Item chippedFlintFragment = new ItemChippedFlintFragment();
	public static final Item polishedFlintFragment = new ItemPolishedFlintFragment();
	public static final Item roughFlintSpear = new ItemRoughFlintSpear();
	public static final Item Torcherino = new Torcherino();
	public static final Item MACHINE_GUN = new ItemMachineGun();
	public static final Item fireStick = new ItemFireStick();
	public static final Item hayTorch = new ItemHayTorch();
	public static final Item Virus = new ItemVirus();
	public static final Item EOKManual = new ItemEOKManual();
	
	//public static final Item convexLens = new ItemMaterialBase("convex_lens");
	//public static final Item concaveLens = new ItemMaterialBase("concave_lens");
	public static final Item eokSymbol = new ItemMaterialBase("eok_symbol");
	public static final Item plantFiber = new ItemMaterialBase("plant_fiber");
	public static final Item shortStick = new ItemMaterialBase("short_stick");
	public static final Item tube = new ItemMaterialBase("tube");
	public static final Item rubberMat = new ItemMaterialBase("rubber_mat");
	public static final Item simple_hemp_rope = new ItemMaterialBase("simple_hemp_rope");
	public static final Item strong_hemp_rope = new ItemMaterialBase("strong_hemp_rope");
	public static final Item driedHay = new ItemMaterialBase("dried_hay");
	public static final Item deadHayTorch = new ItemMaterialBase("dead_hay_torch");
}
