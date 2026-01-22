package kr.spartaclub.com.example.commerce.step4;

import kr.spartaclub.com.example.commerce.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 전자제품
        List<Product> electronics = new ArrayList<>();
        electronics.add(new Product("Galaxy S24", 1200000, "최신 안드로이드 스마트폰", 50));
        electronics.add(new Product("iPhone 15", 1350000, "Apple의 최신 스마트폰", 30));
        electronics.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 15));
        electronics.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 50));

        // 의류
        List<Product> clothes = new ArrayList<>();
        clothes.add(new Product("후드티", 59000, "기본 후드티", 100));
        clothes.add(new Product("청바지", 79000, "슬림핏 청바지", 80));

        // 식품
        List<Product> foods = new ArrayList<>();
        foods.add(new Product("사과", 12000, "당도 높은 사과 1kg", 40));
        foods.add(new Product("라면", 4500, "매운맛 라면 5개입", 60));

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("전자제품", electronics));
        categories.add(new Category("의류", clothes));
        categories.add(new Category("식품", foods));

        CommerceSystem system = new CommerceSystem(categories);
        system.start();
    }
}
