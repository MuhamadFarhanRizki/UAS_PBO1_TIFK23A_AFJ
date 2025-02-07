
package com.mycompany.gudang_afj_uas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemOperations {
    private Connection connection;

    public ItemOperations() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // CREATE
   public void addItem(Item item) {
    String query = "INSERT INTO items (name, category, stock) VALUES (?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, item.getName());
        stmt.setString(2, item.getCategory());
        stmt.setInt(3, item.getStock());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            System.out.println("❌ Gagal menambahkan barang! Tidak ada baris yang terpengaruh.");
        } else {
            System.out.println("✅ Barang berhasil ditambahkan!");
        }

        // Ambil ID yang baru saja dibuat (pastikan ID AUTO_INCREMENT berfungsi)
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                System.out.println("🆔 ID Barang Baru: " + newId);
            } else {
                System.out.println("⚠️ Tidak bisa mendapatkan ID barang baru.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("❌ ERROR saat menyimpan barang: " + e.getMessage());
    }
}


    // READ
   public List<Item> getItems() {
    List<Item> items = new ArrayList<>();
    String query = "SELECT * FROM items";

    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        System.out.println("📡 Mengambil data dari database...");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            int stock = rs.getInt("stock");
            String createdAt = rs.getString("created_at");

            System.out.println("✅ Data ditemukan: " + name + " | Stok: " + stock);

            items.add(new Item(id, name, category, stock, createdAt));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("❌ Gagal mengambil data dari database!");
    }

    System.out.println("🔄 Total data yang diambil: " + items.size());
    return items;
}

    // UPDATE
   public void updateItem(Item item) {
    String query = "UPDATE items SET name = ?, category = ?, stock = ? WHERE id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, item.getName());
        stmt.setString(2, item.getCategory());
        stmt.setInt(3, item.getStock());
        stmt.setInt(4, item.getId()); // ID barang yang akan diperbarui

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            System.out.println("❌ Gagal memperbarui barang! Tidak ada baris yang terpengaruh.");
        } else {
            System.out.println("✅ Barang berhasil diperbarui!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("❌ ERROR saat memperbarui barang: " + e.getMessage());
    }
}

    // DELETE
    public void deleteItem(int id) {
        String query = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Item deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
