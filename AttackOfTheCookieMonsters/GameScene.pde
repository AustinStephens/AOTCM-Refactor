public class GameScene extends Scene { 
  
  public Tile[][] grid = new Tile[worldRows][worldCols];
  
  public int cookieDough = 50;
  public int trefoils = 20;
  public int gems = 5;
  
  public int factoryHealth = 100;
  public ArrayList<Building> buildings = new ArrayList<Building>();
  public ArrayList<Unit> units = new ArrayList<Unit>();
  public float waveTimer = 60;
  public int wave = 0;
  
  public WaveState waveState = new PrepState(this);
  public ArrayList<Monster> monsters = new ArrayList<Monster>();
  public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  
  public void update() {
    image(Images.mapDesign, 0, 0);//, 1000, 510);
    
    for(int i = 0; i < worldCols; i++) {
      for(int j = 0; j < worldRows; j++) {
        grid[i][j].display();
      }
    }
    
    for(int i = 0; i < buildings.size(); i++) {
      buildings.get(i).update();
      buildings.get(i).display();
    }
    
    waveState.update();
    for(int i = monsters.size() - 1; i >= 0; i--) {
      monsters.get(i).update(this);
      if(monsters.get(i).isDead) {
        monsters.remove(i);
        cookieDough += 5;
      }
    }
    
    for(int i = units.size() - 1; i >= 0; i--) {
      units.get(i).update();
      units.get(i).display();
      if(units.get(i).isDead)
        units.remove(i);
    }
    
    for(int i = projectiles.size() - 1; i >= 0; i--) {
      projectiles.get(i).update();
      if(projectiles.get(i).isDead) {
        projectiles.remove(i);
      }
    }
    
    displayUI();
  }
  
  public void onSceneEnter() {
    initGrid();
  }
  public void onSceneExit(){
  }
  
  public void onMousePressed(){
    waveState.onClick();
  }
  public void onKeyPressed(){
  }
  
  public void initGrid() {
    for(int i = 0; i < worldCols; i++) {
      for(int j = 0; j < worldRows; j++) {
        grid[i][j] = new BuildTile(tileToCorner(i, j));
      }
    }
    
    for(int j = 0; j < worldRows; j++) {
      grid[0][j] = new UnitTile(tileToCorner(0, j));
      grid[1][j] = new UnitTile(tileToCorner(1, j));
      grid[2][j] = new UnitTile(tileToCorner(2, j));
    }
    
    for(int j = 3; j < worldRows - 3; j++) {
      grid[7][j] = new UnitTile(tileToCorner(7,j));
      grid[12][j] = new UnitTile(tileToCorner(12,j));
    }
    
    for(int i = 3; i <= 6; i++) {
      grid[i][9] = new UnitTile(tileToCorner(i,9));
      grid[i][10] = new UnitTile(tileToCorner(i,10));
    }
    for(int i = 13; i <= 16; i++) {
      grid[i][9] = new UnitTile(tileToCorner(i,9));
      grid[i][10] = new UnitTile(tileToCorner(i,10));
    }
    
    for(int i = 8; i <= 11; i++) {
      grid[i][3] = new UnitTile(tileToCorner(i,3));
      grid[i][4] = new UnitTile(tileToCorner(i,4));
      grid[i][15] = new UnitTile(tileToCorner(i,15));
      grid[i][16] = new UnitTile(tileToCorner(i,16));
    }
    
    for(int i = 0; i < worldRows; ++i) {
      for(int j = 0; j < worldCols; ++j) {
        PathTile pt = new PathTile();
        Tile t = grid[j][i];
        
        pt.cost = t.cost;
        pt.x = j;
        pt.y = i;
        
        Pathfinder.worldTiles[i][j] = pt;
      }
    }
    
    
    /////////////////////PLACE UNITS HERE///////////////////////////////////////////////////////////////
    
    placeBuilding(new CookieDoughMine(this, 12, 4), 12, 4);
    placeBuilding(new TrefoilMine(this, 14, 4), 14, 4);
    placeBuilding(new ToffeeTower(this, 12, 6), 12, 6);
    placeBuilding(new ToffeeTower(this, 13, 6), 13, 6);
    placeBuilding(new ToffeeTower(this, 17, 10), 17, 10);
    placeBuilding(new ToffeeTower(this, 17, 11), 17, 11);
    placeBuilding(new ThinMintTower(this, 2, 11), 2, 11);
    placeBuilding(new SmoresTower(this, 2, 12), 2, 12);
    placeUnit(new Samorai(this, 2, 10), 2, 10);
    placeUnit(new Samorai(this, 7, 12), 7, 12);
    placeUnit(new Archer(this, 1, 12), 1, 12);
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    for(int i = 0; i < worldRows; ++i) {
      for(int j = 0; j < worldCols; ++j) {
        Pathfinder.worldTiles[i][j].setNeighbors();
      }
    }
  }
  
  public void changeState(WaveState newState) {
    waveState.onExit();
    waveState = newState;
    waveState.onEnter();
  }
  
  public void placeBuilding(Building building, int j, int i) {
    boolean available = true;
    int maxI = 0;
    int maxJ = 0;
    
    if(i < 0 || j < 0) available = false;
    if(i + building.buildingHeight < worldRows) maxI = i + building.buildingHeight;
    else available = false;
    if(j + building.buildingWidth < worldCols) maxJ = j + building.buildingWidth;
    else available = false;
    for(int k = i; k < maxI && k < worldRows; k++) {
      for(int l = j; l < maxJ && l < worldCols; l++) {
          if(grid[i][j].isOccupied || !grid[i][j].buildable()) available = false;
      }
    }
    
    
    if(available) { 
      buildings.add(building);
    
      for(; i < maxI && i < worldRows; i++) {
        for(; j < maxJ && j < worldCols; j++) {
            grid[i][j].isOccupied = true;
        }
      }
    }
  }
  
  public void placeUnit(Unit unit, int i, int j) {
    boolean available = true;
    if(i > worldRows || i < 0) available = false;
    if(j > worldCols || j < 0) available = false;
    if(grid[i][j].hasUnits || !grid[i][j].canHaveUnits()) available = false;
    
    if(available) { 
      units.add(unit);
      grid[i][j].hasUnits = true;
      Pathfinder.worldTiles[j][i].cost = 10;
    }
  }
  
  private void displayUI() {
    image(Images.factoryLevelBar, 0, -70);
    image(Images.recipesBar, 50, 392, 150, 48);
    image(Images.upgradeBar, 800, 392, 150, 48);
    textSize(20);
    fill(0);
    image(Images.CookieDoughConcept, 250, 5, 40, 40);
    text(cookieDough, 300, 35);
    image(Images.TrefoilsConcept, 450, 5, 40, 40);
    text(trefoils, 500, 35);
    image(Images.GemsConcept, 650, 5, 40, 40);
    text(gems, 700, 35);
    PVector p = tileToCorner(17,7);
    image(Images.Factory_Design_Lvl2, p.x - tileWidth * .7, p.y - tileHeight * .3, tileWidth * 3.5, tileHeight * 6);
    waveState.displayTimer();
    fill(255);
  }
}
