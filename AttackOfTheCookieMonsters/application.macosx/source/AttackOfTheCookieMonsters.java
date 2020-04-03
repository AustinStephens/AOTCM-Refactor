import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.ArrayList; 
import java.util.Queue; 
import java.util.LinkedList; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AttackOfTheCookieMonsters extends PApplet {





Scene scene;

final int worldRows = 20;
final int worldCols = 20;

final int tileHeight = 17;
final int tileWidth = 46;

final int gridStartX = 0;
final int gridStartY = 51;

public void setup() {
  
  initImages();
  scene = new MainMenu();
  scene.onSceneEnter();
}

public void draw() {
  background(255);
  Time.update(millis());
  scene.update();
}

public void mousePressed() {
  scene.onMousePressed();
  print(mouseX + ", " + mouseY + "   ");
  print();
}

public void keyPressed() {
  scene.onKeyPressed();
}

public PVector tileToPoint(PVector p) {
  return new PVector((p.x + 0.5f)* tileWidth + gridStartX, (p.y + 0.5f) * tileHeight + gridStartY);
}

public PVector tileToCorner(int x, int y) {
  return new PVector(x * tileWidth + gridStartX, y * tileHeight + gridStartY);
}

public void initImages()
{
  Images.titleImage = loadImage("title_screen.png");
  Images.recipesBar = loadImage("recipe_bar.png");
  Images.upgradeBar = loadImage("upgrade_bar.png");
  Images.factoryLevelBar = loadImage("factory_lvl_bar.png");
  Images.mapDesign = loadImage("map_design.png");
  Images.Bakery_Design_Lvl2 = loadImage("Bakery_Design_Lvl2.png");
  Images.Factory_Design_Lvl2 = loadImage("Factory_Design_Lvl2.png");
  Images.Research_Design_Lvl1 = loadImage("Research_Design_Lvl1.png");
  Images.VaultDesign = loadImage("VaultDesign.png");
  Images.CookieDoughConcept = loadImage("CookieDoughConcept.png");
  //Images.Wall_LVL1 = loadImage("Wall_LVL1.png");
  Images.TrefoilsConcept = loadImage("TrefoilsConcept.png");
  Images.GemsConcept = loadImage("GemsConcept.png");
  //Images.WoodConcept = loadImage("WoodConcept.png");
  Images.ArcherAttackFrame = loadImage("ArcherAttackFrame.png");
  Images.ArcherFrame1 = loadImage("ArcherFrame.png");
  Images.SamoraiAttackFrame = loadImage("SamoraiAttackFrame.png");
  Images.SamoraiFrame1 = loadImage("SamoraiFrame.png");
  Images.FlyerAttackFrame = loadImage("FlyerAttackFrame.png");
  Images.FlyerFrame1 = loadImage("FlyerFrame1.png");
  Images.FlyerFrame2 = loadImage("FlyerFrame2.png");
  Images.FlyerFrame3 = loadImage("FlyerFrame3.png");
  Images.InfantryAttackFrame = loadImage("InfantryAttackFrame.png");
  Images.InfantryFrame1 = loadImage("InfantryFrame1.png");
  Images.InfantryFrame2 = loadImage("InfantryFrame2.png");
  Images.InfantryFrame3 = loadImage("InfantryFrame3.png");
  Images.TankAttackFrame = loadImage("TankAttackFrame.png");
  Images.TankFrame1 = loadImage("TankFrame1.png");
  Images.TankFrame2 = loadImage("TankFrame2.png");
  Images.TankFrame3 = loadImage("TankFrame3.png");
  Images.SmoresProjectile = loadImage("SmoresProjectile.png");
  Images.ThinMintProjectile = loadImage("ThinMintProjectile.png");
  Images.ToffeeProjectile = loadImage("ToffeeProjectile.png");
  Images.ToffeeTowerLvl3 = loadImage("ToffeeTowerLvl3.png");
  Images.ThinMintTowerLvl2 = loadImage("ThinMintTowerLvl2.png");
  Images.SmoresTowerLvl2 = loadImage("SmoresTowerLvl2.png");
}

public void changeScene(Scene newScene) {
  scene.onSceneExit();
  scene = newScene;
  scene.onSceneEnter();
  Time.newScene();
}
public class AchievementsScene extends Scene { 
  public void update() {
  }
  
  public void onSceneEnter() {
  }
  public void onSceneExit(){
  }
  
  public void onMousePressed(){
   
  }
  public void onKeyPressed(){
  }
}
public class Building {
  public GameScene scene;
  public int rows;
  public int cols;
  
  public int i, j;
  
  public Building(GameScene scene, int i, int j) {
    this.scene = scene;
    this.i = i;
    this.j = j;
  }
  public int cost;
  public void update() {}
  public void display() {}
}

public class CookieDoughMine extends Building {
  private final float DOUGH_TIMER_MAX = 8f;
  private float doughTimer = DOUGH_TIMER_MAX;
  public CookieDoughMine(GameScene scene, int i, int j) {
    super(scene, i, j);
    rows = 2;
    cols = 2;
  }
  public void update() {
    if(doughTimer > 0) doughTimer -= Time.deltaTime;
    else {
      doughTimer = DOUGH_TIMER_MAX;
      scene.cookieDough += 5;
    }
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.VaultDesign, p.x + tileWidth * 0.1f, p.y + tileHeight * .1f, tileWidth * 1.8f, tileHeight * 1.8f);
  }
}

