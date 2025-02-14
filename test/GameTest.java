package test;

import model.TicTacToeGame;

public class GameTest {
  public static void main(String[] args) {
    TicTacToeGame game = new TicTacToeGame();
    System.out.println(game);

    game.play(0);
    game.changeTurn();
    System.out.println(game);

    game.play(1);
    game.changeTurn();
    System.out.println(game);

    game.play(3);
    game.changeTurn();
    System.out.println(game);

    game.play(2);
    game.changeTurn();
    System.out.println(game);

    game.play(6);
    game.changeTurn();
    System.out.println(game);

  }
}
