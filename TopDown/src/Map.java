import java.util.*;

public class Map {

    private int xSize, ySize, NumTiles, CurrentX, CurrentY, DesiredX, DesiredY, StartX, StartY, moveX, moveY;
    
    private boolean Left, Right, Up, Down;
    
    private List<Tile> tiles = new ArrayList<Tile>();
    
    public Map(int xsize, int ysize, int nmrm) {
        
        xSize = xsize;
        ySize = ysize;
        NumTiles = nmrm;
        
        moveX = 0;
        moveY = 0;
        
        GenerateTiles();
        
    }
    
    void GenerateTiles() {
        
        StartX = GameObject.RandomNum(xSize);
        StartY = GameObject.RandomNum(ySize);
        
        //Generate starting tile
        tiles.add(new Tile("start", StartX, StartY, "Floor"));
        
        CurrentX = StartX;
        CurrentY = StartY;
        
        while(tiles.size() < NumTiles) {
            
            ChooseDirection();
            GenerateTile();
            
        }
        
        //GENERATE MAP
        GenerateMap();
        ConvertToWorldSpace();
        
    }
    
    void ChooseDirection() {
        moveX = 0;
        moveY = 0;
        
        switch(GameObject.RandomNum(4)) {
        //LEFT
        case 0:
            moveX = -1;
            Left = true;
            break;
        //RIGHT
        case 1:
            moveX = 1;
            Right = true;
            break;
        //UP
        case 2:
            moveY = 1;
            Up = true;
            break;
        //DOWN
        case 3:
            moveY = -1;
            Down = true;
            break;
        }
    }

    void GenerateTile() {
        
        //set the desired coordinates
        DesiredX = CurrentX + moveX;
        DesiredY = CurrentY + moveY;
        
        //check if space is clear to place tile
        if(SpaceIsClear(DesiredX, DesiredY) && !OutOfBounds(DesiredX, DesiredY)) {
        
            Tile NewTile = new Tile("tile", DesiredX, DesiredY, "Floor");
            tiles.add(NewTile);
            
            //Set new current coordinates
            CurrentX = DesiredX;
            CurrentY = DesiredY;
        
        } else {
            
            //checks if a tile is stuck in the middle of tiles
            if(Left && Right && Up && Down) {
                
                //start creating new tiles from a different existing tile
                NewTileBranch();
                GenerateTile();
                
            } else {
                
                ChooseDirection();
                GenerateTile();
                
            }
        }
    }

    boolean SpaceIsClear(int x, int y) {
        
        for(Tile tile : tiles) {
            
            if(tile.getX() == x && tile.getY() == y) {
                return false;
            }
            
        }
        
        return true;
        
    }
    
    boolean OutOfBounds(int x, int y) {
        if(x >= 0 && x < xSize && y >= 0 && y < ySize) {
            return false;
        }
        return true;
    }
    
    void GenerateMap() {
        
        for(int y = 0; y < ySize; y++) {
            
            for(int x = 0; x < xSize; x++) {
                
                if(!SpaceIsClear(x, y)) {
                    
                    Tile StartingTile = getTile(x,y);
                    if(StartingTile.getTileType().equals("start")) {
                    	
                        System.out.print("S");
                        
                    } else {
                    
                        System.out.print("X");
                    
                    }
                    
                } else {
                    
                    System.out.print("-");
                    
                }
                
            }
            
            System.out.println();
            
        }
        
    }
    
    public void NewTileBranch() {
        int RandTile = GameObject.RandomNum(tiles.size());
        
        CurrentX = (int) tiles.get(RandTile).getX();
        CurrentY = (int) tiles.get(RandTile).getY();
        
        Left = false;
        Right = false;
        Up = false;
        Down = false;
        
        ChooseDirection();
        
    }
    
    public Tile getTile(int x, int y) {
        for(Tile tile : tiles) {
            if(x == tile.getX() && y == tile.getY()) {
                return tile;
            }
        }
        return null;
    }
    
    public void ConvertToWorldSpace() {
    	for(Tile tile : tiles) {
    		tile.setX(tile.getX() * 64);
    		tile.setY(tile.getY() * 64);
    	}
    }
    
    public double getStartX() {
    	return StartX * 64;
    }
    
    public double getStartY() {
    	return StartY * 64;
    }
    
}



