package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.TankStars;
import com.mygdx.game.entities.Bullets;
import com.mygdx.game.entities.Explosion;
import com.mygdx.game.entities.Tank;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameScreen implements Screen {
    public static final int TANK_SPEED = 300;
    public static final float SHOOT_WAIT_TIME = 0.3f;
    private final TankStars game;
    private Rectangle tank1Img, tank2Img, tank;
    private Tank tank1Obj, tank2Obj, firingTank, tankToBeHit;
    private ArrayList<Bullets> bullets, bulletsToRemove;
    private ArrayList<Rectangle> bulletRects, bulletRectsToRemove;
    private OrthographicCamera camera;
    private Texture backgroundImage,explosionTexture;
    private TextureRegion backgroundTexture;
    private float shootTimer = 0,angle = 45, delay = 0;
    private float tank1X, tank2X, tank1Y, tank2Y;
    private ImageButton pauseButton;
    private Stage stage;
    private LinkedList<Explosion> explosionList;
    private Sound explosionSound,shootSound,tankMovingSound;


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
        tank1Img = new Rectangle(tank1Obj.getX(), tank1Obj.getY(), 150, 150);
        tank2Img = new Rectangle(tank2Obj.getX(), tank2Obj.getY(), 150, 150);
        // initially tank is to check collision which is tank2
        tank = tank2Img;
        bullets = new ArrayList<Bullets>();
        bulletsToRemove = new ArrayList<Bullets>();
        bulletRects = new ArrayList<Rectangle>();
        bulletRectsToRemove = new ArrayList<Rectangle>();
        explosionList = new LinkedList<Explosion>();
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);
        backgroundImage = new Texture(Gdx.files.internal("backgrounds/gameBG.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, TankStars.WIDTH, TankStars.HEIGHT);
//        game.font.getData().setScale(2, 2);
        game.font.setColor(254f/255f, 208f/255f, 0,1);
        // stuff for pause button
        // making an image button
        pauseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/pause.png")))));
        pauseButton.setPosition(TankStars.WIDTH - pauseButton.getWidth(), TankStars.HEIGHT - pauseButton.getHeight());
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PauseScreen(game,tank1Obj,tank2Obj));
                dispose();
            }
        });
        stage.addActor(pauseButton);

        explosionTexture = new Texture(Gdx.files.internal("elements/explosion.png"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gunshot.mp3"));
        tankMovingSound = Gdx.audio.newSound(Gdx.files.internal("sounds/tankmoving.mp3"));

    }

    private void updateAndRenderExplosion(float deltaTime) {
        for (Explosion explosion : explosionList) {
            explosion.update(deltaTime);
            if (explosion.isFinished()) {
                explosionList.remove(explosion);
            }
            else{
                explosion.draw(game.batch);
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // bullets
        shootTimer += delta;
        // setting angle
//        if (Gdx.input.isKeyPressed(Input.Keys.UP))
//        {
//            angle += 0.1;
//        }
//
//        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
//        {
//            angle -= 0.1;
//        }
//        System.out.println("Angle " + angle);

        // moving bullets
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && shootTimer >= SHOOT_WAIT_TIME) {
            shootTimer = 0;
            shootSound.play();
            if (tankToBeHit == tank2Obj) {
                bullets.add(new Bullets(firingTank.getX() + 100, 1));
                bulletRects.add(new Rectangle(firingTank.getX() + 100, firingTank.getY(), Bullets.BULLET_WIDTH, Bullets.BULLET_HEIGHT));
                bullets.get(bullets.size() - 1).setAngle(angle);
            }
            else {
                bullets.add(new Bullets(firingTank.getX() + 100, 2));
                bulletRects.add(new Rectangle(firingTank.getX() + 100, firingTank.getY(), Bullets.BULLET_WIDTH, Bullets.BULLET_HEIGHT));
                bullets.get(bullets.size()-1).setAngle(angle);
            }

        }

        // updating bullet and its rectangles co-ordinates
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(delta);
            bulletRects.get(i).setX(bullets.get(i).getX());
            bulletRects.get(i).setY(bullets.get(i).getY());
            // checking if bullet is out of screen


        }

        if (firingTank.getHealth() == 0 && delay >= Gdx.graphics.getDeltaTime()) {
            game.setScreen(new GameOverScreen(game));
        }

        // checking when to remove bullet from screen
        // assuming after a collision with the tank, the turn switches
        for (int i = 0; i < bullets.size(); i++) {
            if (bulletRects.get(i).overlaps(tank) || bullets.get(i).getX() < 0 || bullets.get(i).getX() > TankStars.WIDTH || bullets.get(i).getY() < 250 || bullets.get(i).getY() > TankStars.HEIGHT) {
                bulletsToRemove.add(bullets.get(i));
                bulletRectsToRemove.add(bulletRects.get(i));
                // if collision with tank
                if (bulletRects.get(i).overlaps(tank)){
                    // for drawing explosion
                    explosionList.add(new Explosion(explosionTexture, tank,1f));
                    explosionSound.play();
                    if (tankToBeHit.getHealth() > 0) {
                        tankToBeHit.setHealth(tankToBeHit.getHealth() - 1);
                    }
                }

                if (tankToBeHit.getHealth() == 0) {
                    tankToBeHit.setTankTexture(new Texture("elements/rip.png"));
                    delay += Gdx.graphics.getDeltaTime();
//                    game.setScreen(new GameOverScreen(game));
//                    dispose();

                }

                if (tankToBeHit == tank1Obj) {
                    tankToBeHit = tank2Obj;
                    firingTank = tank1Obj;
                    tank = tank2Img;
                    tank.x = tank2Obj.getX();
                }
                else {
                    tankToBeHit = tank1Obj;
                    firingTank = tank2Obj;
                    tank = tank1Img;
                    tank.x = tank1Obj.getX();
                }
            }
        }
        bulletRects.removeAll(bulletRectsToRemove);
        bullets.removeAll(bulletsToRemove);


        game.batch.begin();
        // drawing background
        game.batch.draw(backgroundTexture, 0, 0, TankStars.WIDTH, TankStars.HEIGHT);


        // drawing tanks
        game.batch.draw(tank1Obj.getTankTexture(), tank1Obj.getX(), tank1Obj.getY());
        game.batch.draw(tank2Obj.getTankTexture(), tank2Obj.getX(), tank2Obj.getY());

        // drawing explosion
        updateAndRenderExplosion(delta);

        //  drawing bullets
        for (Bullets bullet : bullets) {
            game.batch.draw(bullet.getBulletTexture(), bullet.getX(), bullet.getY());
        }

        // for movement of tanks
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            tankMovingSound.play();
            firingTank.setX(firingTank.getX() - TANK_SPEED * Gdx.graphics.getDeltaTime());
            if (firingTank.getX() < 0) // boundary so that tank doesn't go out of screen
            {
                firingTank.setX(0);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            firingTank.setX(firingTank.getX() + TANK_SPEED * Gdx.graphics.getDeltaTime());
            if (firingTank.getX() > TankStars.WIDTH - firingTank.getTankTexture().getWidth())
            {
                firingTank.setX(TankStars.WIDTH - firingTank.getTankTexture().getWidth());
            }
        }

        game.font.draw(game.batch, "Tank 1 Health: " + tank1Obj.getHealth(), 0, 50);
        game.font.draw(game.batch, "Tank 2 Health: " + tank2Obj.getHealth(), TankStars.WIDTH - 200, 50);
        game.batch.end();
        stage.act();
        stage.draw();

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
        backgroundImage.dispose();
        explosionSound.dispose();
        shootSound.dispose();
        tank1Obj.getTankTexture().dispose();
        tank2Obj.getTankTexture().dispose();
        stage.dispose();
    }
}

