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
    image(Images.VaultDesign, p.x + tileWidth * 0.1, p.y + tileHeight * .1, tileWidth * 1.8, tileHeight * 1.8);
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
    image(Images.Research_Design_Lvl1, p.x + tileWidth * 0.1, p.y, tileWidth * 1.8, tileHeight * 2);
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
    image(Images.ToffeeTowerLvl3, p.x, p.y - tileHeight / 2, tileWidth * 1.6, tileHeight * 1.5);
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
    image(Images.ThinMintTowerLvl2, p.x - tileWidth * 1.38, p.y - tileHeight, tileWidth * 1.8, tileHeight * 2);
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
    image(Images.SmoresTowerLvl2, p.x - tileWidth / 2.3, p.y - tileHeight / 1.9, tileWidth * 1.8, tileHeight * 2);
  }
}
