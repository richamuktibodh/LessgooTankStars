package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.Collision;


public class Bullets {
    public static final int BULLET_SPEED = 400;
    public static final int defaultY = 300; // need to change this as y is constant
    private Texture bulletTexture ;
    private float x,y;
    private int val;
    public boolean remove = false;
    private Collision rect;
    public Bullets(float x, int val){
        this.x = x;
        this.y = defaultY;
        this.val = val;
        if (bulletTexture == null){
            if (val == 1){
                bulletTexture = new Texture("bullet.png");
            }
            else if (val == 2){
                bulletTexture = new Texture("ultaBullet.png");
            }

        }
        this.rect = new Collision(x,y,bulletTexture.getWidth(),bulletTexture.getHeight());
    }

    public void update(float delta){
        if (val == 1){
            x += BULLET_SPEED * delta;
            if (x > Gdx.graphics.getWidth()){ // changed this
                remove = true;
            }

        }
        else if (val == 2){
            x -= BULLET_SPEED * delta;
            if (x <0){
                remove = true;
            }
        }
        rect.move(x,y);
    }

    public void render(SpriteBatch batch){

        batch.draw(bulletTexture,x,y);
    }

    public Collision getCollisionRect(){

        return rect;
    }
}

