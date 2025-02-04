package Ogni.Shop.models;

import lombok.Getter;

@Getter
public enum PurchaseStatus {
    inProcessing("В обработке"),
    accepted("Принят"),
    sent("Отправлен"),
    completed("Завершен"),
    other("Иное");
    private final String name;
    PurchaseStatus(String name) {this.name = name;}
}