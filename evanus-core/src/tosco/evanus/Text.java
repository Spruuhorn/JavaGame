package tosco.evanus;

import java.util.HashSet;

import java.util.Set;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import interfaces.Clickable;
import interfaces.Hoverable;

public class Text extends HUD {
	
	public static int DEFAULT = 0;
	public static int DIALOGUE = 1;
	public static int PLAIN = 2;
	
	private BackgroundHUD bg;
	private String text;
	private BitmapFont font;
	
	private GlyphLayout layout;
	
	private Set<TextOption> options;
	
	private Texture texture = new Texture("nineSlice.png");
	private float padding = 18;
	private float br = 24;
	
	private class TextOption extends HUD implements Clickable, Hoverable {
		private String text;
		private String path;
		
		private BitmapFont font;
		private GlyphLayout layout;
		private Text textbox;
		
		public TextOption(String text, String path, Text textbox) {
	        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("joystix monospace.ttf"));
	        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	        parameter.size = 14;
	        font = generator.generateFont(parameter);
	        generator.dispose();
			
	        /* Align x */
	        this.x = textbox.x + textbox.padding;
	        this.text = text;
	        this.textbox = textbox;
	        
	        layout = new GlyphLayout();
			layout.setText(font, text);
			
        	rect = new Rectangle(x, y, layout.width, layout.height);
	        rect.setWidth(layout.width);
	        rect.setHeight(layout.height);
	        rect.setX(x);
		}

		@Override
		public void onHover(int x, int y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onHoverOff() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onClick(int x, int y) {
			System.out.println("Clicked Option: " + text);
		}

		@Override
		public void draw(SpriteBatch batch) {
			// TODO Auto-generated method stub
			font.draw(batch, layout, x, y);
		}
		
		@Override
		public void setY(float y) {
			this.y = textbox.y + textbox.rect.height - y;
			rect.setY(this.y - this.rect.height);
		}
		
		public GlyphLayout getLayout() {
			return layout;
		}
	}
	
	public Text() {
		this.active = false;
	}
	
	public Text(float x, float y, String text, Texture texture) {
        this.x = x;
        this.y = y;
        this.text = text;
        
        generate();
	}
	
	/* For distinguishing Text box type */
	public Text(float x, float y, String text, Texture texture, int type) {
        this.x = x;
        this.y = y;
        this.text = text;
        
        if (type == DIALOGUE) {
        	options = new HashSet<TextOption>();
        }
        
        if(type != PLAIN) {
            generate();
        } else {
        	this.texture = null;
        }
	}

	@Override
	public void draw(SpriteBatch batch) {
		bg.draw(batch);
		font.draw(batch, layout, x + padding, rect.y + rect.height - padding);
		
		if (options != null) {
			for(TextOption option : options) {
				option.draw(batch);
			}
		}
	}
	
	public void replace(float x, float y, String text) {
		this.x = x;
		this.y= y;
		this.text = text;
        
        generate();
	}
	
	public void addTextOption(String text) {
		
		/* calculate hieght of text first, then place textoption */
		TextOption option = new TextOption(text, null, this);
		options.add(option);
		
		generate();
		
		/* Generates text options from bottom up */
		option.setY(padding + layout.height + br);
	}
	
	/* Can be used whenever the size of the nine batch has to be changed or initialized */
	private void generate() {
		
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("joystix monospace.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 14;
        font = generator.generateFont(parameter);
        generator.dispose();
		
        /* If the method is being used to initialize */
		if(layout == null) {
			layout = new GlyphLayout();
		}
		
		layout.setText(font, text);
        float bgWidth = layout.width + (padding * 2);
        float bgHeight = layout.height + (padding * 2);
        
        if(options != null && options.size() != 0) { 
	        for(TextOption option : options) {
	        	bgHeight += option.getLayout().height + br;
	        }
        }
        
        if(bg == null) {
            bg = new BackgroundHUD(x, y, bgWidth, bgHeight, texture, 28, 28, 28, 28);
        }
        
        if(rect == null) {
        	rect = new Rectangle(x, y, bgWidth, bgHeight);
        }
        
        bg.setWidth(bgWidth);
        bg.setHeight(bgHeight);
        bg.setX(x);
        bg.setY(y);
        
        rect.setWidth(bgWidth);
        rect.setHeight(bgHeight);
        rect.setX(x);
        rect.setY(y);
		
	}
	
	private void generate(String f, int size) {
		
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(f));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        font = generator.generateFont(parameter);
        generator.dispose();
		
        /* If the method is being used to initialize */
		if(layout == null) {
			layout = new GlyphLayout();
		}
		
		layout.setText(font, text);
        float bgWidth = layout.width + (padding * 2);
        float bgHeight = layout.height + (padding * 2);
        
        if(bg == null) {
            bg = new BackgroundHUD(x, y, bgWidth, bgHeight, texture, 28, 28, 28, 28);
        }
        
        if(rect == null) {
        	rect = new Rectangle(x, y, bgWidth, bgHeight);
        }
        
        bg.setWidth(bgWidth);
        bg.setHeight(bgHeight);
        bg.setX(x);
        bg.setY(y);
        
        rect.setWidth(bgWidth);
        rect.setHeight(bgHeight);
        rect.setX(x);
        rect.setY(y);
	}
	
	@Override
	public void drawRectangle(ShapeRenderer shapeRenderer) {
		if(rect != null) {
			shapeRenderer.rect(x, y, rect.width, rect.height);
		}
	}
}
