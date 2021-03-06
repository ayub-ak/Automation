package com.seleniummaven.RESTUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
	
	public void testJSONParsing(String str)
	{
		try
		{
			jsonSample=str;
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
	
	public void singleUserAPI()
	{
		try
		{
			String URL="https://reqres.in/api/users/2";
			String line;
			StringBuffer strbf = new StringBuffer();
			HttpClient httpclient = HttpClientBuilder.create().build();
			HttpGet httpget = new HttpGet(URL);
			httpget.addHeader("Content-type","application/json");
			HttpResponse httpresponse = httpclient.execute(httpget); 
			if(httpresponse.getStatusLine().getStatusCode()==200)
			{
				BufferedReader br = new BufferedReader
						(new InputStreamReader(httpresponse.getEntity().getContent()));
				//line= br.readLine();
				while((line=br.readLine())!=null)
				{
					strbf.append(line);
				}
			}
			System.out.println("Http Response message : "+strbf);
			line=strbf.toString();
			JSONObject json = new JSONObject(line);
			System.out.println("Retriving ID from JSON Object - data "+
					json.getJSONObject("data").getString("id"));
			System.out.println("Retriving first name from JSON Object - data "+
					json.getJSONObject("data").getString("first_name"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getUsersAPI()  //With parameters
	{
		try
		{
			String URL="https://reqres.in/api/users?page=2";
			String line;
			StringBuffer strbf = new StringBuffer();
			HttpClient httpclient = HttpClientBuilder.create().build();
			HttpGet httpget = new HttpGet(URL);
			httpget.addHeader("Content-type","application/json");
			HttpResponse httpresponse = httpclient.execute(httpget);
			if(httpresponse.getStatusLine().getStatusCode()==200)
			{
				BufferedReader br = new BufferedReader
						(new InputStreamReader(httpresponse.getEntity().getContent()));
				System.out.println("");
				System.out.println("");
				System.out.println("");
				while((line=br.readLine())!=null)
				{
					strbf.append(line);
				}
				System.out.println("Response : "+strbf);
			}
			else
				System.out.println("Http connection unsuccessful");
			
			line=strbf.toString();
			JSONObject json = new JSONObject(line);
			int arrayLength = json.getJSONArray("data").length();
			String firstname;
			for(int i=0;i<arrayLength;i++)
			{
				System.out.println("First name : "+ json.getJSONArray("data").getJSONObject(i).getString("first_name")
						+" of JSON Array index at : "+i);
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

public void createUserPost()
{
	try
	{
		String URL="https://reqres.in/api/users";
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(URL);
		httppost.addHeader("Content-type","application/json");
		JSONObject json = new JSONObject();
		json.put("name","morpheus");
		json.put("role","leader");
		httppost.setEntity(new StringEntity(json.toString()));
		HttpResponse httpresponse = httpclient.execute(httppost);
		if(httpresponse.getStatusLine().getStatusCode()==201)
		{
			System.out.println("New user created");
			//System.out.println("Printing the input stream : "+httpresponse.getEntity().getContent());
			BufferedReader br = new BufferedReader
					(new InputStreamReader(httpresponse.getEntity().getContent()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while((line=br.readLine())!=null)
			{
				buffer.append(line);
			}
			System.out.println("Response : "+buffer);
			line = buffer.toString();
			JSONObject result = new JSONObject(line);
			System.out.println("Name : "+result.getString("name")+" Job : "+
					result.getString("role")+" Created at : "+result.getString("createdAt"));
		}
		else
			System.out.println("User creation unsuccessful");
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	}

}
