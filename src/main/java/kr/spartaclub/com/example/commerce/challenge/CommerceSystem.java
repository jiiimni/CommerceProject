package kr.spartaclub.com.example.commerce.challenge;

import kr.spartaclub.com.example.commerce.Product;

import java.util.List;
import java.util.Scanner;

// CommerceSystem 클래스
// 프로그램 전체 흐름을 제어하는 클래스
// 메인 메뉴, 카테고리 이동, 장바구니 담기, 주문 관리 흐름을 담당한다.
public class CommerceSystem {

    // 여러 카테고리를 관리하는 리스트
    private final List<Category> categories;

    // 사용자 입력을 받기 위한 Scanner
    private final Scanner sc = new Scanner(System.in);

    // 장바구니 객체
    private final Cart cart = new Cart();

    // CommerceSystem 생성자
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    // 프로그램 시작 메서드
    public void start() {
        while (true) {
            printMainMenu();
            int input = sc.nextInt();

            // 종료 처리
            if (input == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            // 주문 관리 - 장바구니 확인
            if (input == 4) {
                if (cart.isEmpty()) {
                    System.out.println("장바구니가 비어 있습니다.\n");
                } else {
                    showCartAndOrderMenu();
                }
                continue;
            }

            // 주문 관리 - 주문 취소
            if (input == 5) {
                if (cart.isEmpty()) {
                    System.out.println("취소할 주문이 없습니다.\n");
                } else {
                    cart.clear();
                    System.out.println("주문이 취소되었습니다.\n");
                }
                continue;
            }

            // 카테고리 선택
            if (input >= 1 && input <= categories.size()) {
                Category selectedCategory = categories.get(input - 1);
                showCategoryMenu(selectedCategory);
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }

    // 메인 메뉴 출력
    private void printMainMenu() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
        }

        System.out.println("0. 종료      | 프로그램 종료");

        // 장바구니가 비어있지 않을 때만 주문 관리 메뉴 출력
        if (!cart.isEmpty()) {
            System.out.println();
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다.");
        }

        System.out.print("> ");
    }

    // 카테고리 메뉴 처리
    private void showCategoryMenu(Category category) {
        while (true) {
            System.out.println();
            System.out.printf("[ %s 카테고리 ]%n", category.getName());

            List<Product> products = category.getProducts();

            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                System.out.printf("%d. %-12s | %,d원 | %s | 재고: %d개%n",
                        i + 1,
                        p.getName(),
                        p.getPrice(),
                        p.getDescription(),
                        p.getStock());
            }

            System.out.println("0. 뒤로가기");
            System.out.print("> ");
            int input = sc.nextInt();

            if (input == 0) {
                System.out.println();
                return;
            }

            if (input >= 1 && input <= products.size()) {
                Product selected = products.get(input - 1);

                System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개%n",
                        selected.getName(),
                        selected.getPrice(),
                        selected.getDescription(),
                        selected.getStock());

                System.out.println();
                System.out.printf("\"%s | %,d원 | %s\"%n",
                        selected.getName(),
                        selected.getPrice(),
                        selected.getDescription());
                System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인        2. 취소");
                System.out.print("> ");
                int confirm = sc.nextInt();

                if (confirm == 1) {
                    System.out.print("담을 수량을 입력해 주세요.\n수량 : ");
                    int quantity = sc.nextInt();

                    if (quantity <= 0) {
                        System.out.println("수량은 1개 이상이어야 합니다.\n");
                        continue;
                    }

                    if (selected.getStock() < quantity) {
                        System.out.println("재고가 부족합니다.\n");
                        continue;
                    }

                    cart.add(selected, quantity);
                    System.out.printf("%s가 %d개 장바구니에 추가되었습니다.%n%n",
                            selected.getName(), quantity);
                } else {
                    System.out.println("취소되었습니다.\n");
                }
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }

    // 장바구니 확인 및 주문 메뉴
    private void showCartAndOrderMenu() {
        System.out.println();
        System.out.println("[ 장바구니 내역 ]");

        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            System.out.printf("%s | %,d원 | %s | 수량: %d개%n",
                    p.getName(),
                    p.getPrice(),
                    p.getDescription(),
                    item.getQuantity());
        }

        int total = cart.getTotalAmount();

        System.out.println();
        System.out.println("[ 총 주문 금액 ]");
        System.out.printf("%,d원%n%n", total);

        System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
        System.out.print("> ");
        int input = sc.nextInt();

        if (input == 1) {
            confirmOrder();
        } else {
            System.out.println();
        }
    }

    // 주문 확정 처리
    private void confirmOrder() {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            product.decreaseStock(quantity);
        }

        int total = cart.getTotalAmount();
        cart.clear();

        System.out.printf("주문이 완료되었습니다! 총 금액: %,d원%n%n", total);
    }
}
