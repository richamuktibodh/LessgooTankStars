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
    private ArrayList<Bullets> bullets;
    private ArrayList<Bullets> bulletsToRemove;
    private float shootTimer = 0;
    private Tank tankobj1, tankobj2, tankToBeHit, firingTank;
    public GameScreen(TankStars game) {
        this.game = game;
        tank1x = 0;
        tank1y = 0;
        tank2x = Gdx.graphics.getWidth() - 500;
        bullets = new ArrayList<Bullets>();
        bulletsToRemove = new ArrayList<Bullets>();
        tankobj1 = new Tank(tank1x, game, 1);
        tankobj2 = new Tank(tank2x, game,2);
        tankToBeHit = tankobj2;
        firingTank = tankobj1;
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
            if (tankToBeHit == tankobj2) {
                bullets.add(new Bullets(firingTank.getX() + 200, 1));
            } else {
                bullets.add(new Bullets(firingTank.getX() + 200, 2));
            }

        }


        // update bullets
        for (Bullets bullet : bullets) {
            bullet.update(delta);
            if (bullet.remove) {
                bulletsToRemove.add(bullet);
            }
        }
        bullets.removeAll(bulletsToRemove);


        // after all the updates, check for collisions
        for (Bullets bullet : bullets) {
            if (bullet.getCollisionRect().collidesWith(tankToBeHit.getCollisionRect())) {
                bulletsToRemove.add(bullet);
                if (tankToBeHit.getHealth() > 0) {
                    tankToBeHit.setHealth(tankToBeHit.getHealth() - 1);
                }
                System.out.print("health of tank");
                System.out.println(tankToBeHit.getHealth());
                //////
                if (tankToBeHit == tankobj1) { // need to update only firing tanks position; if tankToBeHit is tank2 now,  then tank2 was firing before
                    tankToBeHit = tankobj2;
                    tankToBeHit.update(delta);
                    firingTank = tankobj1;
                } else {
                    tankToBeHit = tankobj1;
                    tankToBeHit.update(delta);
                    firingTank = tankobj2;
                }
            }
        }
        bullets.removeAll(bulletsToRemove);


        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        // render bullets
        for (Bullets bullet : bullets) {
            bullet.render(game.batch);
        }

        tankobj1.render();
        tankobj2.render();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            firingTank.setX(firingTank.getX() - SPEED * Gdx.graphics.getDeltaTime());
            if (firingTank.getX() < 0) // boundary so that tank doesn't go out of screen
            {
                firingTank.setX(0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            firingTank.setX(firingTank.getX() + SPEED * Gdx.graphics.getDeltaTime());
            if (firingTank.getX() > TankStars.WIDTH - firingTank.getTankTexture().getWidth())
            {
                firingTank.setX(TankStars.WIDTH - firingTank.getTankTexture().getWidth());
            }
        }

        game.batch.end();

    }

    @Override
    public String toString() {
        if (tankToBeHit == tankobj2) {
            return "tank2";
        } else {
            return "tank1";
        }
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

