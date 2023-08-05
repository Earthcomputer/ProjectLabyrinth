package net.earthcomputer.projectlabyrinth.entity;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SentientPistonRenderer extends MobRenderer<SentientPistonEntity, SentientPistonModel> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ProjectLabyrinth.MODID, "textures/entity/sentient_piston.png");

    public SentientPistonRenderer(EntityRendererProvider.Context context) {
        super(context, new SentientPistonModel(context.bakeLayer(SentientPistonModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(SentientPistonEntity pEntity) {
        return TEXTURE_LOCATION;
    }
}
