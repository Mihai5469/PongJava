package com.esercizi.javafx.pongjava;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Factory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(OgettiGioco.racchetta)
                .viewWithBBox(new Rectangle(100, 10, Color.BLUE))
                //.with(new PlayerComponent())
                .collidable()
                .build();
    }


    @Spawns("palina")
    public Entity newPalina(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(OgettiGioco.palina)
                .collidable()
                .viewWithBBox(new Circle(10, 10, 10, Color.RED))
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(OgettiGioco.racchetta)
                .viewWithBBox(new Rectangle(100, 10, Color.BLUE))
                .collidable()
                .build();
    }

    @Spawns("wall")
    public Entity newWallL(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(OgettiGioco.wall)
                .viewWithBBox( new Rectangle(10, FXGL.getAppHeight()+10))
                .collidable()
                .build();
    }

}
