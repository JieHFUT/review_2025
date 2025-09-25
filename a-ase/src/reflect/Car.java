package reflect;

/**
 * ClassName: Car
 * Package: reflect
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/25 16:36
 * @Version 1.0
 */
public class Car {

    private String brand;
    private int year;
    private String color;

    // 无参构造
    public Car(){}
    // 有参数构造
    public Car(String brand, int year, String color) {
        this.brand = brand;
        this.year = year;
        this.color = color;
    }

    // 私有的方法
    private void run() {
        System.out.println("private car run...............................");
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}