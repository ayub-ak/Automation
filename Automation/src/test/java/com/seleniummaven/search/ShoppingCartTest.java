package com.seleniummaven.search;

import org.apache.bcel.generic.NEW;
import org.testng.annotations.Test;

import com.seleniummaven.automationpractice.ShoppingCart;

public class ShoppingCartTest  
{
	ShoppingCart sc = new ShoppingCart();
	
	@Test
	public void testCartEmpty()
	{
		System.out.println("Is cart empty : "+sc.isCartEmpty());
	}
}
