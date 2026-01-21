package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 50));
        products.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 30));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 15));
        products.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 50));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                System.out.printf("%d. %-12s | %,d원 | %s%n",
                        i + 1, p.getName(), p.getPrice(), p.getDescription());
            }
            System.out.println("0. 종료           | 프로그램 종료");

            System.out.print("> ");
            int input = sc.nextInt();

            if (input == 0) {
                System.out.println();
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            // 1~4만 정상 범위
            if (input >= 1 && input <= products.size()) {
                Product selected = products.get(input - 1);
                System.out.println();
                System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개%n%n",
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

        sc.close();
    }
}
