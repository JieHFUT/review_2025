package com.jiehfut.iocxml.di;

/**
 * ClassName: Book
 * Package: com.jiehfut.iocxml.di
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/28 23:22
 * @Version 1.0
 */
public class Book {

    private String bname;
    private String author;
    private String nullValue;


    public Book() {
        System.out.println("无参数构造器...");
    }

    public Book(String bname, String author) {
        System.out.println("有参数构造器...");
        this.bname = bname;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", nullValue='" + nullValue + '\'' +
                '}';
    }

    // 生成这些属性的 setter && getter 方法
    public String getBname() {
        return bname;
    }
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getNullValue() {
        return nullValue;
    }
    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }
}
