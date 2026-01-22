package kr.spartaclub.com.example.commerce.step1;

import kr.spartaclub.com.example.commerce.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main: 프로그램 시작 지점
 * - Step1에서는 Main에서 "상품 목록 구성 + 출력 + 입력 처리"까지 전부 담당
 * - Step2부터는 흐름 제어를 CommerceSystem 클래스로 옮길 예정
 */
public class Main {
    public static void main(String[] args) {

        // 1) 여러 상품을 담기 위한 리스트 생성
        //    List 인터페이스로 선언하면 나중에 구현체(ArrayList 등)를 바꿔도 코드 변경이 적음
        List<Product> products = new ArrayList<>();

        // 2) Product 객체 생성(new) 후 리스트에 추가
        //    -> "데이터(상품)"를 객체로 만들고, 여러 개를 List로 관리하는 것이 Step1 핵심
        products.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 50));
        products.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 30));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 15));
        products.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 50));

        // 3) 사용자 입력을 받기 위한 Scanner 준비
        Scanner sc = new Scanner(System.in);

        // 4) 프로그램은 사용자가 "0(종료)"를 입력할 때까지 반복 실행
        while (true) {

            // 4-1) 메뉴 출력
            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");

            // 4-2) 리스트에 들어있는 Product들을 순회하면서 번호 + 정보 출력
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);

                // 번호는 사람이 보기 좋게 1부터 시작하게 출력 (인덱스는 0부터지만)
                // %,d -> 숫자에 콤마(,)
                // %-12s -> 문자열 왼쪽 정렬(출력 정렬 예쁘게)
                System.out.printf("%d. %-12s | %,d원 | %s%n",
                        i + 1, p.getName(), p.getPrice(), p.getDescription());
            }

            // 4-3) 종료 메뉴
            System.out.println("0. 종료           | 프로그램 종료");

            // 5) 사용자 입력 받기
            System.out.print("> ");
            int input = sc.nextInt();

            // 6) 입력값에 따라 분기 처리
            if (input == 0) {
                System.out.println();
                System.out.println("커머스 플랫폼을 종료합니다.");
                break; // while 탈출(프로그램 종료)
            }

            // 7) 정상 범위(1~상품개수) 체크
            if (input >= 1 && input <= products.size()) {
                Product selected = products.get(input - 1); // 번호(1~) -> 인덱스(0~) 변환

                System.out.println();
                System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개%n%n",
                        selected.getName(),
                        selected.getPrice(),
                        selected.getDescription(),
                        selected.getStock()
                );
            } else {
                // 8) 예외 입력 처리
                System.out.println();
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }

        // 9) 자원 정리
        sc.close();
    }
}
