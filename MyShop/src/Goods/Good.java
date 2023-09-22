package Goods;

import java.util.Objects;

public class Good {
    private int id;
    private String name;
    private double price;
    private double rate;

    public Good(int id, String name, double price, double rate){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString(){
        return name + " - " + price + "$" + "(" + rate + ")" ;
    }


    @Override
    public int hashCode() {
        return (int) (32 * id * rate * price);
    }
}
