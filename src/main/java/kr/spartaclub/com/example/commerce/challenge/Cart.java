package kr.spartaclub.com.example.commerce.challenge;

import kr.spartaclub.com.example.commerce.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 장바구니 전체를 관리하는 객체
 * - 항목 추가/조회/초기화/총금액 계산
 */
public class Cart {

    private final List<CartItem> items = new ArrayList<>();

    public boolean isEmpty() {
        return items.isEmpty();
    }

    // 외부에서 리스트를 직접 수정 못하게 방어적 반환
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * 상품을 장바구니에 담기
     * - 이미 담긴 상품이면 수량만 증가
     */
    public void add(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");

        for (CartItem item : items) {
            if (item.getProduct() == product) { // 같은 객체면 같은 상품으로 판단
                item.addQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public int getTotalAmount() {
        int sum = 0;
        for (CartItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    public void clear() {
        items.clear();
    }
}
