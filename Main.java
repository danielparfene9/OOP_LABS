package org.utm.lab0;

class Human {

    public String name;
    public int age;
    public String favourite_colour;

    public Human(String name, int age, String favourite_colour) {

        this.name = name;
        this.age = age;
        this.favourite_colour = favourite_colour;

    }

    public void humanPrint(){

        System.out.println(this.name + " is " + this.age + " y/o and likes the colour " + this.favourite_colour);

    }

}


public class Main {
    public static void main(String[] args) {

        Human H1 = new Human("Parfene Daniel", 19, "Black");
        H1.humanPrint();

    }
}