package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary library;
  private int index;
  private boolean[][] lamps;
  private List<ModelObserver> observers;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null || library.size() == 0) {
      throw new IllegalArgumentException();
    }
    this.library = library;
    index = 0;
    lamps = new boolean[getActivePuzzle().getHeight()][getActivePuzzle().getWidth()];
    observers = new ArrayList<>();
  }

  public void addLamp(int r, int c) {
    if (r < 0 || r >= lamps.length || c < 0 || c >= lamps[0].length) {
      throw new IndexOutOfBoundsException();
    }
    if (!getActivePuzzle().getCellType(r, c).equals(CellType.CORRIDOR)) {
      throw new IllegalArgumentException();
    }
    if (!lamps[r][c]) {
      lamps[r][c] = true;
      notifyObservers();
    }
  }

  public void removeLamp(int r, int c) {
    if (r < 0 || r >= lamps.length || c < 0 || c >= lamps[0].length) {
      throw new IndexOutOfBoundsException();
    }
    if (!getActivePuzzle().getCellType(r, c).equals(CellType.CORRIDOR)) {
      throw new IllegalArgumentException();
    }
    if (lamps[r][c]) {
      lamps[r][c] = false;
      notifyObservers();
    }
  }

  public boolean isLit(int r, int c) {
    if (r < 0 || r >= lamps.length || c < 0 || c >= lamps[0].length) {
      throw new IndexOutOfBoundsException();
    }
    if (!getActivePuzzle().getCellType(r, c).equals(CellType.CORRIDOR)) {
      throw new IllegalArgumentException();
    }
    if (lamps[r][c]) {
      return true;
    }

    List<Integer> rowLamps = new ArrayList<>();
    for (int i = 0; i < lamps[0].length; i++) {
      if (lamps[r][i]) {
        rowLamps.add(i);
      }
    }
    for (Integer lamp : rowLamps) {
      if (lamp < c) {
        if (lamp == c - 1) {
          return true;
        }
        for (int i = lamp + 1; i < c; i++) {
          if (!getActivePuzzle().getCellType(r, i).equals(CellType.CORRIDOR)) {
            break;
          }
          if (i == c - 1) {
            return true;
          }
        }
      } else {
        if (lamp == c + 1) {
          return true;
        }
        for (int i = lamp - 1; i > c; i--) {
          if (!getActivePuzzle().getCellType(r, i).equals(CellType.CORRIDOR)) {
            break;
          }
          if (i == c + 1) {
            return true;
          }
        }
      }
    }

    List<Integer> colLamps = new ArrayList<>();
    for (int i = 0; i < lamps.length; i++) {
      if (lamps[i][c]) {
        colLamps.add(i);
      }
    }
    for (Integer lamp : colLamps) {
      if (lamp < r) {
        if (lamp == r - 1) {
          return true;
        }
        for (int i = lamp + 1; i < r; i++) {
          if (!getActivePuzzle().getCellType(i, c).equals(CellType.CORRIDOR)) {
            break;
          }
          if (i == r - 1) {
            return true;
          }
        }
      } else {
        if (lamp == r + 1) {
          return true;
        }
        for (int i = lamp - 1; i > r; i--) {
          if (!getActivePuzzle().getCellType(i, c).equals(CellType.CORRIDOR)) {
            break;
          }
          if (i == r + 1) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isLamp(int r, int c) {
    if (r < 0 || r >= lamps.length || c < 0 || c >= lamps[0].length) {
      throw new IndexOutOfBoundsException();
    }
    if (!getActivePuzzle().getCellType(r, c).equals(CellType.CORRIDOR)) {
      throw new IllegalArgumentException();
    }
    return lamps[r][c];
  }

  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || r >= lamps.length || c < 0 || c >= lamps[0].length) {
      throw new IndexOutOfBoundsException();
    }
    if (!lamps[r][c]) {
      throw new IllegalArgumentException();
    }

    List<Integer> rowLamps = new ArrayList<>();
    for (int i = 0; i < lamps[0].length; i++) {
      if (lamps[r][i] && i != c) {
        rowLamps.add(i);
      }
    }
    for (Integer lamp : rowLamps) {
      if (lamp < c) {
        if (lamp == c - 1) {
          return true;
        }
        for (int i = lamp + 1; i < c; i++) {
          if (!getActivePuzzle().getCellType(r, i).equals(CellType.CORRIDOR)) {
            break;
          }
          if (i == c - 1) {
            return true;
          }
        }
      } else {
        if (lamp == c + 1) {
          return true;
        }
        for (int i = lamp - 1; i > c; i--) {
          if (!getActivePuzzle().getCellType(r, i).equals(CellType.CORRIDOR)) {
            break;
          }
          if (i == c + 1) {
            return true;
          }
        }
      }
    }

    List<Integer> colLamps = new ArrayList<>();
    for (int i = 0; i < lamps.length; i++) {
      if (lamps[i][c] && i != r) {
        colLamps.add(i);
      }
    }
    for (Integer lamp : colLamps) {
      if (lamp < r) {
        if (lamp == r - 1) {
          return true;
        }
        for (int i = lamp + 1; i < r; i++) {
          if (!getActivePuzzle().getCellType(i, c).equals(CellType.CORRIDOR)) {
            break;
          }
          if (i == r - 1) {
            return true;
          }
        }
      } else {
        if (lamp == r + 1) {
          return true;
        }
        for (int i = lamp - 1; i > r; i--) {
          if (!getActivePuzzle().getCellType(i, c).equals(CellType.CORRIDOR)) {
            break;
          }
          if (i == r + 1) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public Puzzle getActivePuzzle() {
    return library.getPuzzle(index);
  }

  public int getActivePuzzleIndex() {
    return index;
  }

  public void setActivePuzzleIndex(int index) {
    this.index = index;
    lamps = new boolean[getActivePuzzle().getHeight()][getActivePuzzle().getWidth()];
    notifyObservers();
  }

  public int getPuzzleLibrarySize() {
    return library.size();
  }

  public void resetPuzzle() {
    lamps = new boolean[getActivePuzzle().getHeight()][getActivePuzzle().getWidth()];
    notifyObservers();
  }

  public boolean isSolved() {
    for (int r = 0; r < lamps.length; r++) {
      for (int c = 0; c < lamps[0].length; c++) {
        if ((getActivePuzzle().getCellType(r, c).equals(CellType.CORRIDOR)
                && (!isLit(r, c) || (isLamp(r, c) && isLampIllegal(r, c))))
            || (getActivePuzzle().getCellType(r, c).equals(CellType.CLUE)
                && !isClueSatisfied(r, c))) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || r >= lamps.length || c < 0 || c >= lamps[0].length) {
      throw new IndexOutOfBoundsException();
    }
    if (!getActivePuzzle().getCellType(r, c).equals(CellType.CLUE)) {
      throw new IllegalArgumentException();
    }

    int lampCount = 0;
    if (c > 0 && lamps[r][c - 1]) {
      lampCount++;
    }
    if (c < lamps[0].length - 1 && lamps[r][c + 1]) {
      lampCount++;
    }
    if (r > 0 && lamps[r - 1][c]) {
      lampCount++;
    }
    if (r < lamps.length - 1 && lamps[r + 1][c]) {
      lampCount++;
    }

    return lampCount == getActivePuzzle().getClue(r, c);
  }

  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }
}
