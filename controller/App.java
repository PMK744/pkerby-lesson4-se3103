package controller;

import javax.swing.JFrame;

import model.TicTacToeGame;
import view.AppWindow;

public class App {
  public static AppWindow window = new AppWindow();
  public static TicTacToeGame game = new TicTacToeGame();

  public static void main(String[] args) {
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.init();
    window.setLocation(300, 200);
    window.pack();
    window.setVisible(true);
  }
}
