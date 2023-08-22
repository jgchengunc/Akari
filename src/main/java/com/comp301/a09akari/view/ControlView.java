package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {
  ControllerImpl controller;

  public ControlView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox hbox = new HBox();
    hbox.getStyleClass().add("hbox");

    Button previous = new Button("Previous Puzzle");
    previous.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    hbox.getChildren().add(previous);

    Button next = new Button("Next Puzzle");
    next.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    hbox.getChildren().add(next);

    Button random = new Button("Random Puzzle");
    random.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });
    hbox.getChildren().add(random);

    Button reset = new Button("Reset");
    reset.getStyleClass().add("reset");
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    hbox.getChildren().add(reset);

    return hbox;
  }
}
