// "Write the code for the scenario given below and explain the compile/run logic:

// Create an abstract class DatabaseConnector with:

// A protected String dbUrl field and a parametrized constructor.
// A concrete method logConnection(String msg) that prints "[LOG]: " + msg.
// An abstract method void connect().
// Create a child class PostgresConnector that inherits from DatabaseConnector.

// Trap Condition: If we make the constructor in DatabaseConnector private, will the child class compile or fail?  And why?"

// File: src/01_abstract_classes/DatabaseDemo.java

abstract class DataBaseConnector {
    protected String dbUrl;

    // Best Practice: Protected constructor for abstract classes because aise v koi class bina child bne to abastract ko aces nhi kr sakta mtlab family ka oerson atla b protectred
    protected DataBaseConnector(String dbUrl) {
        this.dbUrl = dbUrl;
        System.out.println("1. Base DataBaseConnector initialized with URL: " + this.dbUrl);
    }

    public void logConnection(String msg) {
        System.out.println("[ LOG ] " + msg);
    }

    public abstract void connect();
}

class PostgresConnector extends DataBaseConnector {
    private int port;

    public PostgresConnector(String dbUrl, int port) {
        super(dbUrl); // Constructor Chaining: Parent constructor pehle execute hoga
        this.port = port;
        System.out.println("2. Child PostgresConnector initialized on port: " + this.port);
    }

    @Override
    public void connect() {
        logConnection("Attempting connection to " + dbUrl + ":" + port);
        System.out.println("PostgreSQL Database Connected Successfully.");
    }
}

public class DatabaseDemo {
    public static void main(String[] args) {
        // Polymorphic reference
        DataBaseConnector db = new PostgresConnector("jdbc:postgresql://localhost", 5432);
        db.connect();
    }
}


//trap-public bna dene se to child acess hi nhi kr payega to abastract ya kisi v class ke cunstructor ko private bnana cuntructor ko accesable nhi bnata es liye sorry nhi kr sakte.