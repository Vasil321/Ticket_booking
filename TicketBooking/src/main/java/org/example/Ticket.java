package org.example;

public class Ticket {
    private String row;
    private int seat;
    private int price;


    public Ticket(String row, int seat, int price) {
        this.row = row;
        this.seat = seat;
        this.price = price;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
