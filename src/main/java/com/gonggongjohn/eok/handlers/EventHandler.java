package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.capabilities.CapabilityConsciousness;
import com.gonggongjohn.eok.capabilities.IConsciousness;
import com.gonggongjohn.eok.utils.EOKToolMaterials;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Mod.EventBusSubscriber(modid = EOK.MODID)
public class EventHandler {
    @SideOnly(Side.CLIENT)
	@SubscribeEvent
    public static void Tooltip(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack != null && !stack.isEmpty() && stack.hasTagCompound()) {
            NBTTagCompound compound = stack.getTagCompound();
            if(compound != null && compound.hasKey("data.universe")){
                int value = compound.getInteger("data.universe");
                List<String> tooltip = event.getToolTip();
                String str = "";
                str += I18n.format("tooltip.data.universe", value);
                tooltip.add(str);
            }
        }
    }

    
    
    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof EntityPlayer){
            ICapabilitySerializable<NBTTagCompound> provider = new CapabilityConsciousness.ProvidePlayer();
            event.addCapability(new ResourceLocation(EOK.MODID + ":" + "consciousness"), provider);
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
        Capability<IConsciousness> capability = CapabilityHandler.capConsciousness;
        Capability.IStorage<IConsciousness> storage = capability.getStorage();

        if(event.getOriginal().hasCapability(capability, null) && event.getEntityPlayer().hasCapability(capability, null)){
            NBTBase nbt = storage.writeNBT(capability, event.getOriginal().getCapability(capability, null), null);
            storage.readNBT(capability, event.getEntityPlayer().getCapability(capability, null), null, nbt);
        }
    }
}
