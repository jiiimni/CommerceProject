package kr.spartaclub.com.example.commerce.challenge;

/**
 * Customer 클래스
 * 고객 정보를 저장하는 객체
 */
public class Customer {

    // 고객 이름과 이메일은 생성 후 변경되지 않으므로 final
    private final String name;
    private final String email;

    // 고객 등급과 누적 주문 금액
    private CustomerGrade grade;
    private int totalOrderAmount;

    // Customer 생성자 (기본 BRONZE)
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.grade = CustomerGrade.BRONZE;
        this.totalOrderAmount = 0;
    }

    // Customer 생성자
    public Customer(String name, String email, CustomerGrade grade, int totalOrderAmount) {
        this.name = name;
        this.email = email;
        this.grade = (grade == null) ? CustomerGrade.BRONZE : grade;
        this.totalOrderAmount = Math.max(0, totalOrderAmount);
    }

    // Getter
    public String getName() { return name; }
    public String getEmail() { return email; }
    public CustomerGrade getGrade() { return grade; }
    public int getTotalOrderAmount() { return totalOrderAmount; }

   // 주문 금액 누적 + 등급 갱신
    public void addOrderAmount(int amount) {
        if (amount <= 0) return;
        totalOrderAmount += amount;
        updateGradeByTotal();
    }

    //누적 주문 금액 기준으로 등급 산정
    private void updateGradeByTotal() {
        if (totalOrderAmount >= 2_000_000) {
            grade = CustomerGrade.PLATINUM;
        } else if (totalOrderAmount >= 1_000_000) {
            grade = CustomerGrade.GOLD;
        } else if (totalOrderAmount >= 500_000) {
            grade = CustomerGrade.SILVER;
        } else {
            grade = CustomerGrade.BRONZE;
        }
    }
}
