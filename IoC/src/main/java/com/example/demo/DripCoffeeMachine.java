package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary 애노테이션 이용
//@Component
//@Primary

//@Qualifier 애노테이션 이용
@Component("dripCoffeeMachine")
public class DripCoffeeMachine implements CoffeeMachine {
    @Override
    public String brew() {
        return "Brewing coffee with Drip Coffee Machine";
    }
}
