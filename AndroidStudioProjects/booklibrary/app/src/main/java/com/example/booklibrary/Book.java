package com.example.booklibrary;

public class Book {
    private int id;
    private String name;
    private String Author;
    private int pages;
    private String imgageUrl;
    private String shortDesc;
    private String longDesc;

    public Book(int id, String name, String author, int pages, String imgageUrl, String shortDesc, String longDesc) {
        this.id = id;
        this.name = name;
        Author = author;
        this.pages = pages;
        this.imgageUrl = imgageUrl;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getImgageUrl() {
        return imgageUrl;
    }

    public void setImgageUrl(String imgageUrl) {
        this.imgageUrl = imgageUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Author='" + Author + '\'' +
                ", pages=" + pages +
                ", imgageUrl='" + imgageUrl + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                '}';
    }
}
