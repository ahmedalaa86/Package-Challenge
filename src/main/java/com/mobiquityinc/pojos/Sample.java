package com.mobiquityinc.pojos;

import java.util.ArrayList;
import java.util.List;

public class Sample {
	Integer maxWeight;
	List<Thing> things = new ArrayList<>();
	
	public Integer getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(Integer maxWeight) {
		this.maxWeight = maxWeight;
	}
	public List<Thing> getThings() {
		return things;
	}
	public void setThings(List<Thing> things) {
		this.things = things;
	}
	
	
}
