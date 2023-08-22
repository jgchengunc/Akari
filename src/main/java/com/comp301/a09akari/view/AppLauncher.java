package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
    Model model = new ModelImpl(library);
    ControllerImpl controller = new ControllerImpl(model);

    BorderPane pane = new BorderPane();
    FXComponent message = new MessageView(controller);
    FXComponent puzzle = new PuzzleView(controller);
    FXComponent control = new ControlView(controller);
    pane.setTop(message.render());
    pane.setCenter(puzzle.render());
    pane.setBottom(control.render());

    Scene scene = new Scene(pane, 475, 450);
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);
    stage.setTitle("Akari");
    stage.setResizable(false);
    stage.show();

    model.addObserver(
        (Model m) -> {
          pane.setTop(message.render());
          pane.setCenter(puzzle.render());
          pane.setBottom(control.render());
          scene.setRoot(pane);
          stage.setScene(scene);
          stage.show();
        });
  }
}
