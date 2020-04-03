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
      image(attackImage, p.x - tileWidth * 1.5, p.y - tileHeight * 1.5, tileWidth*3, tileHeight*3);
    else
      image(displayImage, p.x - tileWidth * 1.5, p.y - tileHeight * 1.5, tileWidth*3, tileHeight*3);
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
