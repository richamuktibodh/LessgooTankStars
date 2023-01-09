package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;


public class Bullets {
    public static final int BULLET_SPEED = 10;
    public static final int BULLET_WIDTH = 20;
    public static final int BULLET_HEIGHT = 20;
//    public static final int defaultY = 300; // need to change this as y is constant
    private Texture bulletTexture ;
    private float xStartingPoint;
    private float yStartingPoint;
    private float xProjectile;
    private float yProjectile;
    private float x;

    private float y;
    private int val;
    public boolean remove = false;
    private Vector3 position = new Vector3(), direction = new Vector3();

    public Bullets(float x, int val){
//        this.xStartingPoint = 200;
//        this.yStartingPoint = 300;
        this.xProjectile = 0;
        this.yProjectile = 0;
        //System.out.println("xProjectile initial: " + xProjectile + " yProjectile initial: " + yProjectile);
        // x and y are supposed to be initial tank coordinates from where bullet is shot
        this.x = x ;
        this.y = 300 ;
//        this.y = defaultY;
        this.val = val;
//        this.position.set(position);
//        this.direction.set(direction);

        if (bulletTexture == null){
            if (val == 1){
                bulletTexture = new Texture("bullet.png");
            }
            else if (val == 2){
                bulletTexture = new Texture("ultaBullet.png");
            }
        }
    }

    public void update(float delta){
        if (this.val == 1){
//            System.out.println("xProjectile update before: " + xProjectile + " yProjectile update before: " + yProjectile);
            xProjectile += BULLET_SPEED * delta;
            //yProjectile    = (xProjectile - (10.0f * (xProjectile*xProjectile))/(2.0f * (BULLET_SPEED * BULLET_SPEED))) * delta;
            yProjectile  = (xProjectile - (10.0f * (xProjectile*xProjectile))/(BULLET_SPEED * BULLET_SPEED));
            x  += xProjectile;
            y  += yProjectile;
//            System.out.println("xProjectile after update: " + xProjectile + " yProjectile after update: " + yProjectile);
            if (x > Gdx.graphics.getWidth()){ // changed this
                remove = true;
            }
        }
        else if (this.val == 2){
            xProjectile += BULLET_SPEED * delta;
            yProjectile = (xProjectile- ((10.0f/(BULLET_SPEED*BULLET_SPEED)) * (xProjectile*xProjectile)));
            //x += xProjectile;
            x = x - xProjectile;
            y += yProjectile;
            if (x <0){
                remove = true;
            }
        }
    }

//    public void render(SpriteBatch batch){
//        System.out.println("x: " + xProjectile + " y: " + yProjectile);
//        batch.draw(bulletTexture,x,y);
//    }

    public  Texture getBulletTexture() {
        return bulletTexture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

