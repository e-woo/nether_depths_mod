// Made with Blockbench 4.8.3
package net.moistti.nether_depths.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.moistti.nether_depths.entity.FireSpiritEntity;
import net.moistti.nether_depths.entity.animation.DepthsAnimations;

public class FireSpiritEntityModel<T extends FireSpiritEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	public FireSpiritEntityModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, new Dilation(-1.0F))
				.uv(0, 0).cuboid(-2.0F, -5.0F, -7.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(1.0F, -5.0F, -7.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tail = root.addChild("tail", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -2.0F, 8.0F, 8.0F, 8.0F, new Dilation(-2.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tail2 = root.addChild("tail2", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, 2.0F, 8.0F, 8.0F, 8.0F, new Dilation(-3.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(FireSpiritEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(DepthsAnimations.FIRE_SPIRIT_BOB, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.IDLE_ANIMATION, DepthsAnimations.FIRE_SPIRIT_BOB, ageInTicks, 1f);
	}

	private void setHeadAngles(float yaw, float pitch) {
		yaw = MathHelper.clamp(yaw, -30.0f, 30.0f);
		pitch = MathHelper.clamp(pitch, -25.0f, 45.0f);

		this.head.yaw = yaw * ((float) Math.PI / 180);
		this.head.pitch = pitch * ((float) Math.PI / 180);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}