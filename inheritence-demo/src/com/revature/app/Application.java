package com.revature.app;

import com.revature.model.Animal;
import com.revature.model.Cat;
import com.revature.model.Dog;
import com.revature.model.Food;
import com.revature.model.Lion;

public class Application {

	public static void main(String[] args) {
		Food steak = new Food("Filet Mignon", 50);
		
		Animal a1 = new Dog("Fido");
		
		a1.eat(steak);
		a1.makeNoise();
		
		Food fish = new Food(" Tilapia ", 25);
		
		a1 = new Cat("Whiskers ");
		
		a1.eat(fish);
		a1.makeNoise();
		
		a1 = new Lion("Simba");
		a1.makeNoise();
		
		Food boneMarrow = new Food("Bone Marrow " , 10);
		
		Animal a1000 = new Dog("Sparky ");
		a1000.eat(boneMarrow);
		a1000.makeNoise();
		
		
		
		if(a1000 instanceof Dog) {
			((Dog) a1000).play();
			((Dog) a1000).play("Fetch ");
		}
		
		
		
	}
}
