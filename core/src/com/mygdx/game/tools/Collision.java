package com.mygdx.game.tools;

public class Collision {
    private float x,y;
    private int width,height;
    public Collision(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void  move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(Collision other){
        if (x < other.x + other.width && x + width > other.x && y < other.y + other.height && y + height > other.y){
            return true;
        }
        return false;
    }


}