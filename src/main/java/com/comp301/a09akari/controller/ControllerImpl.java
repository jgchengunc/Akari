package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;

public class ControllerImpl implements AlternateMvcController {
  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  public void clickNextPuzzle() {
    if (model.getActivePuzzleIndex() < model.getPuzzleLibrarySize() - 1) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    }
  }

  public void clickPrevPuzzle() {
    if (model.getActivePuzzleIndex() > 0) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    }
  }

  public void clickRandPuzzle() {
    int index = model.getActivePuzzleIndex();
    while (index == model.getActivePuzzleIndex()) {
      index = (int) (Math.random() * model.getPuzzleLibrarySize());
    }
    model.setActivePuzzleIndex(index);
  }

  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  public void clickCell(int r, int c) {
    if (getActivePuzzle().getCellType(r, c).equals(CellType.CORRIDOR)) {
      if (isLamp(r, c)) {
        model.removeLamp(r, c);
      } else {
        model.addLamp(r, c);
      }
    }
  }

  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  public boolean isLampIllegal(int r, int c) {
    return model.isLampIllegal(r, c);
  }

  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  public boolean isSolved() {
    return model.isSolved();
  }

  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }

  public int getActivePuzzleIndex() {
    return model.getActivePuzzleIndex();
  }

  public int getPuzzleLibrarySize() {
    return model.getPuzzleLibrarySize();
  }
}
