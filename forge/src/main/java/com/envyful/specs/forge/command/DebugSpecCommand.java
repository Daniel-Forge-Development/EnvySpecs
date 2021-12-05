package com.envyful.specs.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;

@Command(
        value = "debugspec",
        description = "Checks if the parsed spec matches the entity being looked at",
        aliases = {
                "dbspec",
                "dspec",
                "spectest"
        }
)
@Permissible("envy.specs.forge.command.debug")
public class DebugSpecCommand {

    @CommandProcessor(executeAsync = false)
    public void onCommand(@Sender EntityPlayerMP sender, String[] args) {
        Vec3d start = sender.getPositionVector().add(0, sender.getEyeHeight(), 0);
        Vec3d direction = sender.getLookVec().scale(10);

        EntityArrow arrow = new EntityTippedArrow(sender.getEntityWorld());
        arrow.setPosition(sender.posX, sender.posY, sender.posZ);
        arrow.motionX = direction.x;
        arrow.motionY = direction.y;
        arrow.motionZ = direction.z;
        arrow.setEntityBoundingBox(sender.getEntityBoundingBox());
        RayTraceResult rayTraceResult = ProjectileHelper.forwardsRaycast(arrow, true, false, sender);

        if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.ENTITY) {
            sender.sendMessage(new TextComponentString("§c§l(!) §cFailed to find entity in line of sight!"));
            return;
        }

        Entity entityHit = rayTraceResult.entityHit;

        if (!(entityHit instanceof EntityPixelmon)) {
            sender.sendMessage(new TextComponentString("§c§l(!) §cThe entity you're looking at is not a pokemon (§e" + entityHit.getClass().getSimpleName() + "§c)."));
            return;
        }

        EntityPixelmon pixelmon = (EntityPixelmon) entityHit;
        PokemonSpec spec = PokemonSpec.from(args);
        boolean match = spec.matches(pixelmon);
        sender.sendMessage(new TextComponentString("§e§l(!) §eThe spec " + (match ? "§a§lmatches" : "§c§ldoesn't match") + "§ethe entity you're looking at"));
        sender.sendMessage(new TextComponentString("§e§l(!) §ePokemon: §b" + pixelmon.getPokemonName()));

        if (!match) {
            sender.sendMessage(new TextComponentString("§e§l(!) §eSpec: \n§a" + spec.toString()));
        }
     }
}
