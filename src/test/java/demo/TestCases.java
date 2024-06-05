package demo;
import demo.WrapperActions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Setup WebDriverManager for Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
    
    //change to beforeTest when clear() works for flipkart searchbox 
    @BeforeMethod
    public void openBrowser() throws InterruptedException {
        // Open a website
        driver.get("https://www.flipkart.com/");
        Thread.sleep(3000);
    }

     //Handle the pop-up manually
    @Test(description = "Search Washing Machine. Sort by popularity and print the count of items with rating less than or equal to 4 stars")
    public void washingMachineRatings (){
        WrapperActionsClass.sendKeysWrapper(driver,By.xpath("//input[@name='q']"),"Washing Machine");

        // Sort By Popularity
        WrapperActionsClass.clickElementWrapper(driver, By.xpath("//div[text()='Popularity']"));
        
        int count=0; 
        List<WebElement> washingMachinesRatings = WrapperActionsClass.listOfElements(driver, By.xpath("//span[starts-with(@id,'productRating')]"));
        // Print the Number of Items with Rating Less than or Equal to 4 Stars
        for(WebElement washingMachineRating : washingMachinesRatings){
            String rating = washingMachineRating.getText();
            if(Double.parseDouble(rating)<=4.0)
                count++;
        }
        System.out.println("Washing machines with rating less than or equal to 4 stars : "+count);
    }

    //Handle the pop-up manually
    @Test(description = "Search “iPhone”, print the Titles and discount % of items with more than 17% discount")
    public void iphoneDiscounts(){

        WrapperActionsClass.sendKeysWrapper(driver,By.xpath("//input[@name='q']") , "iPhone");
    
        List<WebElement> phoneDiscounts = WrapperActionsClass.listOfElements(driver, By.xpath("//div[@class='UkUFwK']/span[contains(text(), '% off')]"));
        List<WebElement> phoneTitles = WrapperActionsClass.listOfElements(driver, By.xpath("//div[@class='KzDlHZ']"));
      
        System.out.println("iPhones with more than 17% Discount");
        if(phoneTitles.size()!=phoneDiscounts.size()){
            System.out.println("Mismatch in lists");
        }
        else{
            // Print titles with more than 17% Discount
            for(int i=0; i<phoneDiscounts.size();i++){
                String discount = phoneDiscounts.get(i).getText();
                discount = discount.replace("% off", "").trim();
                if(Double.parseDouble(discount)>17.0){
                    System.out.println(phoneTitles.get(i).getText()+" "+phoneDiscounts.get(i).getText());
                }
            }
        }
        
    }

     //Handle the pop-up manually
    @Test(description = "Search “Coffee Mug”, select 4 stars and above, and print the Title and image URL of the 5 items with highest number of reviews")
    public void coffeeMugs(){
        WrapperActionsClass.sendKeysWrapper(driver,By.xpath("//input[@name='q']") , "Coffee Mug");

        //Select 4 stars and above
        WrapperActionsClass.clickElementWrapper(driver, By.xpath("//div[(text()='4★ & above')]"));

        List<WebElement> productElements = WrapperActionsClass.listOfElements(driver, By.xpath("//div[@class='slAVV4']"));
        List<Product> products = new ArrayList<>();

        //store product details in a list
        for(WebElement productElement : productElements){
            String title = productElement.findElement(By.xpath(".//a[2]")).getText();
            String imageUrl = productElement.findElement(By.xpath(".//a[1]")).getAttribute("href");
            int reviewCount = Integer.parseInt(productElement.findElement(By.xpath(".//span[@class='Wphh3N']")).getText().replaceAll("[^\\d]", ""));
            Product product = new Product(title, imageUrl,reviewCount );

            products.add(product);
        }

        //sort list according to reviews
        products.sort(Collections.reverseOrder(Comparator.comparingInt(Product::getReviewCount)));

        //print Top 5 Coffee Mugs with Highest Reviews
        System.out.println("Top 5 Coffee Mugs with Highest Reviews:");
        for(int i=0; i<5 && i<products.size(); i++){
            Product p = products.get(i);
            System.out.print("Review Counts = "+p.getReviewCount());
            System.out.print(" ;Title = "+p.getTitle());
            System.out.print(" ;Image Url = "+p.getImageUrl());
            System.out.println(" ");
            System.out.println("------");

        }
    }


    
}

