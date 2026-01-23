package kr.spartaclub.com.example.commerce;

public class Product {
    private final String name;
    private int price;
    private String description;
    private int stock;

    public Product(String name, int price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    // 주문 확정 시 재고 감소를 위한 메서드
    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("차감 수량은 0보다 커야 합니다.");
        }
        if (stock < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        stock -= quantity;
    }

    public void setPrice(int price) {
        if (price <= 0) throw new IllegalArgumentException("가격은 0원보다 커야 합니다.");
        this.price = price;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("설명은 비어 있을 수 없습니다.");
        this.description = description;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("재고는 0개 이상이어야 합니다.");
        this.stock = stock;
    }

}
