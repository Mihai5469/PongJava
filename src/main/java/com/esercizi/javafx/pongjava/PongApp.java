package com.esercizi.javafx.pongjava;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;


public class PongApp extends GameApplication {
    private Entity player;
    private Entity palina;
    private int direzionePalinaVe = 5;
    private int direzionePalinaOr = 3;
    private Entity enemy;
    private Entity wallR;
    private Entity wallL;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Pong");
        gameSettings.setVersion("0.1");
    }


    @Override
    protected void initGameVars(Map<String, Object> vars){
        vars.put("score", 0);

    }

    @Override
    protected void initGame(){
        FXGL.getGameWorld().addEntityFactory(new Factory());

        //player2.addComponent(new PlayerComponent());

        player = FXGL.spawn("player", FXGL.getAppWidth()/2-50, FXGL.getAppHeight() -20);
        palina = FXGL.spawn("palina", FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
        enemy = FXGL.spawn("enemy" ,FXGL.getAppWidth()/2-50, 10);
        wallL = FXGL.spawn("wall", 0, 0);  //muro sinistro
        wallR = FXGL.spawn("wall", FXGL.getAppWidth()-10, 0); // muro destro

        getGameTimer().runAtInterval(()->{
            palina.translateY(direzionePalinaVe);

            //Controllo posizione palina
            if(palina.getY() > FXGL.getAppHeight()){
                FXGL.inc("score", 1);
                //score += 1;
                //System.out.println("Il punteggio e : " + FXGL.getWorldProperties().intProperty("score"));
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
            if(player.getX() > 10) {
                player.translateX(-5);
            }
        });
        FXGL.onKey(KeyCode.D, () -> {
            if(player.getX() < FXGL.getAppWidth()-110) {
                player.translateX(5);
            }
        });

    }




    @Override
    protected void initPhysics(){
        PhysicsWorld physics = FXGL.getPhysicsWorld();


        //Alla collisione della palina con la racchetta lei rimbalza
        physics.addCollisionHandler(new CollisionHandler(OgettiGioco.racchetta, OgettiGioco.palina) {
            @Override
            protected void onCollisionBegin(Entity racheta, Entity palina) {
                direzionePalinaVe *= -1;
            }
        });


        //Alla collisione della palina con la parete la palina rimbalza
        physics.addCollisionHandler(new CollisionHandler(OgettiGioco.palina, OgettiGioco.wall) {
            @Override
            protected void onCollisionBegin(Entity wallL, Entity palina) {
                direzionePalinaOr *= -1;
            }
        });

    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setFont(Font.font("Arial", 18));
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(50); // y = 100

        textPixels.textProperty().bind(FXGL.getWorldProperties().
                intProperty("score").asString());

        FXGL.getGameScene().addUINode(textPixels); // add to the screen
    }



    public static void main(String[] args) {
        launch(args);
    }

}