public class TrefoilMine extends Building {
  private final float TREF_TIMER_MAX = 8f;
  private float trefTimer = TREF_TIMER_MAX;
  public TrefoilMine(GameScene scene, int i, int j) {
    super(scene, i, j);
    rows = 2;
    cols = 2;
  }
  public void update() {
    if(trefTimer > 0) trefTimer -= Time.deltaTime;
    else {
      trefTimer = TREF_TIMER_MAX;
      scene.trefoils += 2;
    }
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.Research_Design_Lvl1, p.x + tileWidth * 0.1f, p.y, tileWidth * 1.8f, tileHeight * 2);
  }
}

public class ToffeeTower extends Building {
  private final float SHOOT_TIMER_MAX = 2f;
  private float shootTimer = SHOOT_TIMER_MAX;
  public float range = 100f;
  public int damage = 34;
  public Monster target;
  public ToffeeTower(GameScene scene, int i, int j) {
    super(scene, i, j);
    rows = 1;
    cols = 1;
  }
  public void update() {
    //DETECT FOR ENEMIES
    if(shootTimer > 0) shootTimer -= Time.deltaTime;
    float monsterX = 0;
    target = null;
    PVector p = tileToPoint(new PVector(j,i));
    for(int i = 0; i < scene.monsters.size(); i++) {
      Monster m = scene.monsters.get(i);
      if(PVector.sub(p, m.pos).mag() < range) {
        if(m.pos.x > monsterX) {
          target = m;
          monsterX = m.pos.x;
        }
      }
    }
    
    if(target != null) {
      if(shootTimer <= 0) {
        target.health -= damage;
        scene.projectiles.add(new Projectile(Images.ToffeeProjectile, target, 120, tileToPoint(new PVector(j,i)))); 
        shootTimer = SHOOT_TIMER_MAX;
      }
    }
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.ToffeeTowerLvl3, p.x, p.y - tileHeight / 2, tileWidth * 1.6f, tileHeight * 1.5f);
  }
}

