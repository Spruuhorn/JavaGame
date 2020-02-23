public class Tile extends GameObject{
	
	private String tileType;
    
    public Tile(String type, double xo, double yo, String image) {
    	
		super(xo, yo, image);
		
		tileType = type;
		
	}
    
    public String getTileType() {
    	return tileType;
    }
}



