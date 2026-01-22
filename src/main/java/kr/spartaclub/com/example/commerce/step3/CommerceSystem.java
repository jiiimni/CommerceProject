package kr.spartaclub.com.example.commerce.step3;

import kr.spartaclub.com.example.commerce.Product;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private final List<Category> categories;
    private final Scanner sc = new Scanner(System.in);

    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    public void start() {
        while (true) {
            printMainMenu();
            int input = sc.nextInt();

            if (input == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            if (input >= 1 && input <= categories.size()) {
                Category selectedCategory = categories.get(input - 1);
                showCategoryMenu(selectedCategory);
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
        }

        System.out.println("0. 종료      | 프로그램 종료");
        System.out.print("> ");
    }

    private void showCategoryMenu(Category category) {
        while (true) {
            System.out.println();
            System.out.printf("[ %s 카테고리 ]%n", category.getName());

            List<Product> products = category.getProducts();
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                System.out.printf("%d. %-12s | %,d원 | %s%n",
                        i + 1, p.getName(), p.getPrice(), p.getDescription());
            }

            System.out.println("0. 뒤로가기");
            System.out.print("> ");
            int input = sc.nextInt();

            if (input == 0) {
                System.out.println();
                return; // 메인으로 복귀
            }

            if (input >= 1 && input <= products.size()) {
                Product selected = products.get(input - 1);
                System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개%n%n",
                        selected.getName(),
                        selected.getPrice(),
                        selected.getDescription(),
                        selected.getStock());
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }
}
