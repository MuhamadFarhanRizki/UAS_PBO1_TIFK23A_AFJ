
package com.mycompany.gudang_afj_uas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemView {
    private ItemOperations itemOperations;
    private TableView<Item> tableView;
    private ObservableList<Item> itemList;
    private Stage primaryStage;
    private String username;

    public ItemView(Stage primaryStage, String username) {
        this.primaryStage = primaryStage;
        this.username = username;
        try {
            itemOperations = new ItemOperations();
            itemList = FXCollections.observableArrayList(itemOperations.getItems());
        } catch (Exception e) {  // Menangkap semua error agar tidak merah di NetBeans
            Logger.getLogger(ItemView.class.getName()).log(Level.SEVERE, "Gagal mengambil data barang.", e);
        }
    }

    public BorderPane getView() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #ffffff);");

        // Navigasi
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(15));
        menu.setStyle("-fx-background-color: #4682B4; -fx-padding: 15px; -fx-border-radius: 10px;");

        Button dashboardButton = new Button("🏠 Dashboard");
        dashboardButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white;");
        dashboardButton.setOnAction(e -> {
            DashboardView dashboardView = new DashboardView(primaryStage, username);
            primaryStage.setScene(new Scene(dashboardView.getView(), 800, 600));
        });

        Button logoutButton = new Button("🚪 Logout");
        logoutButton.setStyle("-fx-background-color: #DC143C; -fx-text-fill: white;");
        logoutButton.setOnAction(e -> {
            LoginView loginView = new LoginView(primaryStage);
            primaryStage.setScene(new Scene(loginView.getView(), 800, 600));
        });

        menu.getChildren().addAll(new Label("📌 Menu"), dashboardButton, logoutButton);
        root.setLeft(menu);

        // TableView Barang
        tableView = new TableView<>();
        tableView.setItems(itemList);

        TableColumn<Item, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty().asObject());

        TableColumn<Item, String> nameColumn = new TableColumn<>("Nama");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Item, String> categoryColumn = new TableColumn<>("Kategori");
        categoryColumn.setCellValueFactory(data -> data.getValue().categoryProperty());

        TableColumn<Item, Integer> stockColumn = new TableColumn<>("Stok");
        stockColumn.setCellValueFactory(data -> data.getValue().stockProperty().asObject());

        tableView.getColumns().addAll(idColumn, nameColumn, categoryColumn, stockColumn);

        // Tombol Tambah Barang
       Button addButton = new Button("➕ Tambah Barang");
addButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white;");
addButton.setOnAction(e -> {
    showItemModal(null);
    refreshTable(); // Pastikan tabel diperbarui setelah modal ditutup
});

        // Tombol Hapus Barang
        Button deleteButton = new Button("🗑 Hapus Barang");
        deleteButton.setStyle("-fx-background-color: #DC143C; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> deleteSelectedItem());

        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.getChildren().addAll(addButton, deleteButton, tableView);
        root.setCenter(content);

        return root;
    }

    private void showItemModal(Item item) {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setTitle(item == null ? "Tambah Barang" : "Edit Barang");

        TextField nameField = new TextField(item == null ? "" : item.getName());
        TextField categoryField = new TextField(item == null ? "" : item.getCategory());
        TextField stockField = new TextField(item == null ? "" : String.valueOf(item.getStock()));

        Button saveButton = new Button(item == null ? "➕ Tambah" : "💾 Simpan");
        saveButton.setOnAction(e -> {
            try {
                int stock = Integer.parseInt(stockField.getText());
                if (item == null) {
                    itemOperations.addItem(new Item(0, nameField.getText(), categoryField.getText(), stock));
                } else {
                    itemOperations.updateItem(new Item(item.getId(), nameField.getText(), categoryField.getText(), stock));
                }
                refreshTable();
                modalStage.close();
            } catch (Exception ex) {  // Hindari error input
                Logger.getLogger(ItemView.class.getName()).log(Level.SEVERE, "Gagal menyimpan data.", ex);
                showError("Gagal menyimpan data.");
            }
        });

        VBox modalContent = new VBox(10);
        modalContent.getChildren().addAll(new Label("Nama Barang"), nameField, new Label("Kategori"), categoryField, new Label("Stok"), stockField, saveButton);
        Scene modalScene = new Scene(modalContent);
        modalStage.setScene(modalScene);
        modalStage.showAndWait();
    }

    private void deleteSelectedItem() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                itemOperations.deleteItem(selectedItem.getId());
                refreshTable();
            } catch (Exception e) {
                Logger.getLogger(ItemView.class.getName()).log(Level.SEVERE, "Gagal menghapus barang.", e);
                showError("Gagal menghapus barang.");
            }
        }
    }

   private void refreshTable() {
    try {
        List<Item> items = itemOperations.getItems();
        
        if (items.isEmpty()) {
            System.out.println("⚠ Tidak ada data yang ditampilkan di tabel.");
        } else {
            for (Item item : items) {
                System.out.println("📌 Data ke tabel: " + item.getName() + " | Kategori: " + item.getCategory() + " | Stok: " + item.getStock());
            }
        }
        
        itemList.setAll(items);  // Set data ke tabel
        System.out.println("✅ Data berhasil ditampilkan di tabel.");
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("❌ Gagal memperbarui tabel.");
    }
}


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
