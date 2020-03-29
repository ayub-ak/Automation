package com.testWalletHub.modules;

import org.testng.annotations.Test;

import com.wallethub.com.LoginPage;

public class Login extends LoginPage
{
	LoginPage loginpage = new LoginPage();
	
    @Test
    public void verifyLogin() 
    {
    	loginpage.loginCheck();
    }
  
}
