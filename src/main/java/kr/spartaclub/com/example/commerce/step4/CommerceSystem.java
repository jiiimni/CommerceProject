package kr.spartaclub.com.example.commerce.step4;

import kr.spartaclub.com.example.commerce.Product;

import java.util.List;
import java.util.Scanner;

 //CommerceSystem 클래스
 // 프로그램 전체 흐름을 제어하는 클래스 - 메뉴 출력, 사용자 입력 처리, 반복/종료 제어를 담당한다.
public class CommerceSystem {

    // 여러 카테고리를 관리하는 리스트
    private final List<Category> categories;

    // 사용자 입력을 받기 위한 Scanner
    private final Scanner sc = new Scanner(System.in);

    //CommerceSystem 생성자
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    //프로그램 시작 메서드. 메인 메뉴를 반복 출력 - 0이 들어오면 프로그램 종료
    public void start() {
        while (true) {
            printMainMenu();
            int input = sc.nextInt();

            // 종료 처리
            if (input == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            // 정상 범위 입력 시 카테고리 선택
            if (input >= 1 && input <= categories.size()) {
                Category selectedCategory = categories.get(input - 1);
                showCategoryMenu(selectedCategory);
            } else {
                // 잘못된 입력 처리
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }

    //메인 메뉴 출력카테고리 목록을 번호와 함께 출력

    private void printMainMenu() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
        }

        System.out.println("0. 종료      | 프로그램 종료");
        System.out.print("> ");
    }

    // 선택한 카테고리의 상품 목록을 출력하고 상품 선택 또는 뒤로가기를 처리하는 메서드
    private void showCategoryMenu(Category category) {
        while (true) {
            System.out.println();
            System.out.printf("[ %s 카테고리 ]%n", category.getName());

            // 카테고리로부터 상품 목록 조회
            List<Product> products = category.getProducts();

            // 상품 목록 출력
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                System.out.printf("%d. %-12s | %,d원 | %s%n",
                        i + 1, p.getName(), p.getPrice(), p.getDescription());
            }

            System.out.println("0. 뒤로가기");
            System.out.print("> ");
            int input = sc.nextInt();

            // 뒤로가기 처리
            if (input == 0) {
                System.out.println();
                return; // 메인 메뉴로 복귀
            }

            // 상품 선택 처리
            if (input >= 1 && input <= products.size()) {
                Product selected = products.get(input - 1);
                System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개%n%n",
                        selected.getName(),
                        selected.getPrice(),
                        selected.getDescription(),
                        selected.getStock());
            } else {
                // 잘못된 입력 처리
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }
}
