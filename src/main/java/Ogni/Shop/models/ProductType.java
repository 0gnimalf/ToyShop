package Ogni.Shop.models;

import lombok.Getter;

@Getter
public enum ProductType {
    keychain("Брелок"),
    toy("Игрушка"),
    other("Остальное");
    private final String name;
    ProductType(String name) {this.name = name;}
}
