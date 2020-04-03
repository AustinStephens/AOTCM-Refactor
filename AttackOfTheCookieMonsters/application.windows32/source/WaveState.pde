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
      PVector p = new PVector(0, int(random(20)));
      PVector target = (p.y <= 9 ? new PVector(16, 9) : new PVector(16, 10));
      Monster monster;
      float rand = random(.99f);
      if(rand < .33f) 
        monster = new Infantry(int(100 * healthMult), tileToPoint(p), p, target);
      else if(rand < .66)
        monster = new Flyer(int(100 * healthMult), tileToPoint(p), p, target);
      else
        monster = new Tank(int(100 * healthMult), tileToPoint(p), p, target);
        
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
