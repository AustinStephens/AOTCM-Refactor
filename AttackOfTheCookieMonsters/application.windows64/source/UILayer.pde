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
