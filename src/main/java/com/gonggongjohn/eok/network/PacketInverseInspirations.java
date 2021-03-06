package com.gonggongjohn.eok.network;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.capabilities.IInspirations;
import com.gonggongjohn.eok.handlers.CapabilityHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketInverseInspirations implements IMessage {
    public NBTTagCompound compound;

    @Override
    public void fromBytes(ByteBuf buf) {
        compound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, compound);
    }

    public static class Handler implements IMessageHandler<PacketInverseInspirations, IMessage> {

        @Override
        public IMessage onMessage(PacketInverseInspirations message, MessageContext ctx) {
            if(ctx.side == Side.SERVER) {
                final NBTBase nbt = message.compound.getTag("inspirations");
                Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        EntityPlayer player = EOK.getProxy().getPlayer(ctx);
                        if (player.hasCapability(CapabilityHandler.capInspirations, null)) {
                            IInspirations inspirations = player.getCapability(CapabilityHandler.capInspirations, null);
                            Capability.IStorage<IInspirations> storage = CapabilityHandler.capInspirations.getStorage();
                            storage.readNBT(CapabilityHandler.capInspirations, inspirations, null, nbt);
                        }
                    }
                });
            }
            return null;
        }
    }
}
