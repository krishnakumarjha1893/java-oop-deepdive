
//this is a example and not made by me i jjust copy this for my Example

// 1. Interface for special flying capability
interface Flyable {
    void fly();
}

// 2. Abstract Class for common superhero traits
abstract class Superhero {
    String name;
    int health;

    public Superhero(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // Concrete method
    public void displayInfo() {
        System.out.println("Hero: " + name + " | Health: " + health);
    }

    // Abstract method (Har hero ka attack karne ka tareeqa alag hoga)
    abstract void attack();
}

// 3. Concrete Class 1: Iron Man (Uses Abstract Class + Interface)
class IronMan extends Superhero implements Flyable {
    
    public IronMan() {
        super("Iron Man", 100);
    }

    @Override
    void attack() {
        System.out.println(name + " shoots Repulsor Rays! 🚀");
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying using Suit Thrusters! ✈️");
    }
}

// 4. Concrete Class 2: Hulk (Only uses Abstract Class, cannot fly)
class Hulk extends Superhero {
    
    public Hulk() {
        super("Hulk", 150);
    }

    @Override
    void attack() {
        System.out.println(name + " does a Smash! 💥");
    }
}

// 5. Main Class to run the project
public class Main {
    public static void main(String[] args) {
        IronMan ironMan = new IronMan();
        ironMan.displayInfo();
        ironMan.fly();
        ironMan.attack();

        System.out.println("-------------------");

        Hulk hulk = new Hulk();
        hulk.displayInfo();
        hulk.attack();
        // hulk.fly(); -> Error dega kyunki Hulk Flyable interface implement nahi karta!
    }
}
