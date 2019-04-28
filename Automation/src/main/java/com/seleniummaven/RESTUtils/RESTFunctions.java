package com.seleniummaven.RESTUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.jetty.http.HttpGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import com.seleniummaven.Constants.AppConstants;

public class RESTFunctions 
{
	String jsonSample;
	
	public void getMethod(String URL)
	{
		try
		{
			HttpClient httpclient = HttpClientBuilder.create().build();
			HttpGet httpget = new HttpGet(URL);
			httpget.addHeader("Content-Type", "application/json");
			HttpResponse httpresponse = httpclient.execute(httpget);
			String line="";
			StringBuffer buffer = new StringBuffer();
			if(httpresponse.getStatusLine().getStatusCode()==200)
			{
				BufferedReader br = new BufferedReader
						(new InputStreamReader(httpresponse.getEntity().getContent()));
				while((line=br.readLine())!=null)
				{
					buffer.append(line);
				}
				System.out.println("Response : "+buffer);
				
				testJSONParsing(buffer.toString());
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testJSONParsing(String buffer)
	{
		try
		{
			jsonSample=buffer;
			//Pass the response message of GetUsers as String to JSONObject class
			JSONObject jsonObject = new JSONObject(jsonSample);
			
			//To get the value by sending the name
			String totalPages = jsonObject.getString("total_pages");
			System.out.println("Total pages : "+totalPages);
			
			//To retrive the value using both Object and name
			//String data = jsonObject.getJSONObject("per_page").getString("id");
			//System.out.println("Value : "+data);
			
			//To retrive values from JSON array by passing JSON array name
			JSONArray arr = jsonObject.getJSONArray("data");
			System.out.println("Length of JSON Array : "+arr.length());
			for (int i = 0; i < arr.length(); i++) {
	            String firstName = arr.getJSONObject(i).getString("first_name");
	            System.out.println(firstName);
	        }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void getSingleUser()
	{
		String URL=AppConstants.getSingleUser;
		getMethod(URL);
	}
	
	public void getUsers()
	{
		String URL=AppConstants.getUsers;
		getMethod(URL);
	}

}
