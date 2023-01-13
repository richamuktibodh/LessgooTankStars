package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.TankStars;

public class GameOverScreen implements Screen {
    private final TankStars game;
    private OrthographicCamera camera;
    private Texture lmenu;
    Button restartButton, mainMenuButton;
    protected final Stage stage;
    protected Skin mySkin;
    private int row_height = Gdx.graphics.getWidth() / 12;
    private int col_width = Gdx.graphics.getWidth() / 12;

    public GameOverScreen(final TankStars game)
    {
        this.game = game;
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        lmenu = new Texture("backgrounds/lmenu.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        // making buttons
        restartButton = new TextButton("RESTART",mySkin,"small");
        restartButton.setSize(col_width*3,row_height);
        restartButton.setPosition(Gdx.graphics.getWidth()/2 - restartButton.getWidth()/2,Gdx.graphics.getHeight()/2 - restartButton.getHeight()/2 );
        restartButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new Player1ChooseTank(game,new NewGame()));
//                dispose();
            }
        });
        stage.addActor(restartButton);
        mainMenuButton = new TextButton("MAIN MENU",mySkin,"small");
        mainMenuButton.setSize(col_width*3,row_height);
        mainMenuButton.setPosition(Gdx.graphics.getWidth()/2 - mainMenuButton.getWidth()/2,Gdx.graphics.getHeight()/2 - mainMenuButton.getHeight()/2 -150);
        mainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(mainMenuButton);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(lmenu,Gdx.graphics.getWidth()/2 - lmenu.getWidth()/2,Gdx.graphics.getHeight()/2 - lmenu.getHeight()/2);
//        game.font.draw(game.batch, "GAME OVER", 600, 400);
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

    }
}
