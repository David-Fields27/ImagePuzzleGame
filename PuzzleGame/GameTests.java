package PuzzleGame;
import tester.*;
import javalib.impworld.*;
import javalib.worldimages.*;
import java.util.ArrayList;
import java.util.Random;
import javalib.impworld.*;
import javalib.worldimages.*;
import java.awt.*;

//Testing file
class GameTests {

  void testIncreaseTime(Tester t) {
    GameTimer t1 = new GameTimer();
    GameTimer t2 = new GameTimer();
    t2.second = 59;
    GameTimer t3 = new GameTimer();
    t3.minute = 59;
    t3.second = 59;
    GameTimer t4 = new GameTimer();
    t4.hour = 23;
    t4.minute = 59;
    t4.second = 59;

    t1.increaseTime();
    t.checkExpect(t1.second, 1);
    t.checkExpect(t1.minute, 0);
    t.checkExpect(t1.hour, 0);

    t2.increaseTime();
    t.checkExpect(t2.second, 0);
    t.checkExpect(t2.minute, 1);
    t.checkExpect(t2.hour, 0);

    t3.increaseTime();



  }







}
