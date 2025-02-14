package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import controller.App;
import model.GameState;
import model.Marking;
import model.TicTacToeGame;

public class AppCanvas extends JPanel {

  public static final int WIDTH = 400;
  public static final int HEIGHT = 100;

  public AppCanvas() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setFont(new Font("Courier New", Font.BOLD, 16));

    TicTacToeGame game = App.game;
    GameState state = game.getState();

    switch (state) {
      case Init: {
        g2d.setColor(Color.BLUE);
        g2d.drawString("Press <New Game> to start", 50, 50);
        break;
      }
      case Playing: {
        g2d.setColor(Color.BLUE);
        g2d.drawString("Turn: " + game.getTurn(), 50, 90);
        break;
      }
      case Over: {
        Marking winner = game.getWinner();
        String message = winner + " has won!";
        if (winner == Marking.U) message = "Draw/Tie";

        g2d.setColor(Color.RED);
        g2d.drawString("Game Over: " + message, 50, 50);
        g2d.drawString("Press <New Game> to play again!", 50, 80);
        break;
      }
    }
  }
}
