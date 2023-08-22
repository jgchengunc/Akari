package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PuzzleView implements FXComponent {
  ControllerImpl controller;

  public PuzzleView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    grid.getStyleClass().add("grid");

    for (int r = 0; r < controller.getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < controller.getActivePuzzle().getWidth(); c++) {
        if (controller.getActivePuzzle().getCellType(r, c).equals(CellType.CLUE)) {
          Label tile = new Label(String.valueOf(controller.getActivePuzzle().getClue(r, c)));
          tile.getStyleClass().add("clue");
          tile.getStyleClass().add("cell");
          if (controller.isClueSatisfied(r, c)) {
            tile.getStyleClass().add("satisfiedClue");
          }
          grid.add(tile, c, r);

        } else if (controller.getActivePuzzle().getCellType(r, c).equals(CellType.CORRIDOR)) {
          Button tile = new Button();
          if (controller.isLit(r, c)) {
            if (controller.isLamp(r, c)) {
              ImageView image = new ImageView(new Image("file:src/main/resources/light-bulb.png"));
              image.setFitHeight(20);
              image.setPreserveRatio(true);
              image.setCache(true);
              tile.setGraphic(image);
              tile.setPadding(Insets.EMPTY);
              if (controller.isLampIllegal(r, c)) {
                tile.getStyleClass().add("illegalLampCorridor");
              } else {
                tile.getStyleClass().add("lampCorridor");
              }
            } else {
              tile.getStyleClass().add("litCorridor");
            }
          } else {
            tile.getStyleClass().add("unlitCorridor");
          }
          int finalR = r;
          int finalC = c;
          tile.setOnAction(
              (ActionEvent event) -> {
                controller.clickCell(finalR, finalC);
              });
          tile.getStyleClass().add("cell");
          grid.add(tile, c, r);

        } else {
          Label tile = new Label();
          tile.getStyleClass().add("wall");
          tile.getStyleClass().add("cell");
          grid.add(tile, c, r);
        }
      }
    }
    return grid;
  }
}
