public class MainMenu extends Scene {
  public void update() {
    image(Images.titleImage, -40, 0);
  }
  
  public void onSceneEnter() {
  }
  public void onSceneExit(){
  }
  
  public void onMousePressed(){
    if(sqrt(pow(mouseX - 502, 2) + pow(mouseY - 268, 2)) < 64)
      changeScene(new GameScene());
  }
  public void onKeyPressed(){
  }
}
