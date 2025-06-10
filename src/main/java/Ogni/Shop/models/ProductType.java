package Ogni.Shop.models;

import lombok.Getter;

@Getter
public enum ProductType {
    toy("Игрушка"),
    keychain("Брелок"),
    set("Набор"),
    other("Остальное");
    private final String name;
    ProductType(String name) {this.name = name;}
}
