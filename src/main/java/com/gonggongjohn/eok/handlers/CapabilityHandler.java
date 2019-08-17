package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.capabilities.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHandler {
    @CapabilityInject(IConsciousness.class)
    public static Capability<IConsciousness> capConsciousness;
    @CapabilityInject(IMindActivity.class)
    public static Capability<IMindActivity> capMindActivity;
    @CapabilityInject(IResearchData.class)
    public static Capability<IResearchData> capResearchData;

    public static void setupCapabilities(){
        CapabilityManager.INSTANCE.register(IConsciousness.class, new CapabilityConsciousness.Storage(), CapabilityConsciousness.Implementation.class);
        CapabilityManager.INSTANCE.register(IMindActivity.class, new CapabilityMindActivity.Storage(), CapabilityMindActivity.Implementation.class);
        CapabilityManager.INSTANCE.register(IResearchData.class, new CapabilityResearchData.Storage(), CapabilityResearchData.Implementation.class);
    }
}