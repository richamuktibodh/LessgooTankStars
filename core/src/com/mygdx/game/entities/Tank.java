package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.TankStars;

public class Tank {
//    public static final int defaultY = 350; // need to change this as y is constant

    public static final int TANK_Y_POSITION = 250;
    private Texture tankTexture ;
    private float x;
    private float y;
    public boolean remove = false;
    private TankStars game;
    private int val;
    private int health = 1;

    public Tank(float x, TankStars game,int val){
        this.x = x;
        this.y = TANK_Y_POSITION;
        this.game = game;
        this.val = val;
        if (tankTexture == null){
            if (val == 1){
                tankTexture = new Texture("elements/tank1.png");
            }
            else if (val == 2){
                tankTexture = new Texture("elements/tank2.png");
            }
        }
    }

    public  Texture getTankTexture() {
        return tankTexture;
    }

    public void setX(float x) {
        this.x = x;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setTankTexture(Texture texture) {
        this.tankTexture = texture;
    }
}
