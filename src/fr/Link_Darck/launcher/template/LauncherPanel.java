package fr.Link_Darck.launcher.template;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.GameMemory;
import fr.trxyy.alternative.alternative_api.GameSize;
import fr.trxyy.alternative.alternative_api.JVMArguments;
import fr.trxyy.alternative.alternative_api.updater.GameUpdater;
import fr.trxyy.alternative.alternative_api.utils.FontLoader;
import fr.trxyy.alternative.alternative_api.utils.Mover;
import fr.trxyy.alternative.alternative_api.utils.config.EnumConfig;
import fr.trxyy.alternative.alternative_api.utils.config.LauncherConfig;
import fr.trxyy.alternative.alternative_api_ui.LauncherPane;
import fr.trxyy.alternative.alternative_api_ui.base.IScreen;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherButton;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherImage;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherLabel;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherPasswordField;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherProgressBar;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherRectangle;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherTextField;
import fr.trxyy.alternative.alternative_auth.account.AccountType;
import fr.trxyy.alternative.alternative_auth.base.GameAuth;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LauncherPanel extends IScreen {
  private LauncherRectangle topRectangle;
  private LauncherRectangle backgroundWhiteRectangle;
  private LauncherImage titleImage;
  private LauncherLabel titleLabel;
  private LauncherButton closeButton;
  private LauncherButton reduceButton;
  private LauncherButton websiteButton;
  private LauncherButton facebookButton;
  private LauncherButton twitterButton;
  private LauncherButton discordButton;
  private LauncherButton microsoftButton;
  private LauncherButton mojangButton;
  private LauncherButton crackButton;
  private LauncherButton AccueilButton;
  private GameAuth gameAuthentication;
  private LauncherTextField usernameField;
  private LauncherPasswordField passwordField;
  private LauncherButton loginButton;
  private LauncherButton settingsButton;
  public Timeline timeline;
  private DecimalFormat decimalFormat = new DecimalFormat(".#");
  private Thread updateThread;
  private GameUpdater updater = new GameUpdater();
  private LauncherRectangle updateRectangle;
  private LauncherLabel updateLabel;
  private LauncherLabel currentFileLabel;
  private LauncherLabel percentageLabel;
  private LauncherLabel currentStep;
  public LauncherProgressBar bar;
  private String WEBSITE_URL = "https://neko-world.ovh/";
  private String FACEBOOK_URL = "https://www.facebook.com/Neko.World.fr.nf/";
  private String TWITTER_URL = "https://twitter.com/NekoWorld4/";
  private String DISCORD_URL = "https://discord.com/invite/XqAZw3Y";
  private GameEngine theGameEngine;
  private Timer autoLoginTimer;
  private LauncherLabel autoLoginLabel;
  private LauncherRectangle autoLoginRectangle;
  private LauncherButton autoLoginButton;
  public LauncherConfig config;
  
  public LauncherPanel(Pane root, GameEngine engine) {
    this.theGameEngine = engine;
    this.config = new LauncherConfig(engine);
    this.config.loadConfiguration();
    this.backgroundWhiteRectangle = new LauncherRectangle(root, 0, 0, this.theGameEngine.getWidth(), this.theGameEngine.getHeight());
    this.backgroundWhiteRectangle.setFill(Color.rgb(255, 255, 255, 0.17D));
    this.topRectangle = new LauncherRectangle(root, 0, 0, this.theGameEngine.getWidth(), 31);
    this.topRectangle.setFill(Color.rgb(0, 0, 0, 0.7D));
    this.topRectangle.setOpacity(1.0D);
    drawImage(this.theGameEngine, getResourceLocation().loadImage(this.theGameEngine, "logo.png"), this.theGameEngine.getWidth() / 2 - 280, 70, 600, 120, root, Mover.MOVE);
    this.titleImage = new LauncherImage(root);
    this.titleImage.setImage(getResourceLocation().loadImage(this.theGameEngine, "favicon.png"));
    this.titleImage.setBounds(this.theGameEngine.getWidth() / 3 + 40, 3, 25, 25);
    this.titleLabel = new LauncherLabel(root);
    this.titleLabel.setText("AlternativeAPI-Launcher-Base");
    this.titleLabel.setFont(FontLoader.loadFont("Minecraft-Evenings.otf", "Minecraft Evenings", 18.0F));
    this.titleLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
    this.titleLabel.setPosition(this.theGameEngine.getWidth() / 2 - 80, -4);
    this.titleLabel.setOpacity(0.7D);
    this.titleLabel.setSize(500, 40);
    this.closeButton = new LauncherButton(root);
    this.closeButton.setInvisible();
    this.closeButton.setPosition(this.theGameEngine.getWidth() - 35, 2);
    this.closeButton.setSize(15, 15);
    this.closeButton.setBackground(null);
    LauncherImage closeImage = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "close.png"));
    closeImage.setSize(15, 15);
    this.closeButton.setGraphic((Node)closeImage);
    this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            System.exit(0);
          }
        });
    this.reduceButton = new LauncherButton(root);
    this.reduceButton.setInvisible();
    this.reduceButton.setPosition(this.theGameEngine.getWidth() - 55, 2);
    this.reduceButton.setSize(15, 15);
    this.reduceButton.setBackground(null);
    LauncherImage reduceImage = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "reduce.png"));
    reduceImage.setSize(15, 15);
    this.reduceButton.setGraphic((Node)reduceImage);
    this.reduceButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            Stage stage = (Stage)((LauncherButton)event.getSource()).getScene().getWindow();
            stage.setIconified(true);
          }
        });
    this.usernameField = new LauncherTextField(root);
    this.usernameField.setText((String)this.config.getValue(EnumConfig.USERNAME));
    this.usernameField.setPosition(this.theGameEngine.getWidth() / 2 - 135, this.theGameEngine.getHeight() / 2 - 100);
    this.usernameField.setSize(270, 50);
    this.usernameField.setFont(FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", 14.0F));
    this.usernameField.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4); -fx-text-fill: white;");
    this.usernameField.setVisible(false);
    this.passwordField = new LauncherPasswordField(root);
    this.passwordField.setPosition(this.theGameEngine.getWidth() / 2 - 135, this.theGameEngine.getHeight() / 2 - 45);
    this.passwordField.setSize(270, 50);
    this.passwordField.setFont(FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", 14.0F));
    this.passwordField.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4); -fx-text-fill: white;");
    this.passwordField.setVoidText("Mot de passe");
    this.passwordField.setVisible(false);
    this.loginButton = new LauncherButton(root);
    this.loginButton.setInvisible();
    LauncherImage JouerButton = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "Jouer-Boutton.png"));
    this.loginButton.setGraphic((Node)JouerButton);
    this.loginButton.setPosition(this.theGameEngine.getWidth() / 300 + 340, this.theGameEngine.getHeight() - 280);
    this.loginButton.setSize(248, 54);
    this.loginButton.setVisible(false);
    this.loginButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.AccueilButton.setVisible(false);
            LauncherPanel.this.config.updateValue("username", LauncherPanel.this.usernameField.getText());
            if (LauncherPanel.this.usernameField.getText().length() < 3) {
            
            } else if (LauncherPanel.this.usernameField.getText().length() > 3 && LauncherPanel.this.passwordField.getText().isEmpty()) {
              GameAuth auth = new GameAuth(LauncherPanel.this.usernameField.getText(), LauncherPanel.this.passwordField.getText(), AccountType.OFFLINE);
              if (auth.isLogged())
                LauncherPanel.this.update(auth); 
            } else if (LauncherPanel.this.usernameField.getText().length() > 3 && !LauncherPanel.this.passwordField.getText().isEmpty()) {
              GameAuth auth = new GameAuth(LauncherPanel.this.usernameField.getText(), LauncherPanel.this.passwordField.getText(), 
                  AccountType.MOJANG);
              if (auth.isLogged()) {
                LauncherPanel.this.update(auth);
              } else {
              
              } 
            } else {
            
            } 
          }
        });
    this.settingsButton = new LauncherButton(root);
    this.settingsButton.setInvisible();
    LauncherImage imageButton = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "settings.png"));
    this.settingsButton.setGraphic((Node)imageButton);
    this.settingsButton.setPosition(this.theGameEngine.getWidth() / 300 + 340, this.theGameEngine.getHeight() - 215);
    this.settingsButton.setSize(248, 54);
    this.settingsButton.setVisible(true);
    this.settingsButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.autoLoginLabel.setVisible(false);
            LauncherPanel.this.autoLoginButton.setVisible(false);
            LauncherPanel.this.autoLoginRectangle.setVisible(false);
            Scene scene = new Scene(LauncherPanel.this.createSettingsPanel());
            Stage stage = new Stage();
            scene.setFill(Color.TRANSPARENT);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Parametres Launcher");
            stage.setWidth(500.0D);
            stage.setHeight(300.0D);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            LauncherPanel.this.autoLoginTimer.cancel();
          }
        });
    this.AccueilButton = new LauncherButton(root);
    this.AccueilButton.setInvisible();
    this.AccueilButton.setPosition(this.theGameEngine.getWidth() / 2 + 535 - 250, this.theGameEngine.getHeight() - 120);
    LauncherImage accueilImg = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "Accueil.png"));
    accueilImg.setSize(157, 54);
    this.AccueilButton.setGraphic((Node)accueilImg);
    this.AccueilButton.setSize((int)accueilImg.getFitWidth(), (int)accueilImg.getFitHeight());
    this.AccueilButton.setBackground(null);
    this.AccueilButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.microsoftButton.setVisible(true);
            LauncherPanel.this.mojangButton.setVisible(true);
            LauncherPanel.this.crackButton.setVisible(true);
            LauncherPanel.this.settingsButton.setVisible(true);
            LauncherPanel.this.usernameField.setVisible(false);
            LauncherPanel.this.passwordField.setVisible(false);
            LauncherPanel.this.loginButton.setVisible(false);
          }
        });
    this.websiteButton = new LauncherButton(root);
    this.websiteButton.setInvisible();
    this.websiteButton.setPosition(this.theGameEngine.getWidth() / 2 - 125 - 150, this.theGameEngine.getHeight() - 130);
    LauncherImage websiteImg = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "site_icon.png"));
    websiteImg.setSize(80, 80);
    this.websiteButton.setGraphic((Node)websiteImg);
    this.websiteButton.setSize((int)websiteImg.getFitWidth(), (int)websiteImg.getFitHeight());
    this.websiteButton.setBackground(null);
    this.websiteButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.openLink(LauncherPanel.this.WEBSITE_URL);
          }
        });
    this.facebookButton = new LauncherButton(root);
    this.facebookButton.setInvisible();
    this.facebookButton.setPosition(this.theGameEngine.getWidth() / 2 - 125, this.theGameEngine.getHeight() - 130);
    LauncherImage facebookImg = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "fb_icon.png"));
    facebookImg.setSize(80, 80);
    this.facebookButton.setGraphic((Node)facebookImg);
    this.facebookButton.setSize((int)facebookImg.getFitWidth(), (int)facebookImg.getFitHeight());
    this.facebookButton.setBackground(null);
    this.facebookButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.openLink(LauncherPanel.this.FACEBOOK_URL);
          }
        });
    this.twitterButton = new LauncherButton(root);
    this.twitterButton.setInvisible();
    this.twitterButton.setPosition(this.theGameEngine.getWidth() / 2 + 25, this.theGameEngine.getHeight() - 130);
    LauncherImage twitterImg = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "twitter_icon.png"));
    twitterImg.setSize(80, 80);
    this.twitterButton.setGraphic((Node)twitterImg);
    this.twitterButton.setSize((int)twitterImg.getFitWidth(), (int)twitterImg.getFitHeight());
    this.twitterButton.setBackground(null);
    this.twitterButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.openLink(LauncherPanel.this.TWITTER_URL);
          }
        });
    this.discordButton = new LauncherButton(root);
    this.discordButton.setInvisible();
    this.discordButton.setPosition(this.theGameEngine.getWidth() / 2 - 125 + 300, this.theGameEngine.getHeight() - 130);
    LauncherImage discordImg = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "discord_icon.png"));
    discordImg.setSize(80, 80);
    this.discordButton.setGraphic((Node)discordImg);
    this.discordButton.setSize((int)discordImg.getFitWidth(), (int)discordImg.getFitHeight());
    this.discordButton.setBackground(null);
    this.discordButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.openLink(LauncherPanel.this.DISCORD_URL);
          }
        });
    this.microsoftButton = new LauncherButton(root);
    LauncherImage mcaImage = new LauncherImage(root, loadImage(this.theGameEngine, "Microsoft-Boutton.png"));
    mcaImage.setSize(342, 54);
    this.microsoftButton.setGraphic((Node)mcaImage);
    this.microsoftButton.setPosition(this.theGameEngine.getWidth() / 300 - 6 + 300, this.theGameEngine.getHeight() - 399);
    this.microsoftButton.setInvisible();
    this.microsoftButton.setVisible(true);
    this.microsoftButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.autoLoginLabel.setVisible(false);
            LauncherPanel.this.autoLoginButton.setVisible(false);
            LauncherPanel.this.autoLoginRectangle.setVisible(false);
            LauncherPanel.this.gameAuthentication = new GameAuth(AccountType.MICROSOFT);
            LauncherPanel.this.showMicrosoftAuth(LauncherPanel.this.gameAuthentication);
            if (LauncherPanel.this.gameAuthentication.isLogged())
              LauncherPanel.this.update(LauncherPanel.this.gameAuthentication); 
            LauncherPanel.this.autoLoginTimer.cancel();
          }
        });
    this.mojangButton = new LauncherButton(root);
    this.mojangButton.setInvisible();
    this.mojangButton.setPosition(this.theGameEngine.getWidth() / 300 - 6 + 300, this.theGameEngine.getHeight() - 340);
    LauncherImage mojangImg = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "Mojang-Boutton.png"));
    mojangImg.setSize(342, 54);
    this.mojangButton.setGraphic((Node)mojangImg);
    this.mojangButton.setSize((int)mojangImg.getFitWidth(), (int)mojangImg.getFitHeight());
    this.mojangButton.setBackground(null);
    this.mojangButton.setVisible(true);
    this.mojangButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
              LauncherPanel.this.microsoftButton.setVisible(false);
              LauncherPanel.this.mojangButton.setVisible(false);
              LauncherPanel.this.crackButton.setVisible(false);
              LauncherPanel.this.usernameField.setVisible(true);
              LauncherPanel.this.passwordField.setVisible(true);
              LauncherPanel.this.loginButton.setVisible(true);
              LauncherPanel.this.usernameField.setVoidText("Email");
              LauncherPanel.this.autoLoginLabel.setVisible(false);
              LauncherPanel.this.autoLoginButton.setVisible(false);
              LauncherPanel.this.autoLoginRectangle.setVisible(false);
              LauncherPanel.this.autoLoginTimer.cancel();
          }
        });
    this.crackButton = new LauncherButton(root);
    this.crackButton.setInvisible();
    this.crackButton.setPosition(this.theGameEngine.getWidth() / 300 - 6 + 300, this.theGameEngine.getHeight() - 280);
    LauncherImage crackImg = new LauncherImage(root, getResourceLocation().loadImage(this.theGameEngine, "Crack-Boutton.png"));
    crackImg.setSize(342, 54);
    this.crackButton.setGraphic((Node)crackImg);
    this.crackButton.setSize((int)crackImg.getFitWidth(), (int)crackImg.getFitHeight());
    this.crackButton.setBackground(null);
    this.crackButton.setVisible(true);
    this.crackButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.microsoftButton.setVisible(false);
            LauncherPanel.this.mojangButton.setVisible(false);
            LauncherPanel.this.crackButton.setVisible(false);
            LauncherPanel.this.usernameField.setVisible(true);
            LauncherPanel.this.loginButton.setVisible(true);
            LauncherPanel.this.usernameField.setVoidText("Pseudo");
            LauncherPanel.this.autoLoginLabel.setVisible(false);
            LauncherPanel.this.autoLoginButton.setVisible(false);
            LauncherPanel.this.autoLoginRectangle.setVisible(false);
            LauncherPanel.this.autoLoginTimer.cancel();
          }
        });
    this.updateRectangle = new LauncherRectangle(root, this.theGameEngine.getWidth() / 2 - 175, this.theGameEngine.getHeight() / 2 - 60, 350, 180);
    this.updateRectangle.setArcWidth(10.0D);
    this.updateRectangle.setArcHeight(10.0D);
    this.updateRectangle.setFill(Color.rgb(0, 0, 0, 0.6D));
    this.updateRectangle.setVisible(false);
    this.updateLabel = new LauncherLabel(root);
    this.updateLabel.setText("- MISE A JOUR -");
    this.updateLabel.setAlignment(Pos.CENTER);
    this.updateLabel.setFont(FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", 22.0F));
    this.updateLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
    this.updateLabel.setPosition(this.theGameEngine.getWidth() / 2 - 95, this.theGameEngine.getHeight() / 2 - 55);
    this.updateLabel.setOpacity(1.0D);
    this.updateLabel.setSize(190, 40);
    this.updateLabel.setVisible(false);
    this.currentStep = new LauncherLabel(root);
    this.currentStep.setText("Preparation de la mise a jour.");
    this.currentStep.setFont(Font.font("Verdana", FontPosture.ITALIC, 18.0D));
    this.currentStep.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
    this.currentStep.setAlignment(Pos.CENTER);
    this.currentStep.setPosition(this.theGameEngine.getWidth() / 2 - 160, this.theGameEngine.getHeight() / 2 + 83);
    this.currentStep.setOpacity(0.4D);
    this.currentStep.setSize(320, 40);
    this.currentStep.setVisible(false);
    this.currentFileLabel = new LauncherLabel(root);
    this.currentFileLabel.setText("launchwrapper-12.0.jar");
    this.currentFileLabel.setFont(FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", 18.0F));
    this.currentFileLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
    this.currentFileLabel.setAlignment(Pos.CENTER);
    this.currentFileLabel.setPosition(this.theGameEngine.getWidth() / 2 - 160, this.theGameEngine.getHeight() / 2 + 25);
    this.currentFileLabel.setOpacity(0.8D);
    this.currentFileLabel.setSize(320, 40);
    this.currentFileLabel.setVisible(false);
    this.percentageLabel = new LauncherLabel(root);
    this.percentageLabel.setText("0%");
    this.percentageLabel.setFont(FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", 30.0F));
    this.percentageLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
    this.percentageLabel.setAlignment(Pos.CENTER);
    this.percentageLabel.setPosition(this.theGameEngine.getWidth() / 2 - 50, this.theGameEngine.getHeight() / 2 - 5);
    this.percentageLabel.setOpacity(0.8D);
    this.percentageLabel.setSize(100, 40);
    this.percentageLabel.setVisible(false);
    this.bar = new LauncherProgressBar(root);
    this.bar.setPosition(this.theGameEngine.getWidth() / 2 - 125, this.theGameEngine.getHeight() / 2 + 60);
    this.bar.setSize(250, 20);
    this.bar.setVisible(false);
    this.autoLoginRectangle = new LauncherRectangle(root, 0, this.theGameEngine.getHeight() - 32, 1000, this.theGameEngine.getHeight());
    this.autoLoginRectangle.setFill(Color.rgb(0, 0, 0, 0.7D));
    this.autoLoginRectangle.setOpacity(1.0D);
    this.autoLoginRectangle.setVisible(false);
    this.autoLoginLabel = new LauncherLabel(root);
    this.autoLoginLabel.setText("Connexion auto dans 3 secondes. Appuyez sur ECHAP pour annuler.");
    this.autoLoginLabel.setFont(FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", 18.0F));
    this.autoLoginLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: red;");
    this.autoLoginLabel.setPosition(this.theGameEngine.getWidth() / 2 - 280, this.theGameEngine.getHeight() - 34);
    this.autoLoginLabel.setOpacity(0.7D);
    this.autoLoginLabel.setSize(700, 40);
    this.autoLoginLabel.setVisible(false);
    this.autoLoginButton = new LauncherButton(root);
    this.autoLoginButton.setText("Annuler");
    this.autoLoginButton.setFont(FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", 14.0F));
    this.autoLoginButton.setPosition(this.theGameEngine.getWidth() / 2 + 60, this.theGameEngine.getHeight() - 30);
    this.autoLoginButton.setSize(200, 20);
    this.autoLoginButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.4); -fx-text-fill: black;");
    this.autoLoginButton.setVisible(false);
    this.autoLoginButton.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            LauncherPanel.this.autoLoginLabel.setVisible(false);
            LauncherPanel.this.autoLoginButton.setVisible(false);
            LauncherPanel.this.autoLoginRectangle.setVisible(false);
            LauncherPanel.this.autoLoginTimer.cancel();
          }
        });
    String userName = (String)this.config.getValue(EnumConfig.USERNAME);
    if (userName.length() > 2 && !userName.contains("@") && this.config.getValue(EnumConfig.AUTOLOGIN).equals(Boolean.valueOf(true)))
      Platform.runLater(new Runnable() {
            public void run() {
              LauncherPanel.this.autoLoginTimer = new Timer();
              TimerTask timerTask = new TimerTask() {
                  int waitTime = 5;
                  
                  int elapsed = 0;
                  
                  public void run() {
                    this.elapsed++;
                    if (this.elapsed % this.waitTime == 0) {
                      if (!theGameEngine.getGameMaintenance().isAccessBlocked()) {
                        crackButton.fire();
                        loginButton.fire();
                        autoLoginTimer.cancel();
                        autoLoginLabel.setVisible(false);
                        autoLoginButton.setVisible(false);
                        autoLoginRectangle.setVisible(false);
                      } 
                    } else {
                      final int time = this.waitTime - this.elapsed % this.waitTime;
                      Platform.runLater(new Runnable() {
                            public void run() {
                              autoLoginLabel.setText("Connexion auto dans " + time + " secondes.");
                            }
                          });
                    } 
                  }
                };
              LauncherPanel.this.autoLoginTimer.schedule(timerTask, 0L, 1000L);
              LauncherPanel.this.autoLoginLabel.setVisible(true);
              LauncherPanel.this.autoLoginRectangle.setVisible(true);
              LauncherPanel.this.autoLoginButton.setVisible(true);
            }
          }); 
  }
  
  private void update(GameAuth auth) {
    this.usernameField.setDisable(true);
    this.passwordField.setDisable(true);
    this.loginButton.setDisable(true);
    this.settingsButton.setDisable(true);
    this.usernameField.setVisible(false);
    this.passwordField.setVisible(false);
    this.loginButton.setVisible(false);
    this.settingsButton.setVisible(false);
    this.updateRectangle.setVisible(true);
    this.updateLabel.setVisible(true);
    this.currentStep.setVisible(true);
    this.currentFileLabel.setVisible(true);
    this.percentageLabel.setVisible(true);
    this.bar.setVisible(true);
    this.updater.reg(this.theGameEngine);
    this.updater.reg(auth.getSession());
    this.theGameEngine.reg(GameMemory.getMemory(Double.parseDouble((String)this.config.getValue(EnumConfig.RAM))));
    this.theGameEngine.reg(GameSize.getWindowSize(Integer.parseInt((String)this.config.getValue(EnumConfig.GAME_SIZE))));
    boolean useVmArgs = ((Boolean)this.config.getValue(EnumConfig.USE_VM_ARGUMENTS)).booleanValue();
    String vmArgs = (String)this.config.getValue(EnumConfig.VM_ARGUMENTS);
    String[] s = null;
    if (useVmArgs) {
      if (vmArgs.length() > 3)
        s = vmArgs.split(" "); 
      JVMArguments arguments = new JVMArguments(s);
      this.theGameEngine.reg(arguments);
    } 
    this.theGameEngine.reg(this.updater);
    this.updateThread = new Thread() {
        public void run() {
          LauncherPanel.this.theGameEngine.getGameUpdater().run();
        }
      };
    this.updateThread.start();
    this.timeline = new Timeline(
        new KeyFrame[] { new KeyFrame(Duration.seconds(0.0D), new EventHandler<ActionEvent>() {
              public void handle(ActionEvent event) {
                LauncherPanel.this.microsoftButton.setVisible(false);
                LauncherPanel.this.mojangButton.setVisible(false);
                LauncherPanel.this.crackButton.setVisible(false);
                LauncherPanel.this.settingsButton.setVisible(false);
                LauncherPanel.this.AccueilButton.setVisible(false);
                LauncherPanel.this.timelineUpdate(LauncherPanel.this.theGameEngine);
              }
            },  new javafx.animation.KeyValue[0]), 
          new KeyFrame(Duration.seconds(0.1D), new javafx.animation.KeyValue[0]) });
    this.timeline.setCycleCount(-1);
    this.timeline.play();
  }
  
  private Parent createSettingsPanel() {
    LauncherPane contentPane = new LauncherPane(this.theGameEngine);
    Rectangle rect = new Rectangle(500.0D, 300.0D);
    rect.setArcHeight(15.0D);
    rect.setArcWidth(15.0D);
    contentPane.setClip(rect);
    contentPane.setStyle("-fx-background-color: transparent;");
    return (Parent)contentPane;
  }
  
  public void timelineUpdate(GameEngine engine) {
    if ((engine.getGameUpdater()).downloadedFiles > 0)
      this.percentageLabel.setText(String.valueOf(this.decimalFormat.format(
              (engine.getGameUpdater()).downloadedFiles * 100.0D / (engine.getGameUpdater()).filesToDownload)) + "%"); 
    this.currentFileLabel.setText(engine.getGameUpdater().getCurrentFile());
    this.currentStep.setText(engine.getGameUpdater().getCurrentInfo());
    double percent = (engine.getGameUpdater()).downloadedFiles * 100.0D / (engine.getGameUpdater()).filesToDownload / 100.0D;
    this.bar.setProgress(percent);
  }
  
  private void showMicrosoftAuth(GameAuth auth) {
    Scene scene = new Scene(createMicrosoftPanel(auth));
    Stage stage = new Stage();
    scene.setFill(Color.TRANSPARENT);
    stage.setResizable(false);
    stage.setTitle("Microsoft Authentication");
    stage.setWidth(500.0D);
    stage.setHeight(600.0D);
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }
  
  private Parent createMicrosoftPanel(GameAuth auth) {
    LauncherPane contentPane = new LauncherPane(this.theGameEngine);
    auth.connectMicrosoft((Pane)contentPane);
    return (Parent)contentPane;
  }
}
