package com.houyuli.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private BigDecimal price;
	private double bfb;

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", bfb=" + bfb + "]";
	}

	public Goods(Integer id, String name, BigDecimal price, double bfb) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.bfb = bfb;
	}

	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public double getBfb() {
		return bfb;
	}

	public void setBfb(double bfb) {
		this.bfb = bfb;
	}

}
