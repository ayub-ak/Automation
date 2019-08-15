package com.seleniummaven.RESTTest;

import org.testng.annotations.Test;

import com.seleniummaven.RESTUtils.RESTFunctions;

public class ReqresTest 
{
	RESTFunctions rest = new RESTFunctions();
	
	@Test
	public void testGetSingleUser()
	{
		rest.getSingleUser();
	}
	
	@Test
	public void getMultiUsers()
	{
		rest.getUsers();
	}
	
	@Test
	public void singleUser()
	{
		rest.singleUserAPI();
	}
	
	@Test
	public void createUser()
	{
		rest.createUserPost();
	}

}