public class ThinMintTower extends Building {
  private final float SHOOT_TIMER_MAX = 3f;
  private float shootTimer = SHOOT_TIMER_MAX;
  public float range = 100f;
  public int damage = 20;
  public ArrayList<Monster> inRange = new ArrayList<Monster>();
  public ThinMintTower(GameScene scene, int i, int j) {
    super(scene, i, j);
    rows = 1;
    cols = 1;
  }
  public void update() {
    //DETECT FOR ENEMIES
    if(shootTimer > 0) shootTimer -= Time.deltaTime;
    inRange.clear();
    PVector p = tileToPoint(new PVector(j,i));
    for(int i = 0; i < scene.monsters.size(); i++) {
      Monster m = scene.monsters.get(i);
      if(PVector.sub(p, m.pos).mag() < range) {
        inRange.add(m);
      }
    }
    
    if(!inRange.isEmpty()) {
      if(shootTimer <= 0) {
        for(int i = 0; i < inRange.size(); i++) {
          Monster m = inRange.get(i);
          m.health -= damage;
          m.frostTimer = m.FROST_TIMER_MAX;
        }
        //show projectile
        image(Images.ThinMintProjectile, p.x - range, p.y - range/2, range, range);
        shootTimer = SHOOT_TIMER_MAX;
      }
    }
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.ThinMintTowerLvl2, p.x - tileWidth * 1.38f, p.y - tileHeight, tileWidth * 1.8f, tileHeight * 2);
  }
}

public class SmoresTower extends Building {
  private final float SHOOT_TIMER_MAX = 3f;
  private float shootTimer = SHOOT_TIMER_MAX;
  public float range = 100f;
  public int damage = 20;
  public Monster target;
  public SmoresTower(GameScene scene, int i, int j) {
    super(scene, i, j);
    rows = 1;
    cols = 1;
  }
  public void update() {
    //DETECT FOR ENEMIES
    if(shootTimer > 0) shootTimer -= Time.deltaTime;
    float monsterX = 0;
    target = null;
    PVector p = tileToPoint(new PVector(j,i));
    for(int i = 0; i < scene.monsters.size(); i++) {
      Monster m = scene.monsters.get(i);
      if(PVector.sub(p, m.pos).mag() < range) {
        if(m.pos.x > monsterX) {
          target = m;
          monsterX = m.pos.x;
        }
      }
    }
    
    if(target != null) {
      if(shootTimer <= 0) {
        target.health -= damage;
        target.fireTimer = target.FIRE_TIMER_MAX;
        scene.projectiles.add(new Projectile(Images.SmoresProjectile, target, 120, tileToPoint(new PVector(j,i)))); 
        shootTimer = SHOOT_TIMER_MAX;
      }
    }
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.SmoresTowerLvl2, p.x - tileWidth / 2.3f, p.y - tileHeight / 1.9f, tileWidth * 1.8f, tileHeight * 2);
  }
}
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
    image(Images.Factory_Design_Lvl2, p.x - tileWidth * .7f, p.y - tileHeight * .3f, tileWidth * 3.5f, tileHeight * 6);
    waveState.displayTimer();
    fill(255);
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
    if(i + building.rows < worldRows) maxI = i + building.rows;
    else available = false;
    if(j + building.cols < worldCols) maxJ = j + building.cols;
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
}
public static class Images {
  public static PImage titleImage;
  public static PImage recipesBar;
  public static PImage upgradeBar;
  public static PImage factoryLevelBar;
  public static PImage tempTimer;
  public static PImage Bakery_Design_Lvl2;
  public static PImage Factory_Design_Lvl2;
  public static PImage Research_Design_Lvl1;
  public static PImage VaultDesign;
  public static PImage CookieDoughConcept;
  public static PImage Wall_LVL1;
  public static PImage TrefoilsConcept;
  public static PImage GemsConcept;
  public static PImage WoodConcept;
  public static PImage mapDesign;
  public static PImage ArcherAttackFrame;
  public static PImage ArcherFrame1;
  public static PImage ArcherFrame2;
  public static PImage ArcherFrame3;
  public static PImage SamoraiAttackFrame;
  public static PImage SamoraiFrame1;
  public static PImage SamoraiFrame2;
  public static PImage SamoraiFrame3;
  public static PImage FlyerAttackFrame;
  public static PImage FlyerFrame1;
  public static PImage FlyerFrame2;
  public static PImage FlyerFrame3;
  public static PImage InfantryAttackFrame;
  public static PImage InfantryFrame1;
  public static PImage InfantryFrame2;
  public static PImage InfantryFrame3;
  public static PImage TankAttackFrame;
  public static PImage TankFrame1;
  public static PImage TankFrame2;
  public static PImage TankFrame3;
  public static PImage SmoresProjectile;
  public static PImage ThinMintProjectile;
  public static PImage ToffeeProjectile;
  public static PImage ToffeeTowerLvl3;
  public static PImage ThinMintTowerLvl2;
  public static PImage SmoresTowerLvl2;
}
public class Instructions extends Scene { 
  public void update() {
  }
  
