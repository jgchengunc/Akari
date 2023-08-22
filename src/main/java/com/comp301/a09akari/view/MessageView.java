package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MessageView implements FXComponent {
  ControllerImpl controller;

  public MessageView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox hbox = new HBox();
    hbox.getStyleClass().add("hbox");

    Label index =
        new Label(
            "Puzzle "
                + (controller.getActivePuzzleIndex() + 1)
                + " of "
                + controller.getPuzzleLibrarySize());
    hbox.getChildren().add(index);

    if (controller.isSolved()) {
      Label completed = new Label("Puzzle Completed!");
      completed.getStyleClass().add("completed");
      hbox.getChildren().add(completed);
    }

    return hbox;
  }
}
