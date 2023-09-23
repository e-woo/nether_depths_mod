//package net.moistti.volcanic_caves;
//
//
//import com.mojang.datafixers.util.Pair;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.registry.RegistryKey;
//import net.minecraft.registry.RegistryKeys;
//import net.minecraft.util.Identifier;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.source.util.MultiNoiseUtil;
//import terrablender.api.ParameterUtils;
//import terrablender.api.ParameterUtils.*;
//import terrablender.api.Region;
//import terrablender.api.RegionType;
//import terrablender.api.VanillaParameterOverlayBuilder;
//import java.util.function.Consumer;
//
//public class NetherDepths extends Region {
//    public NetherDepths(Identifier name, int weight) {
//        super(name, RegionType.NETHER, weight);
//    }
//
//    @Override
//    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper)
//    {
//        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
//
//        new ParameterUtils.ParameterPointListBuilder()
//                .temperature(Temperature.HOT)
//                .humidity(Humidity.ARID)
//                .depth(Depth.UNDERGROUND)
//                .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)
//                .build().forEach(point -> builder.add(point, RegistryKey.of(RegistryKeys.BIOME, new Identifier(VolcanicCaves.MOD_ID, "nether_depths"))));
//
//        // Add our points to the mapper
//        builder.build().forEach(mapper::accept);
//    }
//}
