package com.revature.model;

import java.util.Objects;

// Because this is the parent-most class of all other animal types
// we want to define properties and behaviors that are universal to all animals here

public class Animal {
	
	public String name;
	public double eneryLevel;
	
	
	public Animal(String name) {
		this.name = name;
		this.eneryLevel = 100;
	}
	
	public void makeNoise() {
		System.out.println("*Generic Animal Noises*");
		
	}
		
	public void eat(Food food) {
		this.eneryLevel += food.nourishmentValue;
		System.out.println(this.name + " ate " + food.foodName
				+ " and increased its energy by "
				+ food.nourishmentValue + " . It now has an energyLevel of "
				+ this.eneryLevel);
			
	}
	

}
