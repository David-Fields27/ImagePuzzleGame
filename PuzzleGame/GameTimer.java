package PuzzleGame;
import javalib.impworld.*;
import javalib.worldimages.*;
import java.awt.*;

//To represent the timer in the game
class GameTimer {
  int second;
  int minute;
  int hour;

  GameTimer(){
    this.second = 0;
    this.minute = 0;
    this.hour = 0;
  }
  // draws the current time
  WorldScene drawTime(WorldScene scn) {
    WorldImage time;
    if (this.hour == 0) {
      if (this.minute == 0) {
        time = new TextImage("Time: " +this.second, 20, Color.BLACK);
      } else {
        if (this.second < 10) {
          time = new TextImage("Time: " + this.minute + ":0" + this.second, 20, Color.BLACK);
        } else {
          time = new TextImage("Time: " + this.minute + ":" + this.second, 20, Color.BLACK);
        }
      }
    } else {
      if (this.second < 10) {
        time = new TextImage("Time: " + this.hour + ":" + this.minute + ":0" + this.second,
          20, Color.BLACK);
      } else {
        time = new TextImage("Time: " + this.hour + ":" + this.minute + ":" + this.second,
          20, Color.BLACK);
      }
    }
    scn.placeImageXY(time, scn.width/10, scn.height /10);
    return scn;
  }
  // increments time by the appropriate amount
  void increaseTime() {
    this.second = this.second + 1;
    if(this.second == 60){
      this.second = 0;
      this.minute = this.minute + 1;
    }
    if(this.minute == 60){
      this.minute = 0;
      this.hour = this.hour + 1;
    }
  }
}
