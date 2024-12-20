package PuzzleGame;
import javalib.impworld.*;
import javalib.worldimages.*;

//to represent a single piece in the puzzle
class Piece {
  WorldImage image; //the image assigned to this piece
  Position correctPosn; //the correct position of this piece
  Position currentPosn; //the current position of this piece
  int orientation; //the orientation of this piece
  Position drawPosn; //the positon to draw this piece
  boolean selected; //determines if this piece has been selected

  Piece(WorldImage image, Position correctPosn, Position currentPosn, int orientation, Position drawPosn) {
    this.image = image;
    this.correctPosn = correctPosn;
    this.currentPosn = currentPosn;
    this.orientation = orientation;
    this.drawPosn = drawPosn;
    this.selected = false;
  }

  //Draws the piece in the puzzle
  WorldScene drawPiece(WorldScene scn){
    return this.drawPosn.drawPieceAtPosn(this.image, this.orientation, this.selected, scn);
  }

  // swaps the position of the 2 pieces clicked by the user
  void swap(Piece otherPiece) {
    WorldImage tempImage = this.image;
    Position tempPosition = this.currentPosn;
    int tempOrientation = this.orientation;
    this.image = otherPiece.image;
    this.currentPosn = otherPiece.currentPosn;
    this.orientation = otherPiece.orientation;
    otherPiece.currentPosn = tempPosition;
    otherPiece.image = tempImage;
    otherPiece.orientation = tempOrientation;
  }
  // is this piece the same as the other piece?
  boolean samePiece(Piece otherPiece) {
    return otherPiece.sameCurrentPosn(this.currentPosn) && otherPiece.sameDrawPosn(this.drawPosn);
  }
  // is this current position the same as the other current position?
  boolean sameCurrentPosn(Position otherPosn) {
    return otherPosn.samePosn(this.currentPosn);
  }

  // is this draw position the same as the other draw position?
  boolean sameDrawPosn(Position otherPosn) {
    return otherPosn.samePosn(this.drawPosn);
  }
  // flips the image by reflecting it
  void flip(){
    if (this.orientation == 90) {
      this.orientation = 270;
    } else if (this.orientation == 180) {
      this.orientation = 0;
    } else if (this.orientation == 270) {
      this.orientation = 90;
    } else {
      this.orientation = 180;
    }
  }

  boolean inPlace(){
    return this.orientation == 0 && this.currentPosn.samePosn(this.correctPosn);
  }

  void rotateRight(){
    if (this.orientation == 0) {
      this.orientation = 90;
    } else if (this.orientation == 90) {
      this.orientation = 180;
    } else if (this.orientation == 180) {
      this.orientation = 270;
    } else {
      this.orientation = 0;
    }
  }

  void rotateLeft(){
    if (this.orientation == 0) {
      this.orientation = 270;
    } else if (this.orientation == 270) {
      this.orientation = 180;
    } else if (this.orientation == 180) {
      this.orientation = 90;
    } else {
      this.orientation = 0;
    }
  }
  // did the user click
  boolean clicked(Posn mousePosn) {
    int imageLength = Double.valueOf(this.image.getWidth()).intValue();
    return this.drawPosn.inBetween(mousePosn, imageLength);
  }

  //updates this pieces selection status to true
  void select() {
    this.selected = true;
  }
  //updates this pieces selection status to true
  void deselect() {
    this.selected = false;
  }
  //return this image's width
  double getImageWidth() {
    return this.image.getWidth();
  }
}
