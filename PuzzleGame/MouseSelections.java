package PuzzleGame;
import javalib.worldimages.*;

//To represent the last two pieces that have been clicked before a swap
class MouseSelections {
   Piece selectedPiece1;
   Piece selectedPiece2;

  MouseSelections( Piece selectedPiece1,  Piece selectedPiece2) {
    this.selectedPiece1 = selectedPiece1;
    this.selectedPiece2 = selectedPiece2;
  }
  // determines if the user has clicked in the bounds of a given piece
  boolean clickedPiece(Posn pos, int dimensions, int xOffset ,  Piece piece) {
    // gets the area of a single puzzle piece
    int imageLength = Double.valueOf(piece.getImageWidth()).intValue();

    // setting the boundaries of the click area
    int mouseX = pos.x;
    int mouseY = pos.y - 50;
    int minX = (xOffset - (imageLength / 2));
    int maxX = ((xOffset + (imageLength * dimensions)) - (imageLength / 2));
    int minY = 0;
    int maxY = (imageLength * dimensions) - (imageLength / 2);
    // is this mouse position within the bounds of the piece?
    return (mouseX > minX && mouseX < maxX) && (mouseY > minY && mouseY < maxY);
  }

  //Determines if there are 2 pieces that have been selected
  boolean swapReady() {
    return this.selectedPiece1 != null && this.selectedPiece2 != null;
  }

  //Updates the selections with the given piece
  void updateSelections( Piece piece) {
    piece.select(); //updates the piece's selected status
    if (this.selectedPiece1 == null) {
      this.selectedPiece1 = piece;
    } else {
      this.selectedPiece2 = piece;
    }
  }

  //clears both selected pieces
  void clearSelections() {
    this.selectedPiece1 = null;
    this.selectedPiece2 = null;
  }

  //swaps the selected pieces if they are different otherwise deselects the piece
  void swapPieces() {
    this.selectedPiece1.deselect(); //deselects piece 1
    this.selectedPiece2.deselect(); //deselects piece 2
    if(this.selectedPiece1.samePiece(this.selectedPiece2)) {
      this.clearSelections(); //the pieces are the same, so nothing should be swapped
    } else {
      this.selectedPiece1.swap(this.selectedPiece2); //swaps piece 1 with piece 2
    }
  }


  boolean canApply() {
    return this.selectedPiece1 != null;
  }

  void flip() {
    this.selectedPiece1.flip();
  }

  void rotateRight() {
    this.selectedPiece1.rotateRight();
  }

  void rotateLeft() {
    this.selectedPiece1.rotateLeft();
  }

}
