package kr.spartaclub.com.example.commerce.step3;

import kr.spartaclub.com.example.commerce.Product;
import java.util.List;

public class Category {
    private final String name;          // 카테고리 이름(전자제품/의류/식품)
    private final List<Product> products; // 카테고리 안 상품들

    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }
}
