package tosco.evanus;

import java.util.HashSet;

import java.util.Set;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import interfaces.Clickable;
import interfaces.Hoverable;

public class InventoryHUD extends HUD {
	
	private BackgroundHUD bg;
	private Texture slot;
	private int padding;
	private int spacing;

	private Set<Slot> slots;
	
	protected class Slot extends HUD implements Hoverable, Clickable {
		private Texture tex;
		private TestSlotItem item;
		private Text hoverDescription;
		
		public Slot(float x, float y, Texture tex) {
			super(x, y, 1, tex.getWidth(), tex.getHeight());
			this.tex = tex;
			this.hoverDescription = new Text();
		}
		
		public void setItem(Texture item) {
			this.item = new TestSlotItem(x, y, item);
		}

		@Override
		public void draw(SpriteBatch batch) {
			batch.draw(tex, x, y);
			if(item != null) {
				// The sprites need to be centered in the slot
				item.draw(batch);
			}
		}

		@Override
		public void onHover(int screenX, int screenY) {
			if(item != null) {
				hoverDescription.replace(screenX, screenY, "Picture of " + item.getName());
				hoverDescription.setActive(true);
			}
		}
		
		@Override
		public void onHoverOff() {
			hoverDescription.setActive(false);
		}
		
		@Override
		public void onClick(int screenX, int screenY) {
			if (item != null) {
				InventoryManager.setSelected(item);
				InventoryManager.setLastSlot(this);
				item.setActive(true);
				item.setSlotted(false);
				item = null;
				hoverDescription.setActive(false);
			} else if (InventoryManager.getSelected() != null) {
				this.item = InventoryManager.getSelected();
				item.x = x;
				item.y = y;
				item.setSlotted(true);
				InventoryManager.setSelected(null);
			}
		}
		
		@Override
		public void setActive(boolean active) {
			if(!active) {
				hoverDescription.setActive(active);
			}
			if(item != null) {
				item.setActive(active);
			}
			this.active = active;
		}
	}
	
	public InventoryHUD(int rows, int cols, Texture texture) {
		slot = new Texture("slot.png");
		padding = 28;
		spacing = 6;
		float width = (cols * slot.getWidth()) + ((cols - 1) * spacing) + padding * 2;
		float height = (rows * slot.getHeight()) + ((rows - 1) * spacing) + padding * 2;
		x = Gdx.graphics.getWidth()/2 - width/2;
		y = Gdx.graphics.getHeight()/2 - height/2;
		
		bg = new BackgroundHUD(x, y, width, height, texture, 28, 28, 28, 28);
		
		slots = new HashSet<Slot>();
		for (int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				Slot s = new Slot(x + (col * slot.getWidth()) + (col * spacing) + padding, y + (row * slot.getHeight()) + (row * spacing) + padding, slot);
				if(row == 0 && col == 0) {
					s.setItem(new Texture("GodBlessThisImage.png"));
				}
				if(row == 1 && col == 3) {
					s.setItem(new Texture("black_widow.png"));
				}
				slots.add(s);
			}
		}
	}
	
	public void draw(SpriteBatch batch) {
		bg.draw(batch);
		
		/* Currently the calls to draw each slot are doubled
		 * The problem is the layering of the slots would be messed up
		 * if I got rid of this sequence of draw calls
		 * 
		 * I think I can get rid of the abstract draw method and 
		 * move it to an interface so that not everything that is 
		 * a gameobject has to draw.
		 */
		for (Slot slot : slots) {
			slot.draw(batch);
		}
	}
	
	public void setActive(boolean active) {
		for(Slot slot : slots) {
			slot.setActive(active);
		}
		this.active = active;
	}
}