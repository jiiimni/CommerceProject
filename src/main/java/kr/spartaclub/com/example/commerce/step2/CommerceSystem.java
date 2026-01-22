package kr.spartaclub.com.example.commerce.step2;

import kr.spartaclub.com.example.commerce.Product;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private final List<Product> products;
    private final Scanner sc = new Scanner(System.in);

    // 생성자: Main에서 만든 상품 리스트를 전달받음
    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    // 프로그램 시작 메서드
    public void start() {
        while (true) {
            printMenu();
            int input = sc.nextInt();

            if (input == 0) {
                System.out.println();
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            handleInput(input);
        }
    }

    // 메뉴 출력
    private void printMenu() {
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%d. %-12s | %,d원 | %s%n",
                    i + 1, p.getName(), p.getPrice(), p.getDescription());
        }

        System.out.println("0. 종료           | 프로그램 종료");
        System.out.print("> ");
    }

    // 입력값 처리
    private void handleInput(int input) {
        if (input >= 1 && input <= products.size()) {
            Product selected = products.get(input - 1);

            System.out.println();
            System.out.printf(
                    "선택한 상품: %s | %,d원 | %s | 재고: %d개%n%n",
                    selected.getName(),
                    selected.getPrice(),
                    selected.getDescription(),
                    selected.getStock()
            );
        } else {
            System.out.println();
            System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
        }
    }
}
