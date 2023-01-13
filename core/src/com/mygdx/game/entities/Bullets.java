package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static java.lang.Math.cos;
import static java.lang.Math.tan;


public class Bullets {
    public int BULLET_SPEED = 17; // as each bullet object will have its own speed
    public static final int BULLET_WIDTH = 20;
    public static final int BULLET_HEIGHT = 20;
    public static final int defaultY = 350; // need to change this as y is constant
    private Texture bulletTexture;
    private float xProjectile;
    private float yProjectile;
    private float x;
    private float y;

    private float angle = 45,angleInRad;
    private final int val;
    public boolean remove = false;

    public Bullets(float x, int val){
        this.xProjectile = 0;
        this.yProjectile = 0;
        //System.out.println("xProjectile initial: " + xProjectile + " yProjectile initial: " + yProjectile);
        // x and y are supposed to be initial tank coordinates from where bullet is shot, y is constant as ground is straight
        this.x = x ;
        this.y = defaultY ;
        this.val = val;
        angleInRad = (float) Math.toRadians(angle);

        if (bulletTexture == null){
            if (val == 1){
                bulletTexture = new Texture("elements/bullet.png");
            }
            else if (val == 2){
                bulletTexture = new Texture("elements/ultaBullet.png");
            }
        }
    }

    public void update(float delta){
        if (this.val == 1){
//            System.out.println("xProjectile update before: " + xProjectile + " yProjectile update before: " + yProjectile);
            yProjectile = (float) ((tan(angleInRad))*xProjectile - (10.0f * (xProjectile*xProjectile))/(BULLET_SPEED * BULLET_SPEED*cos(angleInRad)*cos(angleInRad)));
            xProjectile += BULLET_SPEED * delta;
//            yProjectile  = (xProjectile - (10.0f * (xProjectile*xProjectile))/(BULLET_SPEED * BULLET_SPEED));
            x  += xProjectile;
            y  += yProjectile;
            if (x > Gdx.graphics.getWidth()){ // changed this
                remove = true;
            }
        }
        else if (this.val == 2){
            xProjectile += BULLET_SPEED * delta;
            yProjectile = (float) ((tan(angleInRad))*xProjectile - (10.0f * (xProjectile*xProjectile))/(BULLET_SPEED * BULLET_SPEED*cos(angleInRad)*cos(angleInRad)));
            x = x - xProjectile;
            y += yProjectile;
            if (x <0){
                remove = true;
            }
        }
    }

    public  Texture getBulletTexture() {
        return bulletTexture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public int getBulletSpeed() {
        return BULLET_SPEED;
    }

    public void setBulletSpeed(int bulletSpeed) {
        BULLET_SPEED = bulletSpeed;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}

