package kr.spartaclub.com.example.commerce.challenge;

import kr.spartaclub.com.example.commerce.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // 고객 정보를 관리하는 Map (email -> Customer)
    private final Map<String, Customer> customers = new HashMap<>();

    // 관리자 비밀번호
    private static final String ADMIN_PASSWORD = "admin123";

    // CommerceSystem 생성자
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;

        // 테스트용 고객 데이터
        customers.put("bronze@test.com", new Customer("브론즈", "bronze@test.com", CustomerGrade.BRONZE, 0));
        customers.put("silver@test.com", new Customer("실버", "silver@test.com", CustomerGrade.SILVER, 600_000));
        customers.put("gold@test.com", new Customer("골드", "gold@test.com", CustomerGrade.GOLD, 1_200_000));
        customers.put("platinum@test.com", new Customer("플래티넘", "platinum@test.com", CustomerGrade.PLATINUM, 2_500_000));
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

            // 관리자 모드
            if (input == 6) {
                runAdminMode();
                continue;
            }

            // 장바구니에서 특정 상품 제거
            if (input == 7) {
                if (cart.isEmpty()) {
                    System.out.println("장바구니가 비어 있습니다.\n");
                } else {
                    removeCartItemFlow();
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

        // 관리자 모드는 항상 출력
        System.out.println("6. 관리자 모드");

        // 장바구니가 비어있지 않을 때만 주문 관리 메뉴 출력
        if (!cart.isEmpty()) {
            System.out.println();
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다.");
            System.out.println("7. 장바구니 상품 제거 | 장바구니 항목을 삭제합니다.");
        }

        System.out.print("> ");
    }

    // 카테고리 메뉴 처리
    private void showCategoryMenu(Category category) {
        while (true) {
            System.out.println();
            System.out.printf("[ %s 카테고리 ]%n", category.getName());

            System.out.println("1. 전체 상품 보기");
            System.out.println("2. 가격대별 필터링 (100만원 이하)");
            System.out.println("3. 가격대별 필터링 (100만원 초과)");
            System.out.println("0. 뒤로가기");
            System.out.print("> ");

            int menu = sc.nextInt();

            if (menu == 0) {
                System.out.println();
                return;
            }

            List<Product> products = category.getProducts();

            List<Product> viewList;
            if (menu == 1) {
                viewList = products;
            } else if (menu == 2) {
                viewList = products.stream()
                        .filter(p -> p.getPrice() <= 1_000_000)
                        .toList();
            } else if (menu == 3) {
                viewList = products.stream()
                        .filter(p -> p.getPrice() > 1_000_000)
                        .toList();
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
                continue;
            }

            if (viewList.isEmpty()) {
                System.out.println("조건에 맞는 상품이 없습니다.\n");
                continue;
            }

            final int[] idx = {1};
            viewList.stream().forEach(p -> {
                System.out.printf("%d. %-12s | %,d원 | %s | 재고: %d개%n",
                        idx[0]++, p.getName(), p.getPrice(), p.getDescription(), p.getStock());
            });


            System.out.println("0. 뒤로가기");
            System.out.print("> ");
            int input = sc.nextInt();

            if (input == 0) {
                System.out.println();
                return;
            }

            if (input >= 1 && input <= viewList.size()) {
                Product selected = viewList.get(input - 1);

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

        System.out.println("1. 주문 하기      2. 메인으로 돌아가기");
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
        int total = cart.getTotalAmount();

        System.out.print("고객 이메일을 입력해주세요.\n입력 : ");
        String email = sc.next();

        Customer customer = customers.get(email);
        if (customer == null) {
            System.out.println("등록되지 않은 이메일입니다. (BRONZE로 처리됩니다)\n");
            customer = new Customer("비회원", email, CustomerGrade.BRONZE, 0);
        }

        CustomerGrade grade = customer.getGrade();
        int discount = (int) Math.round(total * grade.getDiscountRate());
        int finalPay = total - discount;

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            product.decreaseStock(quantity);
        }

        customer.addOrderAmount(finalPay);
        cart.clear();

        System.out.println("주문이 완료되었습니다!");
        System.out.printf("할인 전 금액: %,d원%n", total);
        System.out.printf("%s 등급 할인(%d%%): -%,d원%n", grade.name(), grade.getDiscountPercent(), discount);
        System.out.printf("최종 결제 금액: %,d원%n%n", finalPay);
    }

    // 장바구니에서 특정 상품을 제거하는 기능
    private void removeCartItemFlow() {
        System.out.print("제거할 상품명을 입력해주세요: ");
        sc.nextLine();
        String name = sc.nextLine();

        boolean removed = cart.removeByProductName(name);
        if (removed) {
            System.out.println("장바구니에서 제거되었습니다.\n");
        } else {
            System.out.println("해당 상품이 장바구니에 없습니다.\n");
        }
    }

    // 관리자 모드 기능
    private void runAdminMode() {
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("관리자 비밀번호를 입력해주세요: ");
            String pw = sc.next();

            if (ADMIN_PASSWORD.equals(pw)) {
                System.out.println("인증 성공\n");
                showAdminMenu();
                return;
            }

            attempts++;
            System.out.println("비밀번호가 올바르지 않습니다. (" + attempts + "/3)\n");
        }

        System.out.println("비밀번호 3회 실패로 메인 메뉴로 돌아갑니다.\n");
    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("[ 관리자 모드 ]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");
            System.out.print("> ");

            int input = sc.nextInt();

            if (input == 0) {
                System.out.println();
                return;
            }

            switch (input) {
                case 1 -> addProductFlow();
                case 2 -> updateProductFlow();
                case 3 -> deleteProductFlow();
                case 4 -> printAllProducts();
                default -> System.out.println("잘못된 입력입니다.\n");
            }
        }
    }

    private void addProductFlow() {
        Category category = selectCategory();
        if (category == null) return;

        System.out.println("[ " + category.getName() + " 카테고리에 상품 추가 ]");

        System.out.print("상품명을 입력해주세요: ");
        sc.nextLine();
        String name = sc.nextLine();

        if (findProductByName(category, name) != null) {
            System.out.println("같은 카테고리에 이미 같은 상품명이 존재합니다.\n");
            return;
        }

        System.out.print("가격을 입력해주세요: ");
        int price = sc.nextInt();

        System.out.print("상품 설명을 입력해주세요: ");
        sc.nextLine();
        String desc = sc.nextLine();

        System.out.print("재고수량을 입력해주세요: ");
        int stock = sc.nextInt();

        Product newProduct = new Product(name, price, desc, stock);

        System.out.printf("%s | %,d원 | %s | 재고: %d개%n",
                newProduct.getName(), newProduct.getPrice(), newProduct.getDescription(), newProduct.getStock());
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
        System.out.print("> ");
        int confirm = sc.nextInt();

        if (confirm == 1) {
            category.getProducts().add(newProduct);
            System.out.println("상품이 성공적으로 추가되었습니다!\n");
        } else {
            System.out.println("취소되었습니다.\n");
        }
    }

    private void updateProductFlow() {
        Category category = selectCategory();
        if (category == null) return;

        sc.nextLine();
        System.out.print("수정할 상품명을 입력해주세요: ");
        String name = sc.nextLine();

        Product target = findProductByName(category, name);
        if (target == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.\n");
            return;
        }

        System.out.printf("현재 상품 정보: %s | %,d원 | %s | 재고: %d개%n%n",
                target.getName(), target.getPrice(), target.getDescription(), target.getStock());

        System.out.println("수정할 항목을 선택해주세요:");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고수량");
        System.out.print("> ");
        int choice = sc.nextInt();

        try {
            if (choice == 1) {
                System.out.print("새로운 가격을 입력해주세요: ");
                int newPrice = sc.nextInt();
                int old = target.getPrice();
                target.setPrice(newPrice);
                System.out.printf("%s의 가격이 %,d원 → %,d원으로 수정되었습니다.%n%n",
                        target.getName(), old, newPrice);
            } else if (choice == 2) {
                sc.nextLine();
                System.out.print("새로운 설명을 입력해주세요: ");
                String newDesc = sc.nextLine();
                target.setDescription(newDesc);
                System.out.println(target.getName() + "의 설명이 수정되었습니다.\n");
            } else if (choice == 3) {
                System.out.print("새로운 재고수량을 입력해주세요: ");
                int newStock = sc.nextInt();
                int old = target.getStock();
                target.setStock(newStock);
                System.out.printf("%s의 재고가 %d개 → %d개로 수정되었습니다.%n%n",
                        target.getName(), old, newStock);
            } else {
                System.out.println("잘못된 입력입니다.\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("오류: " + e.getMessage() + "\n");
        }
    }

    private void deleteProductFlow() {
        Category category = selectCategory();
        if (category == null) return;

        sc.nextLine();
        System.out.print("삭제할 상품명을 입력해주세요: ");
        String name = sc.nextLine();

        Product target = findProductByName(category, name);
        if (target == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.\n");
            return;
        }

        System.out.printf("삭제할 상품: %s | %,d원 | %s | 재고: %d개%n",
                target.getName(), target.getPrice(), target.getDescription(), target.getStock());
        System.out.println("정말 삭제하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
        System.out.print("> ");
        int confirm = sc.nextInt();

        if (confirm == 1) {
            category.getProducts().remove(target);
            cart.removeProduct(target);
            System.out.println("상품이 삭제되었습니다.\n");
        } else {
            System.out.println("취소되었습니다.\n");
        }
    }

    private void printAllProducts() {
        System.out.println("[ 전체 상품 현황 ]");
        for (Category c : categories) {
            System.out.println();
            System.out.println("[ " + c.getName() + " ]");
            List<Product> products = c.getProducts();
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                System.out.printf("%d. %s | %,d원 | %s | 재고: %d개%n",
                        i + 1, p.getName(), p.getPrice(), p.getDescription(), p.getStock());
            }
        }
        System.out.println();
    }

    private Category selectCategory() {
        System.out.println("어느 카테고리에 적용하시겠습니까?");
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
        }
        System.out.println("0. 취소");
        System.out.print("> ");

        int input = sc.nextInt();
        if (input == 0) {
            System.out.println("취소되었습니다.\n");
            return null;
        }

        if (input < 1 || input > categories.size()) {
            System.out.println("잘못된 입력입니다.\n");
            return null;
        }

        return categories.get(input - 1);
    }

    private Product findProductByName(Category category, String name) {
        for (Product p : category.getProducts()) {
            if (p.getName().equalsIgnoreCase(name.trim())) {
                return p;
            }
        }
        return null;
    }
}
