package PuzzleGame;
import javalib.impworld.*;
import javalib.worldimages.*;
import java.util.ArrayList;
import java.util.Random;

//To represent the puzzle board
class PuzzleBoard {
  ArrayList<ArrayList< Piece>> board;

  PuzzleBoard(ArrayList<ArrayList< Piece>> board) {
    this.board = board;
  }

  //Scrambles the pieces in the board to randomize them
  void scramble() {
    Random rand = new Random();
    for (int i = this.board.size() - 1; i > 0; i = i - 1) {
      for (int j = this.board.size() - 1; j > 0; j = j - 1) {
        int x = rand.nextInt(i + 1);
        int y = rand.nextInt(j + 1);

         Piece swappee = this.board.get(i).get(j);
         Piece swapper = this.board.get(x).get(y);
        WorldImage tempImage = swappee.image;
         Position tempPosition = swappee.currentPosn;
        swappee.currentPosn = swapper.currentPosn;
        swappee.image = swapper.image;
        swapper.image = tempImage;
        swapper.currentPosn = tempPosition;

      }
    }
  }

  //draws the board onto the scene
  WorldScene drawBoard(WorldScene scn) {
    for (ArrayList< Piece> rowPieces : this.board) {
      for ( Piece piece : rowPieces) {
        scn = piece.drawPiece(scn);
      }
    }
    return scn;
  }

  //Determines if the given board is completed
  boolean isCompleted(){
    boolean result = true;
    for (ArrayList< Piece> rowPieces : this.board) {
      for ( Piece piece : rowPieces) {
        if (!piece.inPlace()) {
          result = false;
        }
      }
    }
    return result;
  }

  //Finds the piece clicked based on the given position clicked
   Piece findPiece(Posn pos) {
     Piece targetPiece = null;
    for (ArrayList< Piece> rowPieces : this.board) {
      for ( Piece piece : rowPieces) {
        if (piece.clicked(pos)) {
          targetPiece = piece;
        }
      }
    }
    return targetPiece;
  }
}

