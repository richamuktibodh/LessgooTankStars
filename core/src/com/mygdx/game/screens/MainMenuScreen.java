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

public class MainMenuScreen implements Screen {
    private final TankStars game;
    private Texture backgroundImg, logoImg;
    private TextureRegion backgroundTexture;
    private OrthographicCamera camera;
    protected final Stage stage;
    protected Skin mySkin;
    Button resumeButton, newGameButton, exitButton;
    private int row_height = Gdx.graphics.getWidth() / 12;
    private int col_width = Gdx.graphics.getWidth() / 12;


    public MainMenuScreen(final TankStars game) {
        this.game = game;
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        backgroundImg = new Texture("backgrounds/MenuBG.png");
        backgroundTexture = new TextureRegion(backgroundImg, 0, 0, TankStars.WIDTH, TankStars.HEIGHT);
        logoImg = new Texture("backgrounds/logo.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        // making buttons
        resumeButton = new TextButton("RESUME",mySkin,"small");
        resumeButton.setSize(col_width*4,row_height);
        resumeButton.setPosition(Gdx.graphics.getWidth()/2 - resumeButton.getWidth()/2,Gdx.graphics.getHeight()/2 - resumeButton.getHeight()/2);
        resumeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new SavedGames(game));
//                dispose();
            }
        });
        stage.addActor(resumeButton);

        newGameButton  = new TextButton("NEW GAME",mySkin,"small");
        newGameButton.setSize(col_width*4,row_height);
        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - newGameButton.getWidth()/2,Gdx.graphics.getHeight()/2 - newGameButton.getHeight()/2 + 150);


        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                NewGame newGame = new NewGame();
                game.setScreen(new Player1ChooseTank(game));
                dispose();
            }
        });

        stage.addActor(newGameButton);

        exitButton = new TextButton("EXIT",mySkin,"small");
        exitButton.setSize(col_width*4,row_height);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2,Gdx.graphics.getHeight()/2 - exitButton.getHeight()/2 - 150);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                dispose();
            }
        });
        stage.addActor(exitButton);

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
//        game.font.draw(game.batch, "Welcome to TankStars!!! ", 100, 150);
//        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.draw(logoImg, Gdx.graphics.getWidth()/2-logoImg.getWidth()/2, 600);
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
        backgroundImg.dispose();
        logoImg.dispose();
    }
}
