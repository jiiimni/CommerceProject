package kr.spartaclub.com.example.commerce.challenge;

 // Customer 클래스
 // 고객 정보를 저장하는 객체
public class Customer {

    // 고객 이름과 이메일은 생성 후 변경되지 않으므로 final
    private final String name;
    private final String email;

    // 고객 등급과 누적 주문 금액
    private String grade;
    private int totalOrderAmount;

     // Customer 생성자
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.grade = "BRONZE";
        this.totalOrderAmount = 0;
    }

    // Getter 메서드만 제공하여 외부에서 직접 필드 수정 불가
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getGrade() { return grade; }
    public int getTotalOrderAmount() { return totalOrderAmount; }
}
