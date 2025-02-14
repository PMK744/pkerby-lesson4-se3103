package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameState;
import model.TicTacToeGame;
import view.BoardButton;

public class ButtonListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    TicTacToeGame game = App.game;
    BoardButton button = (BoardButton) e.getSource();

    game.play(button.getPos());

    if (game.getWinner() != null) {
      game.setState(GameState.Over);
      System.out.println("Winner: " + game.getWinner());
    } else {
      game.changeTurn();
    }

    App.window.updateWindow();
  }
}
