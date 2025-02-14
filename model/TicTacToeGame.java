package model;

public class TicTacToeGame {
  private Marking[] board = new Marking[9];
  private Marking turn = Marking.X;
  private int moves = 0;
  private Marking winner = null;
  private GameState state = GameState.Init;
  private PlayStrategy strategy = PlayStrategy.VsHuman;

  public TicTacToeGame() {
    this.reset();
  }

  public void reset() {
    for (int i = 0; i < board.length; i++) {
      this.board[i] = Marking.U;
    }

    this.turn = Marking.X;
    this.moves = 0;
    this.winner = null;
  }

  public void changeTurn() {
    this.turn = this.turn == Marking.X ? Marking.O : Marking.X;
  }

  public void play(int position) {
    if (this.strategy == PlayStrategy.VsHuman) {
      this.humanPlayer(position);
      this.setWinner();
    } else if (this.strategy == PlayStrategy.VsComputer) {
      this.humanPlayer(position);
      this.setWinner();
      if (this.winner != null) return;
      this.changeTurn();
      this.computerPlayer();
      this.setWinner();
    }
  }

  private void humanPlayer(int position) {
    this.board[position] = this.turn;
    ++this.moves;
  }

  private void computerPlayer() {
    int pos = this.computerPick();
    this.board[pos] = this.turn;
    ++this.moves;
  }

  private int computerPick() {
    int pos = -1;
    for (int i = 0; i < this.board.length; i++) {
      if (this.board[i] == Marking.U) {
        pos = i;
        break;
      }
    }

    assert pos >= 0 : "Invalid position from computer player";

    return pos;
  }

  public Marking getWinner() {
    return winner;
  }

  public void setWinner() {
    for (int i = 0; i < 3; i++) {
      this.winner = this.checkRow(i);
      if (this.winner != null) return;
      
      this.winner = this.checkColumn(i);
      if (this.winner != null) return;
    }

    this.winner = this.checkDiagonal();
    if (this.winner != null) return;

    if (this.moves == 9) {
      this.winner = Marking.U;
      return;
    }

    this.winner = null;
  }

  private Marking checkRow(int n) {
    int r = n * 3;

    if (
      this.board[r] != Marking.U &&
      this.board[r] == this.board[r + 1] &&
      this.board[r] == this.board[r + 2]
    ) {
      return this.board[r];
    } else {
      return null;
    }
  }

  private Marking checkColumn (int n) {
    if (
      this.board[n] != Marking.U &&
      this.board[n] == this.board[n + 3] &&
      this.board[n] == this.board[n + 6]
    ) {
      return this.board[n];
    } else {
      return null;
    }
  }

  private Marking checkDiagonal () {
    if (
      this.board[0] != Marking.U &&
      this.board[0] == this.board[4] &&
      this.board[0] == this.board[8]
    ) {
      return this.board[0];
    } else if (
      this.board[2] != Marking.U &&
      this.board[2] == this.board[4] &&
      this.board[2] == this.board[6]
    ) {
      return this.board[2];
    } else {
      return null;
    }
  }

  public GameState getState() {
    return this.state;
  }

  public void setState(GameState state) {
    this.state = state;
  }

  public PlayStrategy getStrategy() {
    return strategy;
  }

  public void setStrategy(PlayStrategy strategy) {
    this.strategy = strategy;
  }

  public Marking[] getBoard() {
    return board;
  }

  public Marking getTurn() {
    return turn;
  }

  @Override
  public String toString() {
    String r1 = String.format("%s | %s | %s\n", this.board[0], this.board[1], this.board[2]);
    String r2 = String.format("%s | %s | %s\n", this.board[3], this.board[4], this.board[5]);
    String r3 = String.format("%s | %s | %s\n", this.board[6], this.board[7], this.board[8]);
    String r4 = String.format("Winner: %s (moves: %d)\n", this.winner, this.moves);

    return r1 + r2 + r3 + r4;
  }
}
