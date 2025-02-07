
package com.mycompany.gudang_afj_uas;

import javafx.beans.property.*;

import javafx.beans.property.*;

public class Item {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty category;
    private final IntegerProperty stock;
    private final StringProperty createdAt;

    // Constructor LENGKAP (dengan createdAt)
    public Item(int id, String name, String category, int stock, String createdAt) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.stock = new SimpleIntegerProperty(stock);
        this.createdAt = new SimpleStringProperty(createdAt);
    }

    // Constructor TANPA createdAt (untuk digunakan di ItemView.java)
    public Item(int id, String name, String category, int stock) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.stock = new SimpleIntegerProperty(stock);
        this.createdAt = new SimpleStringProperty(""); // Kosongkan
    }

    // Getter dan Setter
    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getCategory() { return category.get(); }
    public StringProperty categoryProperty() { return category; }

    public int getStock() { return stock.get(); }
    public IntegerProperty stockProperty() { return stock; }

    public String getCreatedAt() { return createdAt.get(); }
    public StringProperty createdAtProperty() { return createdAt; }
}
