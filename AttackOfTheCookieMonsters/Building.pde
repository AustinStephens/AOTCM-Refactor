public abstract class Building {
  public GameScene scene;
  public int buildingWidth;
  public int buildingHeight;
  public int i, j;
  
  protected float buildingTimerMax;
  protected float buildingTimer;
  
  public Building(GameScene scene, int i, int j, float buildingTimerMax) {
    this.scene = scene;
    this.i = i;
    this.j = j;
    this.buildingTimerMax = buildingTimerMax;
    this.buildingTimer = buildingTimerMax;
  }
  public int cost;
  public void update() {
    if(buildingTimer > 0) buildingTimer -= Time.deltaTime;
    else {
      buildingTimer = buildingTimerMax;
      timerAction();
    }
  }
  public void display() {}
  public abstract void timerAction();
}

public class CookieDoughMine extends Building {
  public CookieDoughMine(GameScene scene, int i, int j) {
    super(scene, i, j, 8f);
    buildingWidth = 2;
    buildingHeight = 2;
  }
  
  public void timerAction() {
    scene.cookieDough += 5;
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.VaultDesign, p.x + tileWidth * 0.1, p.y + tileHeight * .1, tileWidth * 1.8, tileHeight * 1.8);
  }
}

public class TrefoilMine extends Building {
  public TrefoilMine(GameScene scene, int i, int j) {
    super(scene, i, j, 8f);
    buildingWidth = 2;
    buildingHeight = 2;
  }
  
  public void timerAction() {
    scene.trefoils += 2;
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.Research_Design_Lvl1, p.x + tileWidth * 0.1, p.y, tileWidth * 1.8, tileHeight * 2);
  }
}

public class ToffeeTower extends Building {
  public float range = 100f;
  public int damage = 34;
  public Monster target;
  public ToffeeTower(GameScene scene, int i, int j) {
    super(scene, i, j,2f);
    buildingWidth = 1;
    buildingHeight = 1;
  }
  
  @Override
  public void update() {
    //DETECT FOR ENEMIES
    if(buildingTimer > 0) buildingTimer -= Time.deltaTime;
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
      if(buildingTimer <= 0) {
        target.health -= damage;
        scene.projectiles.add(new Projectile(Images.ToffeeProjectile, target, 120, tileToPoint(new PVector(j,i)))); 
        buildingTimer = buildingTimerMax;
      }
    }
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.ToffeeTowerLvl3, p.x, p.y - tileHeight / 2, tileWidth * 1.6, tileHeight * 1.5);
  }
  
  public void timerAction() {}
}

public class ThinMintTower extends Building {
  public float range = 100f;
  public int damage = 20;
  public ArrayList<Monster> inRange = new ArrayList<Monster>();
  public ThinMintTower(GameScene scene, int i, int j) {
    super(scene, i, j, 3f);
    buildingWidth = 1;
    buildingHeight = 1;
  }
  
  @Override
  public void update() {
    //DETECT FOR ENEMIES
    if(buildingTimer > 0) buildingTimer -= Time.deltaTime;
    inRange.clear();
    PVector p = tileToPoint(new PVector(j,i));
    for(int i = 0; i < scene.monsters.size(); i++) {
      Monster m = scene.monsters.get(i);
      if(PVector.sub(p, m.pos).mag() < range) {
        inRange.add(m);
      }
    }
    
    if(!inRange.isEmpty()) {
      if(buildingTimer <= 0) {
        for(int i = 0; i < inRange.size(); i++) {
          Monster m = inRange.get(i);
          m.health -= damage;
          m.frostTimer = m.FROST_TIMER_MAX;
        }
        //show projectile
        image(Images.ThinMintProjectile, p.x - range, p.y - range/2, range, range);
        buildingTimer = buildingTimerMax;
      }
    }
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.ThinMintTowerLvl2, p.x - tileWidth * 1.38, p.y - tileHeight, tileWidth * 1.8, tileHeight * 2);
  }
  
  public void timerAction() {}
}

public class SmoresTower extends Building {
  public float range = 100f;
  public int damage = 20;
  public Monster target;
  public SmoresTower(GameScene scene, int i, int j) {
    super(scene, i, j, 3f);
    buildingWidth = 1;
    buildingHeight = 1;
  }
  
  @Override
  public void update() {
    //DETECT FOR ENEMIES
    if(buildingTimer > 0) buildingTimer -= Time.deltaTime;
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
      if(buildingTimer <= 0) {
        target.health -= damage;
        target.fireTimer = target.FIRE_TIMER_MAX;
        scene.projectiles.add(new Projectile(Images.SmoresProjectile, target, 120, tileToPoint(new PVector(j,i)))); 
        buildingTimer = buildingTimerMax;
      }
    }
  }
  
  public void display() {
    PVector p = tileToCorner(j, i);
    image(Images.SmoresTowerLvl2, p.x - tileWidth / 2.3, p.y - tileHeight / 1.9, tileWidth * 1.8, tileHeight * 2);
  }
  
  public void timerAction() {}
}
