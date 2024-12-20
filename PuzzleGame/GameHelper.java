package PuzzleGame;
import javalib.worldimages.*;
import java.util.ArrayList;

//to represent a helper for the game
class GameHelper {
  //
  WorldImage cropImage(WorldImage puzzleImage, int dimensions) {
    int newPuzzleWidth = Double.valueOf(puzzleImage.getWidth() / dimensions).intValue()* dimensions;
    int newPuzzleHeight = Double.valueOf(puzzleImage.getHeight() / dimensions).intValue()* dimensions;
    WorldImage newPuzzleImage;
    if (newPuzzleWidth <= newPuzzleHeight) {
      newPuzzleImage = new CropImage(0, 0, newPuzzleWidth, newPuzzleWidth, puzzleImage);
    } else {
      newPuzzleImage = new CropImage(0, 0, newPuzzleHeight, newPuzzleHeight, puzzleImage);
    }

    if (Double.valueOf(newPuzzleImage.getWidth()).intValue() > 500) {
      newPuzzleImage = new ScaleImage(newPuzzleImage, .4);
    }
    return newPuzzleImage;
  }

  //makes the board using the given image at the given dimensions
  ArrayList<ArrayList< Piece>> makeBoard(WorldImage puzzleImage, int dimensions) {
    int newPuzzleLength = Double.valueOf(puzzleImage.getWidth()).intValue();
    int pieceLength = newPuzzleLength / dimensions;

    int halfPuzzleImageSize = Double.valueOf(puzzleImage.getWidth() / 2).intValue();
    ArrayList<ArrayList< Piece>> board = new ArrayList<>();
    for (int i = 0; i < dimensions; i = i + 1) {
      board.add(new ArrayList< Piece>());
      for (int j = 0; j < dimensions; j = j + 1) {
        WorldImage piece = new CropImage(i * pieceLength, j * pieceLength, pieceLength,  pieceLength, puzzleImage);
        Position piecePosition = new Position(i, j);
        Position drawPosition = new Position((i * pieceLength) + halfPuzzleImageSize, (j * pieceLength) + 50);
        board.get(i).add(new  Piece(piece, piecePosition, piecePosition,
          Double.valueOf(Math.random() * 3).intValue() * 90, drawPosition));
      }
    }
    return board;
  }
}
