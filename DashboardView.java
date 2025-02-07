
package com.mycompany.gudang_afj_uas;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView {
    private Stage primaryStage;
    private UserOperations userOperations;
    private String username;

    public DashboardView(Stage primaryStage, String username) {
        this.primaryStage = primaryStage;
        this.username = username;
    }

    public BorderPane getView() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #ffffff);");

        // Menu Navigasi
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(15));
        menu.setStyle("-fx-background-color: #4682B4; -fx-padding: 15px; -fx-border-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 2, 2);");

        Label menuTitle = new Label("📌 Navigasi");
        menuTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button itemButton = new Button("📦 Data Barang");
        itemButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 12px 20px; -fx-border-radius: 8px;");
        itemButton.setOnAction(e -> {
            // Tampilkan ItemView
            System.out.println("Fitur Data Barang");
            ItemView itemView = new ItemView(primaryStage, username);
            primaryStage.setScene(new Scene(itemView.getView(), 800, 600));
        });

        Button logoutButton = new Button("🚪 Logout");
        logoutButton.setStyle("-fx-background-color: #DC143C; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 12px 20px; -fx-border-radius: 8px;");
        logoutButton.setOnAction(e -> {
            // Arahkan ke LoginView
            LoginView loginView = new LoginView(primaryStage);
            primaryStage.setScene(new Scene(loginView.getView(), 800, 600));
        });

        menu.getChildren().addAll(menuTitle, itemButton, logoutButton);
        root.setLeft(menu);

        // Tampilan Utama
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        Label welcomeLabel = new Label("🎉 Selamat Datang, " + username + " di Dashboard Gudang AFJ!");
        welcomeLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2E8B57;");

        Label rulesTitle = new Label("📜 Peraturan Gudang:");
        rulesTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #00008B;");

        Label rule1 = new Label("✅ Dilarang masuk tanpa izin.");
        Label rule2 = new Label("✅ Gunakan alat pelindung sesuai prosedur.");
        Label rule3 = new Label("✅ Catat keluar masuk barang dengan benar.");
        Label rule4 = new Label("✅ Jaga kebersihan dan kerapihan area kerja.");
        Label rule5 = new Label("✅ Laporkan jika ada barang rusak atau hilang.");

        rule1.setStyle("-fx-font-size: 14px;");
        rule2.setStyle("-fx-font-size: 14px;");
        rule3.setStyle("-fx-font-size: 14px;");
        rule4.setStyle("-fx-font-size: 14px;");
        rule5.setStyle("-fx-font-size: 14px;");

        try {
            userOperations = new UserOperations();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }

        User user = userOperations.getProfile(username);

        content.getChildren().addAll(welcomeLabel, rulesTitle, rule1, rule2, rule3, rule4, rule5);

        if (user != null) {
            // Tampilkan informasi profil pengguna
            Label profileLabel = new Label("👤 Username: " + user.getUsername());
            Label roleLabel = new Label("🔰 Role: " + user.getRole());
            profileLabel.setStyle("-fx-font-size: 14px;");
            roleLabel.setStyle("-fx-font-size: 14px;");
            content.getChildren().addAll(profileLabel, roleLabel);
        }

        root.setCenter(content);
        return root;
    }
}
