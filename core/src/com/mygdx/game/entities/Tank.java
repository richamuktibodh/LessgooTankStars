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
    private int health = 10;

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
//        tankTexture = new Texture("tank2.png");
        this.rect = new Collision(x,y,tankTexture.getWidth()-400,tankTexture.getHeight()-150);
    }

public void update(float delta){
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//            x -= SPEED * delta;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//            x += SPEED * delta;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
//            y += SPEED * delta;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//            y -= SPEED * delta;
//        }
//        if (x < 0){
//            x = 0;
//        }
//        if (x > TankStars.WIDTH - tankTexture.getWidth()){
//            x = TankStars.WIDTH - tankTexture.getWidth();
//        }
//        if (y < 0){
//            y = 0;
//        }
//        if (y > TankStars.HEIGHT - tankTexture.getHeight()){
//            y = TankStars.HEIGHT - tankTexture.getHeight();
//        }
        rect.move(x,y);
    }

    public void render(){
        game.batch.draw(tankTexture,x,0);
        if (val == 1)
        {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            {
                x -= SPEED * Gdx.graphics.getDeltaTime();
                if (x < 0) // boundary so that tank doesn't go out of screen
                {
                    x = 0;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                x += SPEED * Gdx.graphics.getDeltaTime();
                if (x > TankStars.WIDTH - tankTexture.getWidth())
                {
                    x = TankStars.WIDTH - tankTexture.getWidth();
                }
            }
        }

        else if (val == 2)
        {
            if (Gdx.input.isKeyPressed(Input.Keys.UP))
            {
                x -= SPEED * Gdx.graphics.getDeltaTime();
                if (x < 0) // boundary so that tank doesn't go out of screen
                {
                    x = 0;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            {
                x += SPEED * Gdx.graphics.getDeltaTime();
                if (x > TankStars.WIDTH - this.getTankTexture().getWidth())
                {
                    x = TankStars.WIDTH - this.getTankTexture().getWidth();
                }
            }

        }

    }

    public  Texture getTankTexture() {
        return tankTexture;
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
