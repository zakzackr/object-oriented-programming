class Animal {
    public void animalEat() {
        System.out.println("The animal is eating...");
    }
}

class Cat extends Animal {
    public void animalEat() {
        System.out.println("The cat is eating cat food...");
        }
}

class Dog extends Animal {
    public void animalEat() {
        System.out.println("The dog is eating dog food...");
    }
}

class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();  // Create a Animal object
        // polymorphism (run-time polymorphism)
        // The cat and dog objects are instances of the Animal class but they are actually instances of the Cat and Dog subclasses.
        // When we call the animalEat() method on these objects, the correct implementation is called based on the actual type of the object (Cat and Dog).
        Animal cat = new Cat();  // Create a Cat object
        Animal dog = new Dog();  // Create a Dog object
        animal.animalEat();
        cat.animalEat();
        dog.animalEat();
    }
}
