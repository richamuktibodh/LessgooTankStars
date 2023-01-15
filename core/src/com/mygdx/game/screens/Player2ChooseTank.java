package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.TankStars;

public class Player2ChooseTank implements Screen {
    private final TankStars game;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    private Texture logo,h1,tank1,tank2,tank3;
    protected final Stage stage;
    protected Skin mySkin;
    private OrthographicCamera camera;
    Button tank1Button, tank2Button, tank3Button, playButton;
    private int row_height = Gdx.graphics.getWidth() / 12;
    private int col_width = Gdx.graphics.getWidth() / 12;

    public Player2ChooseTank(final TankStars game){
        this.game = game;
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        backgroundImage = new Texture("backgrounds/BlueBG.png");
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, TankStars.WIDTH, TankStars.HEIGHT);
        logo = new Texture("backgrounds/logo.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        // making buttons
        tank1Button = new TextButton("TANK 1",mySkin,"small");
        tank1Button.setSize(col_width*4,row_height);
        tank1Button.setPosition(Gdx.graphics.getWidth()/2 - tank1Button.getWidth()/2 - 500,Gdx.graphics.getHeight()/2 - tank1Button.getHeight()/2 + 40);
        tank1Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                newGame.setPlayer1Tank(1);
            }
        });
        stage.addActor(tank1Button);
        tank2Button  = new TextButton("TANK 2",mySkin,"small");
        tank2Button.setSize(col_width*4,row_height);
        tank2Button.setPosition(Gdx.graphics.getWidth()/2 - tank2Button.getWidth()/2,Gdx.graphics.getHeight()/2 - tank2Button.getHeight()/2 + 40 );
        tank2Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                newGame.setPlayer1Tank(2);
            }
        });
        stage.addActor(tank2Button);
        tank3Button = new TextButton("TANK 3",mySkin,"small");
        tank3Button.setSize(col_width*4,row_height);
        tank3Button.setPosition(Gdx.graphics.getWidth()/2 - tank3Button.getWidth()/2 + 500,Gdx.graphics.getHeight()/2 - tank3Button.getHeight()/2 + 40);
        tank3Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                newGame.setPlayer1Tank(3);
            }
        });
        stage.addActor(tank3Button);
        tank1 = new Texture("elements/tank15.png");
        tank2 = new Texture("elements/tank3.png");
        tank3 = new Texture("elements/tank25.png");
        playButton = new TextButton("PLAY!! ",mySkin,"small");
        playButton.setSize(col_width*4,row_height);
        playButton.setPosition(Gdx.graphics.getWidth()/2 - playButton.getWidth()/2+80,Gdx.graphics.getHeight()/2 - playButton.getHeight()/2 - 330);
        playButton.setTransform(true);
        playButton.scaleBy(-0.3f);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        stage.addActor(playButton);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, TankStars.WIDTH, TankStars.HEIGHT);
        game.batch.draw(logo, Gdx.graphics.getWidth()/2 - logo.getWidth()/2,650);
//        game.batch.draw(h1, Gdx.graphics.getWidth()/2 - h1.getWidth() / 4,300);
        game.batch.draw(tank1,50,-50, 450, 550);
        game.batch.draw(tank2,Gdx.graphics.getWidth()/2 - tank2.getWidth() /2 + 80,40, 650, 350);
        game.batch.draw(tank3,950,-50,450,550);
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
        stage.dispose();
        tank1.dispose();
        tank2.dispose();
        tank3.dispose();
        backgroundImage.dispose();
        logo.dispose();
    }


}
