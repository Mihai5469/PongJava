package com.esercizi.javafx.pongjava;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends GameApplication {
    private Entity player;
    private Entity palina;
    private Entity enemy;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Pong");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initGame(){
        FXGL.getGameWorld().addEntityFactory(new Factory());

        player = FXGL.spawn("player");
        palina = FXGL.spawn("palina");
        enemy = FXGL.spawn("enemy");
    }

    @Override
    protected void initInput(){
        FXGL.onKey(KeyCode.A, () -> {
          player.translateX(-5);
        });
        FXGL.onKey(KeyCode.D, () -> {
            player.translateX(5);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}