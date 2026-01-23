package kr.spartaclub.com.example.commerce.challenge;

import kr.spartaclub.com.example.commerce.Product;

//장바구니에 담긴 한 줄 항목 - 어떤 상품을 몇 개 담았는지를 보관
public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("상품이 null일 수 없습니다.");
        if (quantity <= 0) throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");

        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // 같은 상품을 추가로 담을 때 수량만 늘릴 수 있게
    public void addQuantity(int add) {
        if (add <= 0) throw new IllegalArgumentException("추가 수량은 1개 이상이어야 합니다.");
        this.quantity += add;
    }

    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
