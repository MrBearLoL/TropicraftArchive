package net.tropicraft.core.registry;

import net.minecraft.entity.Entity;
import net.tropicraft.Tropicraft;
import net.tropicraft.core.common.entity.EntityLavaBall;
import net.tropicraft.core.common.entity.hostile.EntityEIH;
import net.tropicraft.core.common.entity.hostile.EntityIguana;
import net.tropicraft.core.common.entity.hostile.EntityTreeFrogBlue;
import net.tropicraft.core.common.entity.hostile.EntityTreeFrogGreen;
import net.tropicraft.core.common.entity.hostile.EntityTreeFrogRed;
import net.tropicraft.core.common.entity.hostile.EntityTreeFrogYellow;
import net.tropicraft.core.common.entity.hostile.EntityTropiCreeper;
import net.tropicraft.core.common.entity.hostile.EntityTropiSkeleton;
import net.tropicraft.core.common.entity.passive.EntityFailgull;
import net.tropicraft.core.common.entity.passive.EntityVMonkey;
import net.tropicraft.core.common.entity.placeable.EntityChair;
import net.tropicraft.core.common.entity.placeable.EntityUmbrella;
import net.tropicraft.core.common.entity.placeable.EntityWallStarfish;
import net.tropicraft.core.common.entity.projectile.EntityCoconutGrenade;
import net.tropicraft.core.common.entity.projectile.EntityPoisonBlot;
import net.tropicraft.core.common.entity.underdasea.EntityEagleRay;
import net.tropicraft.core.common.entity.underdasea.EntityManOWar;
import net.tropicraft.core.common.entity.underdasea.EntityMarlin;
import net.tropicraft.core.common.entity.underdasea.EntitySeaUrchin;
import net.tropicraft.core.common.entity.underdasea.EntitySeaUrchinEgg;
import net.tropicraft.core.common.entity.underdasea.EntitySeahorse;
import net.tropicraft.core.common.entity.underdasea.EntityStarfish;
import net.tropicraft.core.common.entity.underdasea.EntityStarfishEgg;
import net.tropicraft.core.common.entity.underdasea.EntityTropicalFish;

public class EntityRegistry {

	private static int entityID = 0;

	public static void init() {
		registerEntity(EntityEIH.class, "EasterIslandHead", 80, 3, true);
		registerEntity(EntityTropiCreeper.class, "TropiCreeper", 80, 3, true);
		registerEntity(EntityIguana.class, "Iguana", 80, 3, true);
		registerEntity(EntityTreeFrogGreen.class, "TreeFrogGreen", 80, 3, true);
		registerEntity(EntityTreeFrogRed.class, "TreeFrogRed", 80, 3, true);
		registerEntity(EntityTreeFrogBlue.class, "TreeFrogBlue", 80, 3, true);
		registerEntity(EntityTreeFrogYellow.class, "TreeFrogYellow", 80, 3, true);
		registerEntity(EntityTropiSkeleton.class, "TropiSkeleton", 80, 3, true);
		registerEntity(EntityVMonkey.class, "VMonkey", 80, 3, true);
		registerEntity(EntityPoisonBlot.class, "PoisonBlot", 32, 1, true);
		registerEntity(EntityMarlin.class, "Marlin", 80, 3, true);
		registerEntity(EntityLavaBall.class, "Lava Ball", 120, 4, true);
		registerEntity(EntitySeahorse.class, "Seahorse", 80, 3, true);
		registerEntity(EntityFailgull.class, "Failgull", 80, 3, true);
		registerEntity(EntityChair.class, "beachChair", 120, 10, true);
		registerEntity(EntityUmbrella.class, "beachUmbrella", 120, 10, false);
		registerEntity(EntityCoconutGrenade.class, "CoconutBomb", 120, 5, true);
		registerEntity(EntityTropicalFish.class, "TropicalFish", 80, 3, true);
		registerEntity(EntityManOWar.class, "MOW", 64, 3, true);
		registerEntity(EntityEagleRay.class, "EagleRay", 80, 1, true);
		registerEntity(EntitySeaUrchin.class, "SeaUrchin", 64, 3, true);
		registerEntity(EntitySeaUrchinEgg.class, "SeaUrchinEgg", 64, 3, false);
		registerEntity(EntityStarfish.class, "Starfish", 64, 3, false);
		registerEntity(EntityStarfishEgg.class, "StarfishEgg", 64, 3, false);
		//registerEntity(EntityWallStarfish.class, "WallStarfish", 160, Integer.MAX_VALUE, false);
	}
	
	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entityClass, entityName, entityID++, Tropicraft.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
	
}
