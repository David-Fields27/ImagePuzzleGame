package PuzzleGame;
import javalib.impworld.*;
import javalib.worldimages.*;
import java.awt.Color;

//to represent a position
class Position {
  int row; //the x of the piece
  int col; //the y of the piece

  Position(int row, int col){
    this.row = row;
    this.col = col;
  }

  //Draws the piece's image, orientation, and selection indication at the given board
  WorldScene drawPieceAtPosn(WorldImage image, int orientation, boolean selected, WorldScene scn) {
    WorldImage pieceImage = new RotateImage(image, orientation);
    int imageLength = Double.valueOf(image.getWidth()).intValue();
    if (selected) {
      pieceImage = new OverlayImage(pieceImage,
        new RectangleImage(imageLength + 10, imageLength + 10, OutlineMode.SOLID, Color.BLACK));
    }
    scn.placeImageXY(pieceImage, this.row, this.col);
    return scn;
  }

  //Determines if this position is the same as the given position
  boolean samePosn(Position otherPosn) {
    return otherPosn.sameRow(this.row) && otherPosn.sameCol(this.col);
  }

  //Determines if this row is the same as the given row
  boolean sameRow(int otherRow) {
    return this.row == otherRow;
  }

  //Determines if this row is the same as the given row
  boolean sameCol(int otherCol) {
    return this.col == otherCol;
  }

  //Determines the mouse position is between this current position
  boolean inBetween(Posn mousePosn, int imageLength) {
    int mouseX = mousePosn.x;
    int mouseY = mousePosn.y;
    int halfLength = imageLength / 2;
    int lowerBoundX = this.row - halfLength;
    int upperBoundX = this.row + halfLength;
    int lowerBoundY = this.col - halfLength;
    int upperBoundY = this.col + halfLength;
    return ((mouseX >= lowerBoundX && mouseX <= upperBoundX) &&
      (mouseY >= lowerBoundY && mouseY <= upperBoundY));
  }

}
