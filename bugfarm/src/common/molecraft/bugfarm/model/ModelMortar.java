package common.molecraft.bugfarm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMortar extends ModelBase {

	ModelRenderer Bowl;
	ModelRenderer Rim;
	
	public ModelMortar() {
		
		textureWidth = 16;
		textureHeight = 16;
		
		Bowl = new ModelRenderer(this, 0, 0).setTextureSize(textureWidth, textureHeight);
		//Bowl.addBox(0f, 0f, 0f, 2, 1, 1);
		//Bowl.addBox(0f, 0f, 0f, 1, 2, 1);
		//Bowl.addBox(0f, 0f, 0f, 1, 1, 2);
		Bowl.addBox(-3.5f, -3f, -3.5f, 7, 7, 1);
		Bowl.addBox(-3.5f, -3f, -3.5f, 1, 7, 7);
		Bowl.addBox(3.5f, -3f, 3.5f, -7, 7, -1);
		Bowl.addBox(3.5f, -3f, 3.5f, -1, 7, -7);
		Bowl.addBox(-2.5f, 4f, -2.5f, 5, 2, 5);
		Bowl.addBox(-3.5f, 6f, -3.5f, 7, 1, 7);
		//Bowl.addBox(-8.5f, -8.5f, -8.5f, 16, 16, 16);
		Bowl.setRotationPoint(0.5f, 0, 0.5f);
		
		Rim = new ModelRenderer(this, 0, 0).setTextureSize(textureWidth, textureHeight);
		Rim.addBox(-3.5f, 0f, -4f, 7, 1, 1);
		Rim.addBox(-4f, 0f, -3.5f, 1, 1, 7);
		Rim.addBox(3.5f, 0f, 4f, -7, 1, -1);
		Rim.addBox(4f, 0f, 3.5f, -1, 1, -7);
		Rim.setRotationPoint(0.5f, 0f, 0.5f);
		//Rim.rotateAngleX = 0;
		//Rim.rotateAngleY = 0;
		//Rim.rotateAngleZ = 0;
	}
	
	@Override
	public void render(Entity entity, float x, float y, float z,
			float f1, float f2, float f3) {
		super.render(entity, x, y, z, f1, f2, f3);
		Bowl.render(f3);
		Rim.render(f3);
	}
	
	public void renderAll() {
		
		Bowl.render(1/16f);
		Rim.render(1/16f);
	}

}
