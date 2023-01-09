package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.tools.Collision;
import com.mygdx.game.TankStars;

import static com.mygdx.game.screens.GameScreen.SPEED;

public class Tank {
//    public static final int defaultY = 350; // need to change this as y is constant

    private Texture tankTexture ;


    private float x;
    private float y;
    public boolean remove = false;
    private TankStars game;
    private Collision rect;
    private int val;
    private int health = 3;

    public Tank(float x, TankStars game,int val){
        this.x = x;
//        this.y = defaultY;
        this.game = game;
        this.val = val;
        if (tankTexture == null){
            if (val == 1){
                tankTexture = new Texture("tank1.png");
            }
            else if (val == 2){
                tankTexture = new Texture("tank2.png");
            }
        }
        this.rect = new Collision(x,y,tankTexture.getWidth()-400,tankTexture.getHeight()-150);
    }

public void update(float delta){

        rect.move(x,y);
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

    public Collision getCollisionRect(){

        return rect;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
