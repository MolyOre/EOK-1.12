package com.gonggongjohn.eok.commands;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.network.PacketTestGUIScreen;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTestGuiScreen extends CommandBase {

	@Override
	public String getName() {
		return "eoktestscreen";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.eoktestscreen.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer(sender);
		EOK.getNetwork().sendTo(new PacketTestGUIScreen(), player);
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 4;
	}

}
