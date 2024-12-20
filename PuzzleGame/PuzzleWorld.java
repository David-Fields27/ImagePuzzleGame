package PuzzleGame;
import javalib.impworld.*;
import javalib.worldimages.*;
import java.awt.Color;

//to represent the puzzlegame
public class PuzzleWorld extends World {
  PuzzleBoard gameBoard; //the current board being displayed
  WorldImage background; //the entire background image (used for sizing the window)
  WorldImage puzzleImage; //the cropped background image using for the puzzle
  MouseSelections mouseSelections; //the last two pieces selected before a swap
  int gameDimensions; //the dimensions of the game
  GameTimer time; //how much time has elapsed since running the game
  GameHelper gh = new GameHelper();

  //base constructor that loads local image
  public PuzzleWorld(){
    this.gameDimensions = 3;
    WorldImage img = new FromFileImage("DefaultImage.jpg");
    this.puzzleImage = this.gh.cropImage(img, this.gameDimensions);
    this.gameBoard = new PuzzleBoard(this.gh.makeBoard(this.puzzleImage, this.gameDimensions));
    this.gameBoard.scramble();
    this.time = new GameTimer();
    this.background = img;
    this.mouseSelections = new MouseSelections(null, null);
    this.bigBang(738,510, 1);
  }

  //constructor that loads file from path
  public PuzzleWorld(String filepath){
    this.gameDimensions = 5;
    WorldImage img = new FromFileImage(filepath);
    this.puzzleImage = this.gh.cropImage(img, this.gameDimensions);
    this.gameBoard = new PuzzleBoard(this.gh.makeBoard(this.puzzleImage, this.gameDimensions));
    this.gameBoard.scramble();
    this.background = img;
    this.time = new GameTimer();
    this.mouseSelections = new MouseSelections(null, null);
    this.bigBang(738,510, 1);
  }

  //Constructor that loads file from path and defines puzzles dimensions
  public PuzzleWorld(String filePath, int puzzleDimensions){
    WorldImage img = new FromFileImage(filePath);
    this.gameDimensions = puzzleDimensions;
    this.puzzleImage = this.gh.cropImage(img, this.gameDimensions);
    this.gameBoard = new PuzzleBoard(this.gh.makeBoard(this.puzzleImage, this.gameDimensions));
    this.gameBoard.scramble();
    this.background = img;
    this.time = new GameTimer();
    this.mouseSelections = new MouseSelections(null, null);
    this.bigBang(738,510, 1);
  }
  //draws the instructions to the world
  WorldScene drawInstructions(WorldScene scn) {
    WorldImage fKey = new FromFileImage("KEYBOARD-F-KEY.png");
    WorldImage fInstruct = new TextImage("Flip", 18, Color.BLACK);
    WorldImage rKey = new FromFileImage("KEYBOARD-R-KEY.png");
    WorldImage rInstruct = new TextImage("Rotate Right", 18, Color.BLACK);
    WorldImage lKey = new FromFileImage("KEYBOARD-L-KEY.png");
    WorldImage lInstruct = new TextImage("Rotate Left", 18,  Color.BLACK);
    WorldImage cursor = new FromFileImage("Cursor.png");
    WorldImage cursorInstruct = new TextImage("Select to swap", 18, Color.BLACK);
    WorldImage f = new AboveImage(fKey, fInstruct);
    WorldImage r = new AboveImage(rKey, rInstruct);
    WorldImage l = new AboveImage(lKey, lInstruct);
    WorldImage c = new AboveImage(cursor, cursorInstruct);
    WorldImage bufferSpace = new RectangleImage(40, 40, OutlineMode.SOLID, Color.WHITE);
    WorldImage instructions = new BesideImage(c, bufferSpace, r,bufferSpace ,l, bufferSpace, f);
    scn.placeImageXY(instructions, scn.width/2, (scn.height * 9)/10);
    return scn;
  }
  //draws the scene
  public WorldScene makeScene(){
    WorldScene scn = new WorldScene(738,510);
    return this.time.drawTime(this.gameBoard.drawBoard(this.drawInstructions(scn)));
  }

  //processes the key events
  public void onKeyEvent(String key) {
    if (this.mouseSelections.canApply()) {
      if (key.equals("r")) {
        this.mouseSelections.rotateRight();
      } else if (key.equals("l")) {
        this.mouseSelections.rotateLeft();
      } else if (key.equals("f")) {
        this.mouseSelections.flip();
      }
    }
  }

  //updates the timer in the game
  public void onTick() {
    this.time.increaseTime();
  }

  //draws the last world
  public WorldScene drawLastScene(){
    WorldScene lastWorld = this.makeScene();
    WorldImage textImg = new TextImage("Congratulations", 50, Color.CYAN);
    lastWorld.placeImageXY(textImg, lastWorld.width/2, lastWorld.height/2);
    return lastWorld;
  }

  //determines if the world ends
  public WorldEnd worldEnds() {
    if (this.gameBoard.isCompleted()) {
      return new WorldEnd(true, this.drawLastScene());
    } else {
      return new WorldEnd(false, this.makeScene());
    }
  }
  //updates the mouse events
  public void onMouseClicked(Posn pos) {
    WorldImage puzzleImage = this.puzzleImage;
    int dimensions = this.gameDimensions;
    int imageXOffset = Double.valueOf(puzzleImage.getWidth() / 2).intValue();


    if (this.mouseSelections.clickedPiece(pos, dimensions, imageXOffset,
      this.gameBoard.board.get(0).get(0))) {
      this.mouseSelections.updateSelections(this.gameBoard.findPiece(pos)); //updates
      if (this.mouseSelections.swapReady()) {
        this.mouseSelections.swapPieces();
        this.mouseSelections.clearSelections();
      }
    }
  }
}