  public void onSceneEnter() {
  }
  public void onSceneExit(){
  }
  
  public void onMousePressed(){
   
  }
  public void onKeyPressed(){
  }
}
public class MainMenu extends Scene {
  public void update() {
    image(Images.titleImage, -40, 0);
  }
  
  public void onSceneEnter() {
  }
  public void onSceneExit(){
  }
  
  public void onMousePressed(){
    if(mouseX >= 190 && mouseX <= 343 && mouseY >= 360 && mouseY <= 405)
      changeScene(new Options());
    else if(mouseX >= 395 && mouseX <= 605 && mouseY >= 360 && mouseY <= 405)
      changeScene(new Instructions());
    else if(mouseX >= 630 && mouseX <= 848 && mouseY >= 360 && mouseY <= 405)
      changeScene(new AchievementsScene());
    else if(sqrt(pow(mouseX - 502, 2) + pow(mouseY - 268, 2)) < 64)
      changeScene(new GameScene());
  }
  public void onKeyPressed(){
  }
}
public abstract class Monster {
  
  public PVector targetPos;
  private Queue<PVector> path;
  public PVector nextTile;
  public PVector pos;
  private PVector start;
  public boolean isDead = false;
  
  public float health;
  public float damage;
  
  public boolean attackMode = false;
  public Unit attackTarget;
  
  public float speedMult = 1;
  public float FROST_TIMER_MAX = 2f;
  public float frostTimer = 0;
  
  public float FIRE_TIMER_MAX = 2f;
  public float fireTimer = 0;
  
  public float timeToLeave = 0;
  private float speed = 50;
  
  public final float FRAME_MAX = 0.15f;
  public float frameCounter = FRAME_MAX;
  
  public int imageIndex = 0;
  public PImage images[];
  
  public Monster(int health, PVector pos, PVector start, PVector target) {
    this.health = health;
    this.pos = pos.copy();
    this.start = start;
    this.targetPos = target;
    findPath();
  }
  
  public void update(GameScene scene) {
    noTint();
    if(frostTimer <= 0) speedMult = 1f;
    else {
      speedMult = .5f;
      frostTimer -= Time.deltaTime;
    }
    
    if(fireTimer > 0) {
      health -= 8 * Time.deltaTime;
      fireTimer -= Time.deltaTime;
      frostTimer = 0f;
    }
    
    if(frameCounter > 0) {
      frameCounter -= Time.deltaTime;
    }
    //noTint();
    if(attackMode && attackTarget != null) {
      if(attackTarget.health > 0)
        attackTarget.health -= damage * Time.deltaTime;
      else {
        attackTarget = null;
        attackMode = false;
      }
    } else if(nextTile != null) {
      if(tileToPoint(nextTile).equals(pos)) {
        nextTile = path.poll();
      } else {
        PVector distance = PVector.sub(tileToPoint(nextTile), pos);
        PVector dir = distance.copy().normalize();
        PVector travel = new PVector(speed * speedMult * Time.deltaTime * dir.x, speed * speedMult * Time.deltaTime * dir.y);
        if(travel.mag() > distance.mag()) {
          if(nextTile.equals(targetPos)) {
            isDead = true;
            targetPos = nextTile.copy();
            // hurt factory health
            scene.factoryHealth -= 10;
          } else {
            pos = tileToPoint(nextTile.copy());
            int i = (int)nextTile.x;
            int j = (int)nextTile.y;
            PVector attackLocation = new PVector(0,0);
            attackMode = false;
            if(i+1 < worldRows && scene.grid[i+1][j].hasUnits) { 
              attackMode = true;
              attackLocation = new PVector(j, i+1);
            }
            if(i-1 > 0 && scene.grid[i-1][j].hasUnits) { 
              attackMode = true;
              attackLocation = new PVector(j, i-1);
            }
            if(j+1 < worldCols && scene.grid[i][j+1].hasUnits) { 
              attackMode = true;
              attackLocation = new PVector(j+1, i);
            }
            if(j-1 > 0 && scene.grid[i][j-1].hasUnits) { 
              attackMode = true;
              attackLocation = new PVector(j-1, i);
            }
            
            if(attackMode) {
              for(int k = 0; k < scene.units.size(); k++) {
                if(scene.units.get(k).j == (int)attackLocation.x && scene.units.get(k).i == (int)attackLocation.y)
                  attackTarget = scene.units.get(k);
              } 
            }
          }
        } else
          pos.add(travel);
      }
    }
    display();
    if(health < 0) isDead = true;
  }
  
