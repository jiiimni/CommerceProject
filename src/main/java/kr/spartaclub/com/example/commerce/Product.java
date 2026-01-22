package kr.spartaclub.com.example.commerce;

public class Product {

    // 상품의 기본 정보 (생성 후 바뀌지 않는 값이므로 final로 둠)
    private final String name;        // 상품명
    private final int price;          // 가격
    private final String description; // 설명

    // 재고는 주문/장바구니 구현 시 감소할 수 있으므로 final X
    private int stock;                // 재고수량

    /**
     * 생성자: new Product(...)로 상품을 만들 때 필수 정보들을 한 번에 세팅
     */
    public Product(String name, int price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    // 캡슐화: 필드에 직접 접근하지 않고 getter로만 꺼내도록 설계
    public String getName() { return name; }

    public int getPrice() { return price; }

    public String getDescription() { return description; }

    public int getStock() { return stock; }
}
