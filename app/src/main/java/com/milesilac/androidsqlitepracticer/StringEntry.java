package com.milesilac.androidsqlitepracticer;

public class StringEntry {

    private int id;
    private String date;
    private String text;

    public StringEntry(int id, String date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public StringEntry() {
    }

    @Override
    public String toString() {
        return "StringEntry{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
