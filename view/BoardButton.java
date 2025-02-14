package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import model.Marking;

public class BoardButton extends JButton {
  private int pos; // Index of the button in the board

  public BoardButton(int pos) {
    this.pos = pos;
    this.setFont(new Font("Courier New", Font.BOLD, 84));
    this.setForeground(Color.BLUE);
    this.setMark(Marking.U);
  }

  public int getPos() {
    return this.pos;
  }

  public void setMark(Marking mark) {
    String label = mark.name();
    if (mark == Marking.U) label = ".";

    this.setText(label);
  }
}
