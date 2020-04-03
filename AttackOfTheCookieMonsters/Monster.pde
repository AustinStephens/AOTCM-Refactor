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
    
    counters();
    pathfinding(scene);
    display();
    
    if(health < 0) isDead = true;
  }
  
  public abstract void display();
  
  public void findPath() {
    path = Pathfinder.findPath(start, targetPos);
    nextTile = path.poll();
  }
  
  private void counters() {
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
  }
  
  private void pathfinding(GameScene scene) {
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
