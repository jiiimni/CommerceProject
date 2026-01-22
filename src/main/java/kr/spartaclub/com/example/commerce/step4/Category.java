package kr.spartaclub.com.example.commerce.step4;

import kr.spartaclub.com.example.commerce.Product;

import java.util.List;


 // 하나의 카테고리(전자제품, 의류, 식품)를 표현하는 객체 - 카테고리 이름과 해당 카테고리에 속한 상품 목록을 함께 관리한다.
public class Category {

    // 카테고리 이름 - 외부에서 직접 변경하지 못하도록 private + final
    private final String name;

    // 해당 카테고리에 속한 상품 리스트 - 외부에서 직접 접근하지 못하도록 private으로 선언
    private final List<Product> products;

    /**
     * Category 생성자
     * @param name 카테고리 이름
     * @param products 해당 카테고리에 포함될 상품 목록
     */
    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    //카테고리 이름 조회
    public String getName() {
        return name;
    }

    // 카테고리에 속한 상품 목록 조회
    public List<Product> getProducts() {
        return products;
    }
}
