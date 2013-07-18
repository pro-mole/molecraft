package common.molecraft.bugfarm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCocoon extends ModelBase {

	ModelRenderer Cocoon;
	
	public ModelCocoon() {
		textureWidth = 16;
		textureHeight = 16;
		
		Cocoon = new ModelRenderer(this, 0, 0).setTextureSize(textureWidth, textureHeight);
		
		Cocoon.addBox(-8f, -6f, -2f, 2, 2, 4);
		Cocoon.addBox(-8f, -5f, -1f, 3, 1, 2);
		Cocoon.addBox(-8f, -4f, -3f, 3, 7, 6);
		Cocoon.addBox(-8f, -3f, -2f, 4, 5, 4);
		Cocoon.addBox(-8f, 3f, -2f, 2, 3, 4);
		Cocoon.addBox(-8f, 3f, -1f, 3, 2, 2);
		Cocoon.addBox(-8f, 6f, -1f, 1, 1, 2);
		
		Cocoon.setRotationPoint(0f, 0f, 0f);
	}
	
	@Override
	public void render(Entity entity, float x, float y, float z,
			float f1, float f2, float f3) {
		super.render(entity, x, y, z, f1, f2, f3);
		Cocoon.render(f3);
	}
	
	public void renderAll() {
		Cocoon.render(1/16f);
	}
}
