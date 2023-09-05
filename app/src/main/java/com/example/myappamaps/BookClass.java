package com.example.myappamaps;

public class BookClass {

    private String bookId;
    private String name;
    private double price;


    private String author;
    private String category;

    private String branch;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public BookClass(String bookId, String name, double price, String author, String category, String branch) {
        this.bookId = bookId;
        this.name = name;
        this.price = price;
        this.author = author;
        this.category = category;
        this.branch = branch;
    }






}
