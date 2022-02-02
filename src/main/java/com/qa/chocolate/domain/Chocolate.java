package com.qa.chocolate.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // marks as table
public class Chocolate {

	@Id // marks as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
	private Long id;// get javax import

	private String name;

	private String brand;

	private String type;

	private int tastiness;

	private String texture;

	private int sugarContent;
	
	//------------------------------------------
	// cadbury cadbury milk 100 chocolatey? 80 - instance 1
	// aero aero milk 100 chocolatey? 80 - instance 2
	// Chocolate a = new Chocolate(cadbury cadbury milk 100 chocolatey? 80); - instance creation
	//------------------------------------------

	// right click - source - generate constructor using fields - deselect all
	// default constructor - best practice
	public Chocolate() {
		super();
		// Default constructor
		// REQUIRED
	}

	// right click - source - generate constructor using fields - select all, not id
	// constructor with everything except ID - format to pass through to the DB
	public Chocolate(String name, String brand, String type, int tastiness, String texture, int sugarContent) {
		super();
		this.name = name;
		this.brand = brand;
		this.type = type;
		this.tastiness = tastiness;
		this.texture = texture;
		this.sugarContent = sugarContent;
	}

	// right click - source - generate constructor using fields - select all
	// constructor with everything - format received from the DB
	public Chocolate(Long id, String name, String brand, String type, int tastiness, String texture, int sugarContent) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.type = type;
		this.tastiness = tastiness;
		this.texture = texture;
		this.sugarContent = sugarContent;
	}

	// GETTERS AND SETTERS REQUIRED
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTastiness() {
		return tastiness;
	}

	public void setTastiness(int tastiness) {
		this.tastiness = tastiness;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public int getSugarContent() {
		return sugarContent;
	}

	public void setSugarContent(int sugarContent) {
		this.sugarContent = sugarContent;
	}

	// EQUALS AND HASHCODE METHODS REQUIRED (especially for testing)
	// - methods are provided by the object class for comparing objects

	// This method returns an int value which references the objects memory address
	// from the hash table
	@Override
	public int hashCode() {
		return Objects.hash(brand, id, name, sugarContent, tastiness, texture, type);
	}

	// This method compares objects which are equivalent but not literally the same
	// Compares the individual attributes of the object rather than the memory location
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chocolate other = (Chocolate) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& sugarContent == other.sugarContent && tastiness == other.tastiness
				&& Objects.equals(texture, other.texture) && Objects.equals(type, other.type);
	}

	// TOSTRING METHOD
	@Override
	public String toString() {
		return "Chocolate [id=" + id + ", name=" + name + ", brand=" + brand + ", type=" + type + ", tastiness="
				+ tastiness + ", texture=" + texture + ", sugarContent=" + sugarContent + "]";
	}

}
