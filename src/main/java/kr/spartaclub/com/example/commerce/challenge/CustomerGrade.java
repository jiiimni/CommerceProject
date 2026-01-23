package kr.spartaclub.com.example.commerce.challenge;

public enum CustomerGrade {
    BRONZE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15);

    private final int discountPercent;

    CustomerGrade(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountRate() {
        return discountPercent / 100.0;
    }
}
