package com.esercizi.javafx.pongjava;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Factory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data){
        return FXGL.entityBuilder()
                .at(FXGL.getAppWidth() /2 -50, FXGL.getAppHeight()-20)
                .type(OgettiGioco.player)
                .viewWithBBox(new Rectangle(100, 10, Color.BLUE))
                .collidable()
                .build();
    }

    @Spawns("palina")
    public Entity newPalina(SpawnData data){
        return FXGL.entityBuilder()
                .type(OgettiGioco.palina)
                .at(FXGL.getAppWidth()/2,FXGL.getAppHeight()/2)
                .collidable()
                .viewWithBBox(new Circle(10, Color.RED))
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data){
        return FXGL.entityBuilder()
                .at(FXGL.getAppWidth() /2 - 50, 10)
                .type(OgettiGioco.enemy)
                .viewWithBBox(new Rectangle(100, 10, Color.BLUE))
                .collidable()
                .build();
    }

}
