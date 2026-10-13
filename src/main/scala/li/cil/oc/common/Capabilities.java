package li.cil.oc.common;

import li.cil.oc.OpenComputers;
import li.cil.oc.api.internal.Colored;
import li.cil.oc.api.network.Environment;
import li.cil.oc.api.network.SidedEnvironment;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.energy.IEnergyStorage;

public final class Capabilities {
    private Capabilities() {}

    private static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(OpenComputers.ID(), path);
    }

    public static final BlockCapability<Environment, Direction> EnvironmentCapability =
            BlockCapability.createSided(location("environment"), Environment.class);

    public static final BlockCapability<SidedEnvironment, Direction> SidedEnvironmentCapability =
            BlockCapability.createSided(location("sided_environment"), SidedEnvironment.class);

    public static final BlockCapability<Colored, Direction> ColoredCapability =
            BlockCapability.createSided(location("colored"), Colored.class);

    public static final ItemCapability<IEnergyStorage, Void> EnergyItemCapability =
            ItemCapability.createVoid(ResourceLocation.withDefaultNamespace("energy"), IEnergyStorage.class);
}
