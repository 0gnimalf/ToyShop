package Ogni.Shop.models;

import lombok.Getter;

@Getter
public enum OrderStatus {
    inProcessing("В обработке"),
    accepted("Принят"),
    sent("Отправлен"),
    completed("Завершен"),
    other("Иное");
    private final String name;
    OrderStatus(String name) {this.name = name;}
}