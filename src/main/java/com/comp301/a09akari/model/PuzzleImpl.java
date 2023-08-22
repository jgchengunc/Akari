package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || r >= getHeight() || c < 0 || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    switch (board[r][c]) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
        return CellType.CLUE;
      case 5:
        return CellType.WALL;
      case 6:
        return CellType.CORRIDOR;
    }
    throw new IllegalArgumentException();
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || r >= getHeight() || c < 0 || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (!getCellType(r, c).equals(CellType.CLUE)) {
      throw new IllegalArgumentException();
    }
    return board[r][c];
  }
}
