package main;

import java.util.ArrayList;
import java.util.List;


abstract class Animal {

    abstract void sleep(); 
    abstract void move();  
}


class Human extends Animal {
    @Override
    void sleep() {
        System.out.println("밤에는 잠을 잡니다.");
    }

    @Override
    void move() {
        System.out.println("사람이 두 발로 걷습니다.");
    }


    void readBook() {
        System.out.println("사람이 책을 읽습니다.");
    }
}

class Tiger extends Animal {
    @Override
    void sleep() {
        System.out.println("밤에는 잠을 잡니다.");
    }

    @Override
    void move() {
        System.out.println("호랑이가 네 발로 뜁니다.");
    }

    // 호랑이만의 추가적인 기능
    void hunt() {
        System.out.println("호랑이가 사냥을 합니다.");
    }
}


public class Quiz07 {
    public static void main(String[] args) {
     
        List<Animal> animalList = new ArrayList<>();

     
        Animal human = new Human();
        Animal tiger = new Tiger();
        animalList.add(human);
        animalList.add(tiger);

      
        for (Animal animal : animalList) {
            animal.sleep();
            animal.move();

            if (animal instanceof Human) {
                ((Human) animal).readBook();
            } else if (animal instanceof Tiger) {
                ((Tiger) animal).hunt();
            }
        }
    }
}
