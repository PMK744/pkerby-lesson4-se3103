package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controller.App;
import controller.ButtonListener;
import controller.NewGameListener;
import controller.StrategyListener;
import model.Marking;
import model.PlayStrategy;
import model.TicTacToeGame;

public class AppWindow extends JFrame {
  public static final String vsHumanAction = "vs. Human";
  public static final String vsComputerAction = "vs. Computer";

  private AppCanvas canvas = new AppCanvas();
  private BoardButton[] markingButtons = new BoardButton[9];
  private JButton newGameButton = new JButton("New Game");
  private JButton exitButton = new JButton("Exit");
  private JRadioButton vsHumanButton = new JRadioButton(vsHumanAction);
  private JRadioButton vsComputerButton = new JRadioButton(vsComputerAction);

  public void init() {
    var cp = this.getContentPane();
    cp.add(this.canvas, BorderLayout.NORTH);

    ButtonListener buttonListener = new ButtonListener();
    for (int i = 0; i < markingButtons.length; i++) {
      markingButtons[i] = new BoardButton(i);
      markingButtons[i].addActionListener(buttonListener);
    }

    JPanel gameBoardPanel = new JPanel();
    gameBoardPanel.setLayout(new GridLayout(3, 3));
    for (var cell : markingButtons) {
      gameBoardPanel.add(cell);
    }

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new GridLayout(2, 1));

    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(new TitledBorder("Play strategy"));
    this.vsHumanButton = new JRadioButton(vsHumanAction, App.game.getStrategy() == PlayStrategy.VsHuman);
    this.vsComputerButton = new JRadioButton(vsComputerAction, App.game.getStrategy() == PlayStrategy.VsComputer);
    radioPanel.add(vsHumanButton);
    radioPanel.add(vsComputerButton);
    ButtonGroup strategyGroup = new ButtonGroup();
    strategyGroup.add(vsHumanButton);
    strategyGroup.add(vsComputerButton);
    southPanel.add(radioPanel);

    StrategyListener strategyListener = new StrategyListener();
    vsHumanButton.addActionListener(strategyListener);
    vsComputerButton.addActionListener(strategyListener);

    JPanel actionPanel = new JPanel();
    actionPanel.setBorder(new TitledBorder("Action"));
    actionPanel.add(newGameButton);
    actionPanel.add(exitButton);
    southPanel.add(actionPanel);

    newGameButton.addActionListener(new NewGameListener());
    exitButton.addActionListener(e -> System.exit(0));

    cp.add(gameBoardPanel, BorderLayout.CENTER);
    cp.add(southPanel, BorderLayout.SOUTH);

    this.updateWindow();
  }

  public void updateWindow() {
    TicTacToeGame game = App.game;
    Marking[] board = game.getBoard();

    for (int i = 0; i < board.length; i++) {
      this.markingButtons[i].setMark(board[i]);
    }

    switch (game.getState()) {
      case Over:
      case Init: {
        for (var button : this.markingButtons) {
          button.setEnabled(false);
        }

        this.newGameButton.setEnabled(true);
        this.vsHumanButton.setEnabled(true);
        this.vsComputerButton.setEnabled(true);

        break;
      }

      case Playing: {
        this.newGameButton.setEnabled(false);
        this.vsHumanButton.setEnabled(false);
        this.vsComputerButton.setEnabled(false);

        for (int i = 0; i < board.length; i++) {
          this.markingButtons[i].setEnabled(board[i] == Marking.U);
        }

        break;
      }
    }

    canvas.repaint();
  }
}
