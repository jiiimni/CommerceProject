package kr.spartaclub.com.example.commerce.step3;

import kr.spartaclub.com.example.commerce.Product;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String name;
    private final List<Product> products;

    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = new ArrayList<>(products); // 외부 리스트 참조 끊기
    }

    public String getName() {
        return name;
    }

    // 내부 리스트를 그대로 주지 않고 복사본을 줌
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

}
