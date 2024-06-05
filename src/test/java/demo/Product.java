package demo;

public class Product {
    private String title;
    private String imageUrl;
    private int reviewCount;

    public Product(String title, String imageUrl, int reviewCount) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.reviewCount = reviewCount;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getReviewCount() {
        return reviewCount;
    }

}
