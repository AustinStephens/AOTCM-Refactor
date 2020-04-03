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
