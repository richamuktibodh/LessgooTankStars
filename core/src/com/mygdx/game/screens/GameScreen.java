package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.TankStars;
import com.mygdx.game.entities.Bullets;
import com.mygdx.game.entities.Tank;

import java.util.ArrayList;

public class GameScreen implements Screen {
    public static final float SPEED = 300;
    public static final float SHOOT_WAIT_TIME = 0.3f;
    private Texture tank1;
    private float tank1x, tank1y, tank2x;
    private final TankStars game;
    private ArrayList<Bullets> bullets1;
    private ArrayList<Bullets> bulletsToRemove1;
    private float shootTimer = 0;
    private Tank tankobj1, tankobj2;
    public GameScreen(TankStars game) {
        this.game = game;
        tank1x = 0;
        tank1y = 0;
        tank2x = Gdx.graphics.getWidth() - 500;
        bullets1 = new ArrayList<Bullets>();
        bulletsToRemove1 = new ArrayList<Bullets>();
        tankobj1 = new Tank(tank1x, game, 1);
        tankobj2 = new Tank(tank2x, game,2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        shootTimer += delta;
        // moving bullets
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && shootTimer >= SHOOT_WAIT_TIME) {
            shootTimer = 0;
            bullets1.add(new Bullets(tank1x + 300,1));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.B) && shootTimer >= SHOOT_WAIT_TIME) {
            shootTimer = 0;
            bullets1.add(new Bullets(tank2x + 300, 2));
        }

        // update bullets
        for (Bullets bullet : bullets1) {
            bullet.update(delta);
            if (bullet.remove) {
                bulletsToRemove1.add(bullet);
            }
        }
        bullets1.removeAll(bulletsToRemove1);


        // after all the updates, check for collisions
        // for tank1
        for (Bullets bullet : bullets1) {
            if (bullet.getCollisionRect().collidesWith(tankobj1.getCollisionRect())) {
                bulletsToRemove1.add(bullet);
                if (tankobj1.getHealth() > 0) {
                    tankobj1.setHealth(tankobj1.getHealth() - 1);
                }
                System.out.println("health of tank1 " + tankobj1.getHealth());
            }
        }
        bullets1.removeAll(bulletsToRemove1);


        // for tank2
        for (Bullets bullet : bullets1) {
            if (bullet.getCollisionRect().collidesWith(tankobj2.getCollisionRect())) {
                bulletsToRemove1.add(bullet);
                if (tankobj2.getHealth() > 0) {
                    tankobj2.setHealth(tankobj2.getHealth() - 1);
                }
                System.out.println("health of tank2 " + tankobj2.getHealth());
            }
        }
        bullets1.removeAll(bulletsToRemove1);


        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        for (Bullets bullet : bullets1) {
            bullet.render(game.batch);
        }

        tankobj1.update(delta);
        tankobj2.update(delta);
        tankobj1.render();
        // to render tank2
        tankobj2.render();


        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

