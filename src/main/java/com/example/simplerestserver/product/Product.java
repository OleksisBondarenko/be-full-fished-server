package com.example.simplerestserver.product;

public record Product (String title,  String description, int weight, String image, String href, int price, int salePrice ) {
    @Override
    public String toString() {
        return this.title;
    }
}
