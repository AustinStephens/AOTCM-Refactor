public abstract class Tile {
  private final int tileWidth = 50;
  private final int tileHeight = 50;
  public int x;
  public int y;
  public int cost;
  public boolean isOccupied;
  public boolean hasUnits = false;
  
  public Tile(boolean isOccupied) {
    this.isOccupied = isOccupied;
  }
  
  public abstract void display();
  
  public abstract boolean buildable();
  public abstract boolean canHaveUnits();
}

public class BuildTile extends Tile {
  
  public BuildTile(PVector p) {
    super(false);
    cost = 100;
    x = (int)p.x;
    y = (int)p.y;
  }
  
  public void display() {
    noFill();
    rect(x, y, tileWidth, tileHeight);
    fill(255);
  }
  
  public boolean buildable() {return true;}
  public boolean canHaveUnits() {return false;}
}

public class UnitTile extends Tile {
  public UnitTile(PVector p) {
    super(true);
    cost = 2;
    x = (int)p.x;
    y = (int)p.y;
  }
  
  public void display() {
    noFill();
    rect(x, y, tileWidth, tileHeight);
    fill(255);
  }
  
  public boolean buildable() {return false;}
  public boolean canHaveUnits() {return true;}
}
