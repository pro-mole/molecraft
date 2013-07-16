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
		
		Cocoon.addBox(5.5f, -5f, -1f, 2, 1, 2);
		Cocoon.addBox(3.5f, -4f, -2f, 4, 2, 4);
		Cocoon.addBox(2.5f, -2f, -2.5f, 5, 6, 5);
		Cocoon.addBox(3.5f, 4f, -2f, 4, 2, 4);
		Cocoon.addBox(4.5f, 5f, -1.5f, 3, 1, 3);
		Cocoon.addBox(5.5f, 6f, -1f, 2, 1, 2);
		
		Cocoon.setRotationPoint(0.5f, 0f, 0.5f);
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
