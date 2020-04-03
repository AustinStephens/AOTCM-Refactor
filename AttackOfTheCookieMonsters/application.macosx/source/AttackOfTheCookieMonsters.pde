import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

Scene scene;

final int worldRows = 20;
final int worldCols = 20;

final int tileHeight = 17;
final int tileWidth = 46;

final int gridStartX = 0;
final int gridStartY = 51;

void setup() {
  size(1000, 440);
  initImages();
  scene = new MainMenu();
  scene.onSceneEnter();
}

void draw() {
  background(255);
  Time.update(millis());
  scene.update();
}

void mousePressed() {
  scene.onMousePressed();
  print(mouseX + ", " + mouseY + "   ");
  print();
}

void keyPressed() {
  scene.onKeyPressed();
}

PVector tileToPoint(PVector p) {
  return new PVector((p.x + 0.5)* tileWidth + gridStartX, (p.y + 0.5) * tileHeight + gridStartY);
}

PVector tileToCorner(int x, int y) {
  return new PVector(x * tileWidth + gridStartX, y * tileHeight + gridStartY);
}

void initImages()
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

void changeScene(Scene newScene) {
  scene.onSceneExit();
  scene = newScene;
  scene.onSceneEnter();
  Time.newScene();
}
