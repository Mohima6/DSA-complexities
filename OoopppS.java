package TimeAndSpace.DSA;

import java.util.*;

// Abstract class for demonstration of abstraction
abstract class Shape {
    // Abstract method
    public abstract void draw();

    // Non-abstract method
    public void display() {
        System.out.println("This is a shape.");
    }
}

// Interface for demonstration of interface implementation
interface Colorable {
    void color(String color);
}

// Base class for inheritance demonstration
class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    public void speak() {
        System.out.println(name + " makes a sound.");
    }
}

// Derived class for inheritance demonstration
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    // Overriding the speak method (Polymorphism)
    @Override
    public void speak() {
        System.out.println(name + " barks.");
    }
}

// Class for encapsulation demonstration
class Person {
    private String name;
    private int age;

    // Constructor for initialization
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter and Setter methods for encapsulation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Method to display information
    public void displayPerson() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// Derived class from Shape to implement drawing functionality
class Circle extends Shape implements Colorable {
    int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    // Implementing the abstract method of Shape class
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }

    // Implementing the color method from Colorable interface
    @Override
    public void color(String color) {
        System.out.println("Coloring the circle with " + color);
    }
}

// Demonstrating Composition
class Engine {
    public void start() {
        System.out.println("Engine starting...");
    }

    public void stop() {
        System.out.println("Engine stopping...");
    }
}

// Car class demonstrates Composition (Car "has" an Engine)
class Car {
    private Engine engine;

    public Car() {
        this.engine = new Engine(); // Car "has" an engine
    }

    public void startCar() {
        engine.start();  // Using the engine's start method
        System.out.println("Car is now running.");
    }

    public void stopCar() {
        engine.stop();  // Using the engine's stop method
        System.out.println("Car is now stopped.");
    }
}

// Demonstrating Aggregation (Department "has" employees but they exist independently)
class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("Employee Name: " + name);
    }
}

// Department class demonstrates Aggregation
class Department {
    private String departmentName;
    private List<Employee> employees;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void displayDepartmentDetails() {
        System.out.println("Department: " + departmentName);
        for (Employee employee : employees) {
            employee.display();
        }
    }
}

// Main class to take user input and demonstrate OOPS concepts
public class OoopppS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demonstrating Encapsulation
        System.out.print("Enter name of the person: ");
        String name = scanner.nextLine();
        System.out.print("Enter age of the person: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume the newline left-over
        Person person = new Person(name, age);
        person.displayPerson();

        // Demonstrating Inheritance and Polymorphism
        System.out.print("\nEnter name of the dog: ");
        String dogName = scanner.nextLine();
        Dog dog = new Dog(dogName);
        dog.speak();  // Demonstrating Polymorphism (Overriding)

        // Demonstrating Abstraction and Interfaces
        System.out.print("\nEnter radius of the circle: ");
        int radius = scanner.nextInt();
        scanner.nextLine();  // Consume the newline left-over
        Circle circle = new Circle(radius);

        circle.draw();  // Demonstrating abstraction (abstract method)
        System.out.print("Enter color for the circle: ");
        String color = scanner.nextLine();
        circle.color(color);  // Demonstrating interface implementation

        // Demonstrating Composition
        System.out.println("\nCreating a car...");
        Car car = new Car();
        car.startCar(); // Car uses Engine to start
        car.stopCar();  // Car uses Engine to stop

        // Demonstrating Aggregation
        System.out.println("\nCreating a department...");
        Department department = new Department("Software Development");
        System.out.print("Enter number of employees: ");
        int numEmployees = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        for (int i = 0; i < numEmployees; i++) {
            System.out.print("Enter employee name: ");
            String employeeName = scanner.nextLine();
            Employee employee = new Employee(employeeName);
            department.addEmployee(employee);
        }

        department.displayDepartmentDetails();

        // Closing the scanner
        scanner.close();
    }
}
