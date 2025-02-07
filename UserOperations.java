
package com.mycompany.gudang_afj_uas;

import java.sql.*;

public class UserOperations {
    private Connection connection;

    public UserOperations() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // Register (Mengembalikan boolean agar bisa digunakan dalam if statement)
    public boolean registerUser(String username, String password, String role) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // Pastikan untuk hash password sebelum menyimpan
            stmt.setString(3, role);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Mengembalikan true jika registrasi berhasil
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Mengembalikan false jika terjadi kesalahan
        }
    }

    // Login
    public boolean loginUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // Bandingkan dengan password yang di-hash
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Jika ada hasil, login sukses
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Logout
    public void logoutUser() {
        System.out.println("User logged out successfully!");
    }

    // Get Profile
    public User getProfile(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role") // Tambahkan role dalam User object
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update Password
    public boolean updatePassword(String username, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPassword); // Pastikan password di-hash
            stmt.setString(2, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Mengembalikan true jika berhasil
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
