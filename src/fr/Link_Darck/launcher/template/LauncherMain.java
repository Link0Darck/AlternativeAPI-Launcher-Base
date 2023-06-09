package fr.Link_Darck.launcher.template;

import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.GameFolder;
import fr.trxyy.alternative.alternative_api.GameLinks;
import fr.trxyy.alternative.alternative_api.GameStyle;
import fr.trxyy.alternative.alternative_api.LauncherPreferences;
import fr.trxyy.alternative.alternative_api.utils.Logger;
import fr.trxyy.alternative.alternative_api.utils.Mover;
import fr.trxyy.alternative.alternative_api.utils.WindowStyle;
import fr.trxyy.alternative.alternative_api_ui.LauncherBackground;
import fr.trxyy.alternative.alternative_api_ui.LauncherPane;
import fr.trxyy.alternative.alternative_api_ui.base.AlternativeBase;
import fr.trxyy.alternative.alternative_api_ui.base.LauncherBase;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LauncherMain extends AlternativeBase {
	private GameFolder GAME_FOLDER = new GameFolder("AlternativeAPI-Launcher-Base");
	private GameLinks GAME_LINKS = new GameLinks("http://localhost/alternative/1_17_1/", "1.17.1.json");
//	private GameForge GAME_FORGE = new GameForge(Forge.DEFAULT, "1.16.3",  "34.1.0", "20200911.084530");
	private LauncherPreferences LAUNCHER_PREFERENCES = new LauncherPreferences("AlternativeAPI Launcher Base", 950, 600, Mover.MOVE);
	private GameEngine GAME_ENGINE = new GameEngine(this.GAME_FOLDER, this.GAME_LINKS, this.LAUNCHER_PREFERENCES, GameStyle.VANILLA/*, this.GAME_FORGE*/);

	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(this.createContent());
		LauncherBase launcher = new LauncherBase(primaryStage, scene, StageStyle.TRANSPARENT, this.GAME_ENGINE);
		launcher.setIconImage(primaryStage, "favicon.png");
	}

	private Parent createContent() {
		LauncherPane contentPane = new LauncherPane(this.GAME_ENGINE, 5, WindowStyle.TRANSPARENT);
		new LauncherBackground(this.GAME_ENGINE, "background.mp4", contentPane);
		new LauncherPanel(contentPane, this.GAME_ENGINE);
		return contentPane;
	}
	
	public static void main(String[] args) {
		Logger.log("AlternativeAPI Launcher Base created by Link0Darck running on TrxyyDev API");
		Application.launch(args);
	}
}