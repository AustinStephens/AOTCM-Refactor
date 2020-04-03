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
