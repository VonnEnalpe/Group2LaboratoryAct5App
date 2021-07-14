package com.example.baru_app.POST_HOME;

public class Post_Model {
    private String Title;
    private String Date;
    private String Description;
    private String NumberComment;
    private String Time;
    private String Author;



    public Post_Model(){

    }




    public Post_Model(String Title, String Date, String Description, String NumberComment,String Time){
        this.Title = Title;
        this.Date = Date;
        this.Time = Time;
        this.Description = Description;
        this.NumberComment = NumberComment;
    }
    public String getAuthor() {
        return Author;
    }
    public void setAuthor(String author) {
        Author = author;
    }
    public String getTime() {
        return Time;
    }
    public void setTime(String time) {
        Time = time;
    }
    public String getNumberComment() {
        return NumberComment;
    }
    public void setNumberComment(String numberComment) {
        NumberComment = numberComment;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
