package kr.spartaclub.com.example.commerce.step3;

public class Customer {
    private final String name;
    private final String email;
    private String grade;           // Step3에서는 일단 String으로, Lv3에서 Enum으로 확장 가능
    private int totalOrderAmount;   // 누적 주문 금액

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.grade = "BRONZE";
        this.totalOrderAmount = 0;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getGrade() { return grade; }
    public int getTotalOrderAmount() { return totalOrderAmount; }
}
