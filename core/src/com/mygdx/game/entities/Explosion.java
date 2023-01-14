package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Explosion {
    private Animation<TextureRegion> explosionAnimation;
    private float explosionTimer;
    private Rectangle boundingBox;

    public Explosion(Texture texture,Rectangle boundingBox,float animationTime){
        this.boundingBox = boundingBox;
        // split texture
        TextureRegion[][] textureRegion = TextureRegion.split(texture,64,64);

        // convert to 1d array
        TextureRegion[] frames = new TextureRegion[9];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                frames[index] = textureRegion[i][j];
//                System.out.println("index: " + index);
                index++;
            }
        }
        explosionAnimation = new Animation<TextureRegion>(animationTime/16,frames);
        explosionTimer = 0;
    }

    public void update(float delta){
        explosionTimer += delta;
    }

    public void draw(SpriteBatch batch){
        // draw animation
//        batch.draw(explosionAnimation.getKeyFrame(explosionTimer),boundingBox.x,boundingBox.y);
        batch.draw(explosionAnimation.getKeyFrame(explosionTimer),boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
    }

    public boolean isFinished(){
        return explosionAnimation.isAnimationFinished(explosionTimer);
    }

}