  public abstract void display();
  
  public void findPath() {
    path = Pathfinder.findPath(start, targetPos);
    nextTile = path.poll();
  }
}

public class Infantry extends Monster {
  public Infantry(int health, PVector pos, PVector start, PVector target) {
    super(health, pos, start, target);
    images = new PImage[] {Images.InfantryFrame1, Images.InfantryFrame2, Images.InfantryFrame3, Images.InfantryFrame2, Images.InfantryAttackFrame};
    imageIndex = 0;
    damage = 20;
  }
  
  public void display() {
    if(frameCounter <= 0) {
      
      if(attackMode) {
        imageIndex = 4;
      } else {
        if(imageIndex < 3) imageIndex++;
        else imageIndex = 0;
      }

      frameCounter = FRAME_MAX;
    }
    image(images[imageIndex], pos.x - tileWidth, pos.y - tileHeight, tileWidth * 2, tileHeight * 2);
  }
}

public class Flyer extends Monster {
  public Flyer(int health, PVector pos, PVector start, PVector target) {
    super(health, pos, start, target);
    images = new PImage[] {Images.FlyerFrame1, Images.FlyerFrame2, Images.FlyerFrame3, Images.FlyerAttackFrame};
    imageIndex = 0;
    damage = 15;
  }
  
  public void display() {
    if(frameCounter <= 0) {
      if(attackMode) {
        imageIndex = 3;
      } else {
        if(imageIndex < 2) imageIndex++;
        else imageIndex = 0;
      }
      
      frameCounter = FRAME_MAX;
    }
    image(images[imageIndex], pos.x - tileWidth * 1.5f, pos.y - tileHeight * 1.5f, tileWidth*3, tileHeight*3);
  }
}

public class Tank extends Monster {
  public Tank(int health, PVector pos, PVector start, PVector target) {
    super(health, pos, start, target);
    images = new PImage[] {Images.TankFrame1, Images.TankFrame2, Images.TankFrame3, Images.TankFrame2, Images.TankAttackFrame};
    imageIndex = 0;
    damage = 10;
  }
  
  public void display() {
    if(frameCounter <= 0) {
      if(attackMode) {
        imageIndex = 4;
      } else {
        if(imageIndex < 3) imageIndex++;
        else imageIndex = 0;
      }
      
      frameCounter = FRAME_MAX;
    }
    image(images[imageIndex], pos.x - tileWidth * 1.5f, pos.y - tileHeight * 1.5f, tileWidth*3, tileHeight*3);
  }
}
public class Options extends Scene { 
  public void update() {
  }
  
  public void onSceneEnter() {
  }
  public void onSceneExit(){
  }
  
  public void onMousePressed(){
   
  }
  public void onKeyPressed(){
  }
}
public static class Pathfinder {

  public static PathTile[][] worldTiles = new PathTile[20][20];
  
