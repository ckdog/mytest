package com.i2.pojo;

public class Book {
	private String id;
	private String name;
	private String pic;
	private String price;
	private String desc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", pic=" + pic + ", price=" + price + ", desc=" + desc + "]";
	}
	
	
	
	
	
}
