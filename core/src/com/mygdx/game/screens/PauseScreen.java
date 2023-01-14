package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.TankStars;

public class PauseScreen implements Screen {
    private final TankStars game;
    protected final Stage stage;
    protected Skin mySkin;
    private int row_height = Gdx.graphics.getWidth() / 12;
    private int col_width = Gdx.graphics.getWidth() / 12;
    private Button saveButton, resumeButton, mainMenuButton;
    private Texture pmenu;
    private OrthographicCamera camera;

    public PauseScreen(final TankStars game) {
        this.game = game;
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        pmenu = new Texture("backgrounds/pmenu.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);
        // create stage and set it as input processor
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        // making buttons
        saveButton = new TextButton("SAVE",mySkin,"small");
        saveButton.setSize(col_width*2,row_height);
        saveButton.setPosition(Gdx.graphics.getWidth()/2 - saveButton.getWidth()/2 ,Gdx.graphics.getHeight()/2 - saveButton.getHeight()/2 + 170);
        stage.addActor(saveButton);
        resumeButton = new TextButton("RESUME",mySkin,"small");
        resumeButton.setSize(col_width*2,row_height);
        resumeButton.setPosition(Gdx.graphics.getWidth()/2 - resumeButton.getWidth()/2 ,Gdx.graphics.getHeight()/2 - resumeButton.getHeight()/2 +45);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        stage.addActor(resumeButton);
        mainMenuButton = new TextButton("MAIN MENU",mySkin,"small");
        mainMenuButton.setSize(col_width*2,row_height);
        mainMenuButton.setPosition(Gdx.graphics.getWidth()/2 - mainMenuButton.getWidth()/2 ,Gdx.graphics.getHeight()/2 - mainMenuButton.getHeight()/2 -80);
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
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
        game.batch.draw(pmenu,Gdx.graphics.getWidth()/2 - pmenu.getWidth()/2,250);
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
        mySkin.dispose();
        pmenu.dispose();
    }
}