  private static ArrayList<PathTile> open = new ArrayList<PathTile>(); // collection of tiles we can use to solve the algorithm
  private static ArrayList<PathTile> closed = new ArrayList<PathTile>(); // collection of tiles that we've ruled out as NOT part of the solution

  public static Queue<PVector> findPath(PVector startPos, PVector endPos) {
    open.clear(); // empty array
    closed.clear(); // empty array

    PathTile start = worldTiles[(int)startPos.y][(int)startPos.x];
    PathTile end = worldTiles[(int)endPos.y][(int)endPos.x];
    start.resetParent(); // starting tile's "parent" property should be null

    // STEP 1:
    connectStartToEnd(start, end);

    // STEP 2: BUILD PATH BACK TO BEGINNING
    ArrayList<PathTile> path = new ArrayList<PathTile>();
    PathTile pathNode = end;
    while (pathNode != null) {
      path.add(pathNode);
      pathNode = pathNode.parent;
    }

    // STEP 3: REVERSE THE COLLECTION:
    Queue<PVector> rev = new LinkedList<PVector>();
    int maxIndex = path.size() - 1;
    for (int i = maxIndex; i >= 0; i--) {
      PathTile pt = path.get(i);
      rev.add(new PVector(pt.x, pt.y));
    }
    return rev;
  }

  private static void connectStartToEnd(PathTile start, PathTile end) {

    open.add(start);

    while (open.size() > 0) {
      ////// GET THE NODE IN open WITH LOWEST F VALUE:
      float F = 9999;
      int index = -1;

      for (int i = 0; i < open.size (); i++) {
        PathTile temp = open.get(i);
        if (temp.F < F) {
          F = temp.F;
          index = i;
        }
      }

      PathTile current = open.remove(index);
      closed.add(current);

      if (current == end) {
        // path found
        break;
      }
      // LOOP THROUGH ALL OF current's NEIGHBORS:
      for (int i = 0; i < current.neighbors.size (); i++) {
        PathTile neighbor = current.neighbors.get(i);
        if (!tileInArray(closed, neighbor)) {
          if (!tileInArray(open, neighbor)) {
            open.add(neighbor);
            neighbor.setParent(current);
            neighbor.doHeuristic(end);
          } else {
            if (neighbor.G > current.G + neighbor.cost) {
              neighbor.setParent(current);
              neighbor.doHeuristic(end);
            } // end if
          } // end else
        } // end if
      } // end for
    } // end while
  } // end method

  private static boolean tileInArray(ArrayList<PathTile> a, PathTile t) {
    for (int i = 0; i < a.size (); i++) {
      if (a.get(i) == t) return true;
    }
    return false;
  }
}


private class PathTile {
  
  public int x;
  public int y;
  
  public float F;
  public float G;
  public float cost;
  
  public PathTile parent;
  public ArrayList<PathTile> neighbors = new ArrayList<PathTile>();
  
  public void setNeighbors() {
    if(x-1 >= 0) neighbors.add(Pathfinder.worldTiles[y][x-1]);
    if(x+1 < worldCols) neighbors.add(Pathfinder.worldTiles[y][x+1]);
    if(y-1 >= 0) neighbors.add(Pathfinder.worldTiles[y-1][x]);
    if(y+1 < worldRows) neighbors.add(Pathfinder.worldTiles[y+1][x]);
  }

  public void setParent(PathTile n) {
    parent = n;
    G = parent.G + cost;
  }
  
  public void resetParent() {
    parent = null;
    G = 0;
    F = 0;
  }
  
    // HEURISTICS CALULATIONS:
  public void doHeuristic(PathTile n) {
    F = G + distanceEuclidean(n);
  }
  
    // EUCLIDEAN HEURISTIC CALCULATION:
  public float distanceEuclidean(PathTile n) {
    float dx = n.x - x;
    float dy = n.y - y;
    return sqrt(dx * dx + dy * dy);
  }
}
public class Projectile {
  public PImage image;
  public Monster target;
  public float speed;
  public PVector pos;
  public boolean isDead = false;
  
