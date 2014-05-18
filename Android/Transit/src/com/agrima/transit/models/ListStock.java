package com.agrima.transit.models;

import android.graphics.drawable.Drawable;

public class ListStock {

	int id;
	
	String imgUrl;
	Drawable image;
	String color;
	String label;
	public ListStock() {
		// TODO Auto-generated constructor stub
	}
	public ListStock(String lable,String color){
		this.color = color;
		this.label = lable;
		id = 1;
	}
	public ListStock(Drawable image){
		this.image = image;
		id = 0;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Drawable getImage() {
		return image;
	}
	public void setImage(Drawable image) {
		this.image = image;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}