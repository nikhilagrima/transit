package com.agrima.transit.models;

import android.graphics.drawable.Drawable;

public class Stock {
	
	int id;
	String imgUrl;
	String name;
	String price;
	
	Drawable image;
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	
	public Stock(String url,String n,String p) {
		this.imgUrl = url;
		this.name = n;
		this.price = p;
		
		this.id = 2;
	}
	
	public Stock(Drawable img,String n,String p) {
		this.image = img;
		this.name = n;
		this.price = p;
		
		this.id = 2;
	}
		
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Drawable getImage() {
		return image;
	}

	public void setImage(Drawable image) {
		this.image = image;
	}
	
	
	
}
