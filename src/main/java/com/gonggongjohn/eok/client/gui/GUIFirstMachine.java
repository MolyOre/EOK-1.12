package com.gonggongjohn.eok.client.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.capabilities.ISeconds;
import com.gonggongjohn.eok.handlers.CapabilityHandler;
import com.gonggongjohn.eok.inventory.ContainerFirstMachine;
import com.gonggongjohn.eok.network.PacketGuiButton;
import com.gonggongjohn.eok.network.PacketSeconds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GUIFirstMachine extends GuiContainer {
    private static final String TEXTURE_BACK= EOK.MODID+":"+"textures/gui/container/elementary_research_table.png";
    private static final String TEXTURE_COMP=EOK.MODID+":"+"textures/gui/container/elementary_research_table_components.png";
    private static final ResourceLocation TEXTUREBACK = new ResourceLocation(TEXTURE_BACK);
    private static final ResourceLocation TEXTURECOMP = new ResourceLocation(TEXTURE_COMP);
    private ContainerFirstMachine containerFM;
    public GUIFirstMachine(ContainerFirstMachine inventorySlotIn)
    {
        super(inventorySlotIn);
        this.xSize=256;
        this.ySize=192;
        this.containerFM=inventorySlotIn;
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTUREBACK);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX,offsetY,0,0,xSize,ySize);
        GL11.glPopMatrix();
    }
    @Override
    public void initGui(){
        super.initGui();
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.buttonList.add(new GuiButton(0, offsetX + 225, offsetY + 114, 22, 22, ""){
            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
                if(this.visible){
                    GlStateManager.color(1.0F, 1.0F, 1.0F);
                    mc.getTextureManager().bindTexture(TEXTURECOMP);
                    this.drawTexturedModalRect(this.x, this.y, 0, 0, this.width, this.height);
                }
            }
        });
    }
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if(button.id == 0){
            EntityPlayer player=Minecraft.getMinecraft().player;
            PacketSeconds message =new PacketSeconds();
            if(player.hasCapability(CapabilityHandler.capSeconds,null))
            {
                ISeconds seconds=player.getCapability(CapabilityHandler.capSeconds,null);
                Capability.IStorage<ISeconds> storage=CapabilityHandler.capSeconds.getStorage();
                int tmp=seconds.getSecondsValue();
                seconds.setSecondsValue(tmp+1);
                message.compound=new NBTTagCompound();
                message.compound.setTag("seconds",storage.writeNBT(CapabilityHandler.capSeconds,seconds,null));
                EOK.getNetwork().sendToServer(message);
            }
            //EOK.getNetwork().sendToServer(new PacketGuiButton(button.id));
        }
    }

}
