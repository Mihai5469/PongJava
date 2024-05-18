package com.esercizi.javafx.pongjava;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;


//TODO devo fissare il bug che fa andare il giovatore fuori dal campo

public class PongApp extends GameApplication {
    private Entity player;
    private Entity palina;
    private int direzionePalinaVe = 5;
    private int direzionePalinaOr = 3;
    private Entity enemy;
    private Entity wallR;
    private Entity wallL;
    private int score = 0;

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

        //player2.addComponent(new PlayerComponent());

        player = FXGL.spawn("player", FXGL.getAppWidth()/2-50, FXGL.getAppHeight() -20);
        palina = FXGL.spawn("palina", FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
        enemy = FXGL.spawn("enemy" ,FXGL.getAppWidth()/2-50, 10);
        wallL = FXGL.spawn("wallL", 0, 0);  //muro sinistro
        wallR = FXGL.spawn("wallR", FXGL.getAppWidth()-10, 0); // muro destro

        getGameTimer().runAtInterval(()->{
            palina.translateY(direzionePalinaVe);

            //Controllo posizione palina
            if(palina.getY() > FXGL.getAppHeight()){
                score += 1;
                System.out.println("Il punteggio e : " +score);
                palina.setX(FXGL.getAppWidth()/2);
                palina.setY(FXGL.getAppHeight()/2);
            }
            palina.translateX(direzionePalinaOr);
            if(palina.getX() >= 50 && palina.getX() <= FXGL.getAppWidth()-50) {
                enemy.setX(palina.getX() - 50);
            }

            }, Duration.millis(3));
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
                direzionePalinaVe *= -1;
            }
        });

        physics.addCollisionHandler(new CollisionHandler(OgettiGioco.enemy, OgettiGioco.palina) {
            @Override
            protected void onCollisionBegin(Entity enemy, Entity palina) {
                direzionePalinaVe *= -1;
            }
        });


        physics.addCollisionHandler(new CollisionHandler(OgettiGioco.palina, OgettiGioco.wall) {
            @Override
            protected void onCollisionBegin(Entity wallL, Entity palina) {
                direzionePalinaOr *= -1;
            }
        });

        /*
        physics.addCollisionHandler(new CollisionHandler(OgettiGioco.enemy, OgettiGioco.wall) {
            @Override
            protected void onCollisionBegin(Entity enemy, Entity wall) {
                enemy.translateX(-50);
            }
        });

         */


    }

    public static void main(String[] args) {
        launch(args);
    }

}