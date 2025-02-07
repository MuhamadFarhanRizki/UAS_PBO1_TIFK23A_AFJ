
package com.mycompany.gudang_afj_uas;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private Stage primaryStage;
    private UserOperations userOperations;

    public LoginView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public VBox getView() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #87CEFA, #FFFFFF); -fx-alignment: center;");

        // Elemen Login
        Label titleLabel = new Label("🔑 Welcome to Gudang AFJ");
        titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #2E8B57;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("👤 Username");
        usernameField.setStyle("-fx-pref-width: 320px; -fx-padding: 12px; -fx-border-radius: 8px; -fx-border-color: #4682B4; -fx-font-size: 14px;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("🔒 Password");
        passwordField.setStyle("-fx-pref-width: 320px; -fx-padding: 12px; -fx-border-radius: 8px; -fx-border-color: #4682B4; -fx-font-size: 14px;");

        Button loginButton = new Button("🚀 Login");
        loginButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-padding: 12px 20px; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-radius: 8px;");
        
        Label registerLink = new Label("❓ Belum punya akun? Yuk Register di sini.");
        registerLink.setStyle("-fx-text-fill: #00008B; -fx-underline: true; -fx-font-size: 13px;");
        registerLink.setOnMouseClicked(event -> {
            // Arahkan ke RegisterView
            RegisterView registerView = new RegisterView(primaryStage);
            Scene registerScene = new Scene(registerView.getView(), 800, 600);
            primaryStage.setScene(registerScene);
        });

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                userOperations = new UserOperations();
            } catch (SQLException ex) {
                Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (userOperations.loginUser(username, password)) {
                // Login sukses, arahkan ke Dashboard
                DashboardView dashboardView = new DashboardView(primaryStage, username);
                Scene dashboardScene = new Scene(dashboardView.getView(), 800, 600);
                primaryStage.setScene(dashboardScene);
            } else {
                showError("❌ Login gagal! Periksa username dan password Anda.");
            }
        });

        root.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, registerLink);
        return root;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("⚠ Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

