package com.example.demo;

public class CoffeeMaker {
    //    private EspressoMachine espressoMachine;
//    private DripCoffeeMachine dripCoffeeMachine;
    private CoffeeMachine coffeeMachine; // 인터페이스

//    public CoffeeMaker() {
        // 생성자
//        this.espressoMachine = new EspressoMachine();
//        this.dripCoffeeMachine = new DripCoffeeMachine();
        // CoffeeMaker 클래스의 espressoMachine을 생성자를 통해
        // CoffeeMaker 인스턴스가 생길 때 espresoMachine이 생성되도록 함
//    }

    public void setCoffeeMachine(CoffeeMachine coffeemachine) {
        this.coffeeMachine = coffeemachine;
    }

    public void makeCoffee() {
//        System.out.println(espressoMachine.brew());
        System.out.println(coffeeMachine.brew());
    }

}