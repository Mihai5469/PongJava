package com.esercizi.javafx.pongjava;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class PongApp extends GameApplication {
    private Entity player;
    private Entity palina;
    private int direzionePalina = 5;
    private int direzionePalinaVe = -3;
    private Entity enemy;
    private Entity wallR, wallL;

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
        wallL = FXGL.spawn("wall", 0, 0);
        wallR = FXGL.spawn("wall", 750, 0);

        getGameTimer().runAtInterval(()->{
            palina.translateY(direzionePalina);
            //System.out.println(FXGL.getAppWidth())




            palina.translateX(direzionePalinaVe);

            enemy.setX(palina.getX()-50);

            }, Duration.millis(16));
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

    @Override
    protected void initPhysics(){
        PhysicsWorld physics = FXGL.getPhysicsWorld();


        physics.addCollisionHandler(new CollisionHandler(OgettiGioco.player, OgettiGioco.palina) {
            @Override
            protected void onCollisionBegin(Entity player, Entity palina) {
                direzionePalina *= -1;
            }
        });

        physics.addCollisionHandler(new CollisionHandler(OgettiGioco.enemy, OgettiGioco.palina) {
            @Override
            protected void onCollisionBegin(Entity enemy, Entity palina) {
                direzionePalina *= -1;
            }
        });

        //TODO devo fare in modo che la palina rimbalzi lateralmente
        physics.addCollisionHandler(new CollisionHandler(OgettiGioco.palina, OgettiGioco.wall) {
            @Override
            protected void onCollisionBegin(Entity wall, Entity palina) {
                direzionePalinaVe *= -1;
            }
        });


    }

    public static void main(String[] args) {
        launch(args);
    }

}