  public Projectile(PImage img, Monster trg, float spd, PVector p) {
    image = img;
    target = trg;
    speed = spd;
    pos = p;
  }
  
  public void update() {
    PVector distance = PVector.sub(target.pos, pos);
    PVector dir = distance.copy().normalize();
    PVector travel = new PVector(speed * Time.deltaTime * dir.x, speed * Time.deltaTime * dir.y);
    if(travel.mag() > distance.mag()) {
      pos = target.pos.copy();
      isDead = true;
    } else {
      pos.add(travel);
    }
    
    image(image, pos.x, pos.y, 20, 20);
  }
}
public abstract class Scene {
  
  public abstract void update();
  
  public abstract void onSceneEnter();
  public abstract void onSceneExit();
  
  public abstract void onMousePressed();
  public abstract void onKeyPressed();
}
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
static class Time {
 
  public static float deltaTime = 0;
  public static float timeScale = 1;
  public static float timeSinceScene = 0;
  private static float prevTime = 0;
  
  public static void update(float milis) {
    float currentTime = milis;
    deltaTime = toSeconds(currentTime - prevTime);
    prevTime = currentTime;
    
    timeSinceScene += deltaTime;
  }
  
  public static float toSeconds(float milis) {
    return milis / 1000 * timeScale;
  }
  
  public static void newScene() {
    timeSinceScene = 0;
  }
}
public abstract class UILayer {
  private boolean isActive;
  
  public void setActive(boolean active) {
    isActive = active; 
  }
  
  public void update() {
    if(isActive) display();
  }
  
  public abstract void display();
  
  public void onClick() {}
}

public class UpperUILayer extends UILayer{ 
  public void display() {
    image(Images.factoryLevelBar, 0, -50);
  }
}

public class BarsLayer extends UILayer{ 
  public void display() {
    image(Images.recipesBar, 50, 450);
    image(Images.upgradeBar, 800, 450);
  }
}

public class RecipesLayer extends UILayer {
  public void display() {
  }
}

public class UpgradeLayer extends UILayer {
  public void display() {
  }
}
public class Unit {
  public GameScene scene;
  
  public int i, j;
  public int range = 0;
  public boolean isAttacking = false;
  public ArrayList<Monster> inRange = new ArrayList<Monster>();
  
  public PImage displayImage;
  public PImage attackImage;
  public float health = 100;
  public int damage = 0;
  public boolean isDead = false;
  public boolean isAttack = false;
  public Unit(GameScene scene, int i, int j) {
    this.scene = scene;
    this.i = i;
    this.j = j;
  }
  public int cost;
  public void update() {
    if(health <= 0) isDead = true;
    
    inRange.clear();
    PVector p = tileToPoint(new PVector(i,j));
    for(int i = 0; i < scene.monsters.size(); i++) {
      Monster m = scene.monsters.get(i);
      if(PVector.sub(p, m.pos).mag() < range) {
        inRange.add(m);
      }
    }
    
    if(!inRange.isEmpty()) {
      Monster m = inRange.get(0);
      m.health -= damage * Time.deltaTime;
      isAttacking = true;
    }
  }
  public void display() {
    PVector p = tileToPoint(new PVector(i,j));
    if(isAttacking)
      image(attackImage, p.x - tileWidth * 1.5f, p.y - tileHeight * 1.5f, tileWidth*3, tileHeight*3);
    else
      image(displayImage, p.x - tileWidth * 1.5f, p.y - tileHeight * 1.5f, tileWidth*3, tileHeight*3);
  }
}

public class Samorai extends Unit {
  public GameScene scene;
  
  public int i, j;
  
  public Samorai(GameScene scene, int i, int j) {
    super(scene, i, j);
    range = 30;
    damage = 20;
    displayImage = Images.SamoraiFrame1;
    attackImage = Images.SamoraiAttackFrame;
  }
  public int cost;
  public void update() {
    super.update();
  }
  public void display() {
    super.display();
  }
}

