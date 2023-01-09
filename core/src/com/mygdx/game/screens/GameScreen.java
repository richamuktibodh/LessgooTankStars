package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.TankStars;
import com.mygdx.game.entities.Bullets;
import com.mygdx.game.entities.Tank;

import java.util.ArrayList;

public class GameScreen implements Screen {
    public static final int TANK_SPEED = 300;
    public static final float SHOOT_WAIT_TIME = 0.3f;
    private final TankStars game;
    private Rectangle tank1Img, tank2Img, tank;
    private Tank tank1Obj, tank2Obj, firingTank, tankToBeHit;
    private ArrayList<Bullets> bullets, bulletsToRemove;
    private ArrayList<Rectangle> bulletRects, bulletRectsToRemove;
    private OrthographicCamera camera;
    private float shootTimer = 0;
    private float tank1X, tank2X, tank1Y, tank2Y;



    public GameScreen(final TankStars game) {
        this.game = game;
        tank1X = 0;
        tank1Y = 0;
        tank2X = Gdx.graphics.getWidth() - 500;
        tank2Y = 0;
        tank1Obj = new Tank(tank1X, game, 1);
        tank2Obj = new Tank(tank2X, game, 2);
        tankToBeHit = tank2Obj;
        firingTank = tank1Obj;
        // create rectangles to represent tanks
        tank1Img = new Rectangle(tank1X, tank1Y, tank1Obj.getTankTexture().getWidth(), tank1Obj.getTankTexture().getHeight());
        tank2Img = new Rectangle(tank2X, tank2Y, tank2Obj.getTankTexture().getWidth(), tank2Obj.getTankTexture().getHeight());
        // initially tank is to check collision which is tank2
        tank = tank2Img;
        bullets = new ArrayList<Bullets>();
        bulletsToRemove = new ArrayList<Bullets>();
        bulletRects = new ArrayList<Rectangle>();
        bulletRectsToRemove = new ArrayList<Rectangle>();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        // tanks
        game.batch.draw(tank1Obj.getTankTexture(), tank1Obj.getX(), tank1Obj.getY());
        game.batch.draw(tank2Obj.getTankTexture(), tank2Obj.getX(), tank2Obj.getY());

        // bullets
        shootTimer += delta;
        // moving bullets
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && shootTimer >= SHOOT_WAIT_TIME) {
            shootTimer = 0;
            if (tankToBeHit == tank2Obj) {
                bullets.add(new Bullets(firingTank.getX() + 200, 1));
                bulletRects.add(new Rectangle(firingTank.getX() + 200, firingTank.getY(), Bullets.BULLET_WIDTH, Bullets.BULLET_HEIGHT));
            }
            else {
                bullets.add(new Bullets(firingTank.getX() + 200, 2));
                bulletRects.add(new Rectangle(firingTank.getX() + 200, firingTank.getY(), Bullets.BULLET_WIDTH, Bullets.BULLET_HEIGHT));
            }

        }

        // updating bullet and its rectangles co-ordinates
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(delta);
            bulletRects.get(i).setX(bullets.get(i).getX());
            bulletRects.get(i).setY(bullets.get(i).getY());
        }

        // checking collision between bullets and tanks --> have to add collision with ground and bullet gone out of screen
        // after any collision, the turn switches
        for (int i = 0; i < bullets.size(); i++) {
            if (bulletRects.get(i).overlaps(tank)) {
                bulletsToRemove.add(bullets.get(i));
                bulletRectsToRemove.add(bulletRects.get(i));
                if (tankToBeHit.getHealth() > 0) {
                    tankToBeHit.setHealth(tankToBeHit.getHealth() - 1);
                }
//                if (tankToBeHit.getHealth() == 0) {
//                    tankToBeHit.setTankTexture(new Texture("tank_destroyed.png"));
                //}
//                if (tankToBeHit.getHealth() == 0) {
//                    game.setScreen(new GameOverScreen(game));
//                    dispose();
//                }
                System.out.println("tank health: " + tankToBeHit.getHealth());
            }
            // condition for next persons turn
//            if (tankToBeHit == tank1Obj) {
//                tankToBeHit = tank2Obj;
//                // update tankToBeHits position --> in object and rectangle
//                firingTank = tank1Obj;
//                tank = tank2Img;
//            }
//            else {
//                tankToBeHit = tank1Obj;
//                // update tankToBeHits position --> in object and rectangle
//                firingTank = tank2Obj;
//                tank = tank1Img;
//            }
        }
        bulletRects.removeAll(bulletRectsToRemove);
        bullets.removeAll(bulletsToRemove);

        //  drawing bullets
        for (Bullets bullet : bullets) {
            game.batch.draw(bullet.getBulletTexture(), bullet.getX(), bullet.getY());
        }
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

