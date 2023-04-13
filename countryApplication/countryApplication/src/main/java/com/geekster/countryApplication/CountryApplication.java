package com.geekster.countryApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
public class CountryApplication{


	public static void main(String[] args) throws Exception {
		URL getUrl = new URL("https://api.nationalize.io/?name=nathaniel");
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();

		if(responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer jsonResponseData = new StringBuffer();
			String readLine = null;

			while ((readLine = in.readLine()) != null) {
				jsonResponseData.append(readLine);
			}
			in.close();
			JSONObject masterData =  new JSONObject(jsonResponseData.toString());
			//JSONObject weather = (JSONObject) masterData.get("current_weather");


			System.out.println("country_details : " +masterData);
		}
		else{
			System.out.println("This is not valid URL "+responseCode);
		}
	}
}