public class Archer extends Unit {
  public GameScene scene;
  
  public int i, j;
  
  public Archer(GameScene scene, int i, int j) {
    super(scene, i, j);
    range = 90;
    damage = 10;
    displayImage = Images.ArcherFrame1;
    attackImage = Images.ArcherAttackFrame;
  }
  public int cost;
  public void update() {
    super.update();
  }
  public void display() {
    super.display();
  }
}
public class UpgradeData {
  public int health;
  public int damage;
  public int range;
  public float fireSpeed;
  public int fanCost;
  
  public UpgradeData(int health, int damage, int range, float fireSpeed, int fanCost) {
    this.health = health;
    this.damage = damage;
    this.range = range;
    this.fireSpeed = fireSpeed;
    this.fanCost = fanCost;
  }
  
  public UpgradeData(int health, int fanCost) {
    this.health = health;
    this.fanCost = fanCost;
  }
}
public abstract class WaveState {
  protected GameScene scene;
  
  public abstract void onEnter();
  public abstract void onExit();
  
  public abstract void update();
  public abstract void onClick();
  
  public abstract void displayTimer();
}

public class PrepState extends WaveState {
  public float waveTimer = 10f;
  
  public PrepState(GameScene scene) {
    this.scene = scene;
  }
  public void onEnter() {
    
  }
  public void onExit() {
    
  }
  
  public void update() {
    if(waveTimer > 0) {
      waveTimer -= Time.deltaTime;
    } else {
      scene.changeState(new AttackState(scene, 1));
    }
  }
  
  public void onClick() {
    
  }
  
  public void displayTimer() {
    fill(0);
    text((int)waveTimer, 900, 35);
    fill(255);
  }
}

public class AttackState extends WaveState {
  private int wave;
  private int waveMonsters = 0;
  private float healthMult;
  
  private final float SPAWN_TIMER_MAX = 1f;
  private float spawnTimer = SPAWN_TIMER_MAX;
  
  public AttackState(GameScene scene, int wave) {
    this.scene = scene;
    this.wave = wave;
  }
  public void onEnter() {
    waveMonsters = wave * 3 + 7;
    healthMult = pow(1.1f, wave - 1);
  }
  public void onExit() {
    
  }
  public void update() {
    if(spawnTimer > 0) {
      spawnTimer -= Time.deltaTime;
    } else if(waveMonsters > 0) {
      PVector p = new PVector(0, PApplet.parseInt(random(20)));
      PVector target = (p.y <= 9 ? new PVector(16, 9) : new PVector(16, 10));
      Monster monster;
      float rand = random(.99f);
      if(rand < .33f) 
        monster = new Infantry(PApplet.parseInt(100 * healthMult), tileToPoint(p), p, target);
      else if(rand < .66f)
        monster = new Flyer(PApplet.parseInt(100 * healthMult), tileToPoint(p), p, target);
      else
        monster = new Tank(PApplet.parseInt(100 * healthMult), tileToPoint(p), p, target);
        
      scene.monsters.add(monster);
      
      waveMonsters--;
      spawnTimer = SPAWN_TIMER_MAX;
    } else if(scene.monsters.isEmpty()) {
      scene.changeState(new RestState(scene, wave));
    } 
  }
  
  public void onClick() { }
  public void displayTimer() {}
}

public class RestState extends WaveState {
  private float restTimer = 10f;
  private int wave;
  
  public RestState(GameScene scene, int wave) {
    this.scene = scene;
    this.wave = wave;
  }
  public void onEnter() {
    //if(wave >= 10) //end game
  }
  public void onExit() {
    
  }
  public void update() {
    if(restTimer > 0) restTimer -= Time.deltaTime;
    else scene.changeState(new AttackState(scene, ++wave));
  }
  
  public void onClick() {
    
  }
  
  public void displayTimer() {
    fill(0);
    text((int)restTimer, 900, 35);
    fill(255);
  }
}
  public void settings() {  size(1000, 440); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AttackOfTheCookieMonsters" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
