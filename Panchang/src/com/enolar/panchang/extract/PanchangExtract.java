package com.enolar.panchang.extract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PanchangExtract {
	private static final String YEAR = "2011";
	private static final String RESOURCES_DIRECTORY = "Resources";

	public static void main(String[] args) {
		
		//Indian Cities
		String indianCities[] = { "Agra-UP", "Ahemadabad-Gujarat", "Ajmer", "Aligarh-UP", "Allahabad-UP", "Amaravati-Maharastra",
				"Amritsar", "Anand-Gujarat-India", "Aurangabad-Maharastra", "Badrinath-Uttarakhand", "Varanasi-India", "Bangalore-India",
				"Baroda-India", "Belgaum-India", "Bharuch-India", "Bhatinda-India", "Bhavnagar-Gujarat-India", "Bhopal-India",
				"Bhubaneswar-India", "Bhuj-India", "Bikaner", "Bilaspur-Chhattisgarh-India", "Mumbai-Maharastra-India",
				"Chandigarh-India", "Chennai-India", "Coimbatore-India", "Kolkata-India", "Dakor-Kheda-Gujarat", "Darjiling-WestBengal", 
				"DehraDun", "Deoria-UP-India", "Dharmapuri", "Dharwar-India", "Dwarka-India", "Ernakulam-India", "Gangotri-Uttarakhand", "Gauhati-India",
				"Gaya(HolyGayaJi)-Bihar", "Ghaziabad-UP", "Godhra-Gujarat", "Guntur-AndraPradesh-India", "Gurgaon-Haryana",
				"Haridwar-Uttarakhand", "Himatnagar-Gujarat", "Hubli-India", "Hyderabad-AP-India", "Idar-Gujarat", "Imphal-Manipur",
				"Indore-India", "Jaipur", "Jaisalmer", "Jullundhur", "Jammu-Kashmir-India", "Jamnagar-India", "Jamshedpur",
				"Jhansi-UP", "Jodhpur", "Kakinada-AP-India", "Kalyan-India", "Kallakkurichchi-India", "Kanchipuram-India", "Kanpur-UP",
				"Kedarnath-Uttarakhand", "Khedabrahma-Sabarkantha-Gujarat", "Kolhapur-Maharastra", "Kota-Rajasthan", "Kumbakonam-India",
				"Kurukshetra-India", "Lucknow-India", "Ludhiana-India", "Machilipatnam-AP-India", "Madurai-India",
				"Manali-HimachalPradesh-India", "Mandi-HimachalPradesh-India", "Mangalore-Karnataka-India", "Mathura-UP", "Meerut-UP",
				"Mehsana-India", "Mysore-Karnataka-India", "Nagpur-Maharastra", "Nasik-Maharastra", "Nainital-Uttarakhand",
				"Nathdwara(LordSrinathjiTemple)-India", "Nellore-India", "NewDelhi-India", "Noida-UP", "Palakkad(Palghat)-Kerala",
				"Panaji(Panjim)-Goa", "Pandharpur-Maharastra", "Patan-Gujarat", "Patiala-Punjab-India", "Patna-Bihar-India",
				"Pondicherry-India", "Proddatur-AndhraPradesh-India", "Pune-Maharastra-India", "Puri(CityofLordJaggnath)-India",
				"PutturnearMangalore", "Raipur-Chhattisgarh", "Rajahmundry-India", "Rajkot-India", "Ranchi-Jharkhand",
				"Rishikesh-Uttarakhand", "Rohtak-Hariyana", "Roorkee-India", "Saharanpur-UP", "Sangli-Maharastra", "Satara-Maharastra",
				"Secunderabad-AndraPradesh-India", "Shimla-HimachalPradesh", "Shiradi(SaibabaTemple)-Maharastra",
				"Srirangam(Thiruvarangam)-India", "Surat-India", "Thiruvananthapuram-Kerala", "Srirangam(Thiruvarangam)-India",
				"Tirumala-AP-India", "Udaipur", "Udipi-Karnataka-India", "Ujjain-India", "Uttarkashi-Uttarakhand",
				 "Vijayawada-India", "Vishakhapatnam-India", "Zirapur-Rajgarh-MP" };
		
		
		// USA Cities
		//USA Cities
		String cities[] = { "Concord-CA", "Covina-California", "Cupertino-CA", "DiamondBar-CA", "Fremont-CA-USA", "Hayward-California",
				"Irvine-California", "Livermore-CA-USA", "LosAngeles-CA", "Malibu-CA-USA", "Milpitas-CA", "Montclair-CA",
				"MountShasta-CA", "MountainView-CA", "Napa-CA-USA", "Riverside-California", "Roseville(PlacerCounty)-CA",
				"Sacramento-CA(US)", "SanDiego-CA", "SanFrancisco-CA", "SanJose-CA", "SanMateo-CA", "SanRamon-CA", "SantaClara-CA",
				"Stockton-CA", "Sunnyvale-CA", "Waterford-CA", "Richmond-VA-USA", "Ashburn-VA", "Arlington-Virginia-USA",
				"Vienna-VA", "Birmingham-AL", "Troy-Alabama", "Anchorage-AK", "Chandler-AZ", "Phoenix-AZ", "Scottsdale-AZ", "Tuscon-AZ",
				"Fayetville-AR", "Jonesboro-AR-USA", "LittleRock-AR", "Hartford-CT", "Stamford-CT", "Wilmington-DE",
				"FortLauderdale-FL", "Jacksonville-FL", "Miami-FL", "Ocala-FL", "Orlando-FL-USA", "Stuart-FL", "Tampa-FL", "Titusville-FL",
				"Atlanta-Georgia", "Honolulu-Hawai", "Boise-Idaho", "Chicago-IL", "Peoria-IL", "Springfield-IL", "Bedford-IN",
				"Evansville-Indiana-USA", "FortWayne-IN", "Indianapolis-IN", "Mishawaka-SouthBend-Notredame-IN", "Richmond-Indiana",
				"Coralville-Iowa", "DesMoines-Iowa", "Madrid-Iowa-USA", "KansasCity-KS", "Wichita-Kansas-USA", "Lexington-KY",
				"Louisville-KY", "BatonRouge-Louisiana-USA", "NewOrleans-Louisiana-USA", "Augusta-ME", "Baltimore-MD-USA",
				"Lanham-MD", "Ashland-MA", "Boston-MA", "Marlborough-MA(US)", "Springfield-MA", "Detroit-MI", "Kalamazoo-MI",
				"Lansing-Michigan", "Novi-MI", "Minneapolis-MN", "Minnesota-MN-USA", "StPaul-MN", "Brandon-MS",
				"St.Louis-Missouri", "Helena-MT", "Omaha-NE", "LasVegas-Nevada", "Reno-NV", "Nashua-NH", "Chatham-NJ",
				"Edison-NJ", "NewJersey-NJ", "Albuquerque-NewMexico", "Gallup-NewMexico", "Albany-NY", "Buffalo-NY(US)",
				"NewYork-NY", "Rochester-NY", "Asheville-NorthCarolina-USA", "Cary-NC", "Charlotte-NC-USA", "Greensboro-NC",
				"Greenville-NorthCarolina-USA", "Raleigh-NC", "Winston-Salem-NC", "Fargo-ND", "Cincinnati-OH",
				"Cleveland-OH", "Columbus-OH", "Dayton-OH", "Mason-OH-USA", "Toledo-OH", "Stillwater-OK(US)", "Tulsa-Oklahoma",
				"Corvalis-OR", "Portland-OR-USA", "Lancaster-PA", "NewCumberland-PA-USA", "Philadelphia-PA", "Pittsburgh-PA",
				"SanJuan-PuertoRico", "Providence-RI", "Greenville-SC", "Summerville-SouthCarolina", "Sumter-SC", "Pierre-SD",
				"Chattanooga-TN(US)", "Knoxville-TN", "Memphis-TN", "Nashville-TN", "Austin-TX", "Beaumont-TX(US)", "Dallas-Texas",
				"ElPaso-TX", "Houston-Texas", "Irving-TX", "Irvington-TX", "Lubbock-TX", "Midland-Texas", "Navasota-TX", "Pearland-TX",
				"Plano-TX", "SanAntonio-TX", "Temple-TX", "SouthJordan-UT-USA", "Montpelier-VT", "Bellingham-WA-USA", "Seattle-WA-USA",
				"Spokane-WA", "Yakima-WA", "Washington-DC", "Charleston-WV", "Madison-WI", "Milwaukee-WI", "Pewaukee-Wisconsin",
				"Laramie-Wyoming" };
		
		
		//Europe Cities
		String europeCities[] = { "Aberdeen-Scotland-UK", "Amsterdam-Netherlands", "Berlin-Germany", "Birmingham-England-UK", "Bonn-Germany",
				"Bremen-Germany", "Budapest-Hungary", "Copenhagen-Denmark", "Czarnow-Poland", "Dublin-Ireland", "Edinburgh-Scotland-UK",
				"Fort-de-France-Martinique", "FrankfurtamMain-Germany", "Geneva-Switzerland", "Glasgow-Scotland-UK", "Hamburg-Germany",
				"Hannover-Germany", "Helsinki-Finland", "Homburg-Saarland-Germany", "Ipswich-UK", "Istanbul-Turkey",
				"Leicester-England-UK", "Kiev-Ukraine", "London-UnitedKingdom", "Maastricht-Netherlands", "Madrid-Spain", "Malaga-Spain",
				"Manchester-UK", "Milano-Italy", "Munich-Germany", "Nuremberg-Germany", "Odense-Denmark", "Oslo-Norway",
				"Paris-France", "Plovdiv-Bulgaria", "Regensburg-Germany", "Rome-Italy", "Rotterdam-Netherlands", "Stockholm-Sweden",
				"Warsaw-Poland", "Zurich-Switzerland" };
		
		String chinaCities[] = { "Shanghai-China", "Ulaanbaatar-Mongolia",
				"Yiwu(Zhejiang)-China", "HongKong-China" };
		String japanCities[] = {"Tokyo-Japan"};
		String caribbeanCities[] = { "Bridgetown-Barbados",
				"Georgetown-Guyana", "Kingston-Jamaica", "Nassau-Bahamas",
				"PortofSpain-TrinidadandTobago-WestIndies" };
		String canadaCities[] = { "Calgary-AB-Canada", "Edmonton-Alberta",
				"Halifax-NovaScotia-Canada", "Montreal-Quebec-Canada",
				"Ottawa-ON-Canada", "Saskatoon-Saskatchewan-Canada",
				"Scarborough-OntarioCanada", "St.Johns-Newfoundland-Canada",
				"Toronto-ON-Canada", "Vancouver-BC-Canada",
				"Victoria-BC-Canada", "Winnipeg-MB-Canada" };
		String pakistanCities[] = {"Karachi-Pakistan", "Islamabad-Pakistan", "Lahore-Pakistan"};
		
		String middleEastCities[] = { "AbuDhabi-UAE", "Doha-Qatar", "Dubai-UAE",
				"KuwaitCity-Kuwait", "Manama-Bahrain", "Muscat-Oman",
				"Riyadh-SaudiArabia", "SanAYemen", "Sharjah-UAE", "Tehran-Iran" };

		
//		for(int i = 0; i < chinaCities.length; i++)
//			extractPreProcessor(chinaCities[i], "CHINA");
//		for(int i = 0; i < japanCities.length; i++)
//			extractPreProcessor(japanCities[i], "JAPAN");
//		for(int i = 0; i < caribbeanCities.length; i++)
//			extractPreProcessor(caribbeanCities[i], "CARIBBEAN");
//		for(int i = 0; i < canadaCities.length; i++)
//			extractPreProcessor(canadaCities[i], "CANADA");
//		for(int i = 0; i < pakistanCities.length; i++)
//			extractPreProcessor(pakistanCities[i], "PAKISTAN");
//		for(int i = 0; i < middleEastCities.length; i++)
//			extractPreProcessor(middleEastCities[i], "MIDDLE EAST");
		for(int i = 0; i < europeCities.length; i++)
			extractPreProcessor(europeCities[i], "EUROPE");
//		for(int i = 0; i < cities.length; i++)
//			extractPreProcessor(cities[i], "USA");
		for(int i = 0; i < indianCities.length; i++)
			extractPreProcessor(indianCities[i], "INDIA");
	}
	
	private static void extractPreProcessor(String cityName, String countryName) {
		int dayCount = 1;
		int monthCount = 1;
		String url = "http://www.mypanchang.com/rssfeeddata.php";
		while(monthCount <= 12) {
			String monthCountString;
			String dayCountString;
			if(monthCount < 10)
				monthCountString = "0" + monthCount;
			else
				monthCountString = String.valueOf(monthCount);
			while(dayCount <= 31)
			{
				if(dayCount < 10) 
					dayCountString = "0" + dayCount;
				else
					dayCountString = String.valueOf(dayCount);
				readFromURL(url, cityName, countryName, YEAR, monthCountString , dayCountString);
				dayCount++;
			}
			dayCount = 1;
			monthCount++;
		}
	}

	private static void readFromURL(String url, String cityName, String countryName, String year, String month, String day) {
		String charset = "UTF-8";
		String query = "";
		try {
			query = String.format("cityname=%s&yr=%s&mn=%s&dt=%s",
					URLEncoder.encode(cityName, charset), URLEncoder.encode(year,
							charset), URLEncoder.encode(month, charset), URLEncoder
							.encode(day, charset));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
				HttpURLConnection connection = (HttpURLConnection) new URL(url + "?" + query).openConnection();
				InputStream inStream = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
				
				String fileName = month + year + ".txt";
				
				File cityDir = new File(RESOURCES_DIRECTORY + "/" + countryName + "/" + cityName);
				cityDir.mkdir();
				BufferedWriter writer = new BufferedWriter(new FileWriter(RESOURCES_DIRECTORY + "/" + countryName + "/" + cityName + "/" + fileName, true));
	
				String line = "";
				int lineCount = 1;
				
				while ((line = reader.readLine()) != null) {
					if(lineCount == 14) {
						writer.write(line);
						writer.newLine();
					}
					lineCount++;
				}
				reader.close();
				writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
