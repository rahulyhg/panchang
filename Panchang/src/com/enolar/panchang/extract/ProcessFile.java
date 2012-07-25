package com.enolar.panchang.extract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import com.enolar.panchang.vo.DailyPanchang;

public class ProcessFile {

	private static final String YEAR = "2011";
	private static final String RESOURCES_DIRECTORY = "Resources";
	private static final String SQL_DIRECTORY = "SQL";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	
	    //Europe Cities
		String europeCities[] = { "Aberdeen-Scotland-UK", "Amsterdam-Netherlands", "Berlin-Germany", "Birmingham-England-UK", "Bonn-Germany",
				"Bremen-Germany", "Budapest-Hungary", "Copenhagen-Denmark", "Czarnow-Poland", "Dublin-Ireland", "Edinburgh-Scotland-UK",
				"Fort-de-France-Martinique", "FrankfurtamMain-Germany", "Geneva-Switzerland", "Glasgow-Scotland-UK", "Hamburg-Germany",
				"Hannover-Germany", "Helsinki-Finland", "Homburg-Saarland-Germany", "Ipswich-UK", "Istanbul-Turkey",
				"Leicester-England-UK", "Kiev-Ukraine", "London-UnitedKingdom", "Maastricht-Netherlands", "Madrid-Spain", "Malaga-Spain",
				"Manchester-UK", "Milano-Italy", "Munich-Germany", "Nuremberg-Germany", "Odense-Denmark", "Oslo-Norway",
				"Paris-France", "Plovdiv-Bulgaria", "Regensburg-Germany", "Rome-Italy", "Rotterdam-Netherlands", "Stockholm-Sweden",
				"Warsaw-Poland", "Zurich-Switzerland" };
	

		
		 //India Cities
		String indiaCities[] = { "Agra-UP", "Ahemadabad-Gujarat", "Ajmer", "Aligarh-UP", "Allahabad-UP", "Amaravati-Maharastra",
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

		
		String pakistanCities[] = {"Karachi-Pakistan", "Islamabad-Pakistan", "Lahore-Pakistan"};
		
		String middleEastCities[] = { "AbuDhabi-UAE", "Doha-Qatar", "Dubai-UAE",
				"KuwaitCity-Kuwait", "Manama-Bahrain", "Muscat-Oman",
				"Riyadh-SaudiArabia", "SanAYemen", "Sharjah-UAE", "Tehran-Iran" };

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

//		for(int i = 0; i < chinaCities.length; i++)
//			processPreProcessor(chinaCities[i], "CHINA", "ASIA");
//		for(int i = 0; i < japanCities.length; i++)
//			processPreProcessor(japanCities[i], "JAPAN", "ASIA");
//		for(int i = 0; i < caribbeanCities.length; i++)
//			processPreProcessor(caribbeanCities[i], "CARIBBEAN", "CARIBBEAN");
//		for(int i = 0; i < canadaCities.length; i++)
//			processPreProcessor(canadaCities[i], "CANADA", "NA");
//		for(int i = 0; i < pakistanCities.length; i++)
//			processPreProcessor(pakistanCities[i], "PAKISTAN", "ASIA");
//		for(int i = 0; i < middleEastCities.length; i++)
//			processPreProcessor(middleEastCities[i], "MIDDLE EAST", "MIDDLE EAST");
        for(int i = 0; i < europeCities.length; i++)
			processPreProcessor(europeCities[i], "EUROPE", "EUROPE");
//		for(int i = 0; i < cities.length; i++)
//			processPreProcessor(cities[i], "USA", "NA");
		for(int i = 0; i < indiaCities.length; i++)
			processPreProcessor(indiaCities[i], "INDIA", "ASIA");
	}
	
	private static void processPreProcessor(String cityName, String countryName, String continentName) {
		String fileName = "";

		int monthCount = 1;
		while(monthCount <= 12) {
			String monthCountString;
			if(monthCount < 10)
				monthCountString = "0" + monthCount;
			else
				monthCountString = String.valueOf(monthCount);

			fileName = monthCountString + YEAR + ".txt";
			try {
				BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_DIRECTORY + "/" + countryName + "/" + cityName + "/" + fileName));
				String line = "";
				StringBuffer sb = new StringBuffer("");
				DailyPanchang  p = null;
				String sunriseSunset = "";
				
				while ((line = reader.readLine()) != null) {
					sb = new StringBuffer();
					sb.append(line.replaceFirst("<description>", "")
							.replaceFirst("</description>", "")
							.replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
					Pattern pattern = Pattern.compile(Pattern.quote("<br/>"));
					String[] data = pattern.split(sb.toString());
	//				System.out.println(Arrays.toString(data));
					p = new DailyPanchang();
					p.setPanchangDate(data[0].trim());
					sunriseSunset = data[1].replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("Sunrise/Set:", "").trim();
					pattern = Pattern.compile(Pattern.quote("/"));
					p.setSunRiseTime(pattern.split(sunriseSunset)[0].trim());
					p.setSunSetTime(pattern.split(sunriseSunset)[1].trim());
					p.setMoonRiseTime(data[2].replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("Moonrise:", "").trim());
					p.setSamvatsaram(data[3].replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("Shaka:", "").trim());
					p.setAyana(data[4].replaceFirst("<span class='mini'><a href='http://www.mypanchang.com/uttarynadakshinayana.php' target='_blank'>", "").replaceFirst("</a>", "").replaceAll("Ayana:", "").trim());
					p.setRitu(data[5].replaceFirst("<a href='http://www.mypanchang.com/ritus.php' target='_blank'>", "").replaceAll("Ritu:", "").trim());
					p.setVedicRitu(data[6].replaceFirst("</a>", "").replaceFirst("</span>", "").replaceAll("Vedic Ritu:", "").trim());
					p.setMaasam(data[7].trim());
					p.setPaksham(data[8].replaceAll("<b>", "").replaceAll("</b>", "").trim());
					p.setTithi(parseTithi(data));
					p.setNakshatra(parseNakshatra(data));
					p.setYoga(parseYoga(data));
					p.setKarana(parseKarana(data));
					p.setRahuKaalam(parseRahuKaalam(data).replaceAll("-", " to "));
					p.setYamagandam(parseYamagandam(data).replaceAll("-", " to "));
					p.setGulikaalam(parseGulikaalam(data).replaceAll("-", " to "));
					p.setAbhijitMuhurtam(parseAbhijitMuhurtam(data).replaceAll("-", " to "));
					p.setDurmuhurtam(parseDurmuhurtam(data).replaceAll("-", " to "));
					p.setVarjyam(parseVarjyam(data).replaceAll("-", " to "));
					p.setAmritkaalam(parseAmritkaalam(data).replaceAll("-", " to "));
					p.setSunRasi(parseSunRasi(data));
					p.setMoonRasi(parseMoonRasi(data));
					p.setTamilYoga(parseTamilYoga(data));
					p.setAnandadiYoga(parseAnandadiYoga(data));
					writeToFile(p, cityName, countryName, continentName);
					data = null;
				}
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			monthCount++;
		}
	}
	
	private static void writeToFile(DailyPanchang p, String cityName, String countryName, String continentName) {
		String fileName = cityName + "-" + YEAR + "panchang.csv";
		String delimiter = "|";
		String dates = "";
		try {
			dates = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("EEEEEEEEE, dd MMMMMMMMM yyyy").parse(p.getPanchangDate()));
			p.setPanchangDate(dates);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(RESOURCES_DIRECTORY + "/" + countryName  + "/" + cityName + "/" + fileName, true));
			writer.write(cityName + delimiter);
			writer.write(dates + delimiter + p.getSunRiseTime() + delimiter + p.getSunSetTime() + delimiter);
			writer.write(p.getMoonRiseTime() + delimiter + p.getSamvatsaram() + delimiter + p.getAyana() + delimiter);
			writer.write(p.getRitu() + delimiter + p.getVedicRitu() + delimiter + p.getMaasam() + delimiter);
			writer.write(p.getPaksham() + delimiter + p.getTithi() + delimiter + p.getNakshatra() + delimiter);
			writer.write(p.getYoga() + delimiter + p.getKarana() + delimiter + p.getRahuKaalam() + delimiter);
			writer.write(p.getYamagandam() + delimiter + p.getGulikaalam() + delimiter + p.getAbhijitMuhurtam() + delimiter);
			writer.write(p.getDurmuhurtam() + delimiter + p.getVarjyam() + delimiter + p.getAmritkaalam() + delimiter);
			writer.write(p.getSunRasi() + delimiter + p.getMoonRasi() + delimiter + p.getTamilYoga() + delimiter);
			writer.write(p.getAnandadiYoga());
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		addToDatabase(p, cityName);
		createSqlBatchFile(p, cityName, countryName, continentName);
	}

	private static void createSqlBatchFile(DailyPanchang p, String cityName, String countryName, String continentName) {
		String fileName = countryName + "-" + YEAR + "panchangINSERT.sql";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(SQL_DIRECTORY  + "/"  + fileName, true));
			String sql = createSqlString(p, cityName, countryName, continentName);
			writer.write(sql);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String createSqlString(DailyPanchang p, String cityName, String countryName, String continentName) {
		String insertSql = "INSERT INTO panchangios.PANCHANG " +
				"(CONTINENT_NAME, COUNTRY_NAME, CITY_NAME, PANCHANG_DATE, SUNRISE_TIME, SUNSET_TIME, MOONRISE_TIME, SAMVATSARAM, AYANA, RITU, " +
				"VEDIC_RITU, MAASAM, PAKSHAM, TITHI, NAKSHATRAM, YOGA, KARANA, RAHU_KALAM, YAMAGANDAM, GULI_KALAM, " +
				"ABHIJIT_MUHURTAM, DUR_MUHURTAM, VARJYAM, AMRIT_KALAM, SUN_RASI, MOON_RASI, TAMIL_YOGA, ANANDADI_YOGA) " +
				"VALUES ('" + continentName + "','" + countryName + "','" + cityName + "', '" + p.getPanchangDate() + "', '" +  p.getSunRiseTime() + "', ' " + p.getSunSetTime() + "', '" +
				p.getMoonRiseTime()	+ "', '" + p.getSamvatsaram() + "', '" + p.getAyana() + "', '" + p.getRitu() + "', " +
				"'" + p.getVedicRitu() + "', '" + p.getMaasam() + "', '" + p.getPaksham() + "', '" + p.getTithi() + "', '" + 
				p.getNakshatra() + "', " + "'" + p.getYoga() + "', '" + p.getKarana() + "', '" + p.getRahuKaalam() + "', '" +
				p.getYamagandam() + "', '" + p.getGulikaalam() + "', " + "'" + p.getAbhijitMuhurtam() + "', '" + 
				p.getDurmuhurtam() + "', '" + p.getVarjyam() + "', '" + p.getAmritkaalam() + "', '" + p.getSunRasi() + "', '" + 
				p.getMoonRasi() + "', '" + p.getTamilYoga() + "', '" + p.getAnandadiYoga() + "');\n";
		return insertSql;
	}


	private static String parseAnandadiYoga(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Anandadi Yoga"))
				returnString.append(rawString.substring("Anandadi Yoga:".length()).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseTamilYoga(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Tamil Yoga"))
				returnString.append(rawString.substring("Tamil Yoga:".length()).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseMoonRasi(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Moon in"))
				returnString.append(rawString.substring("Moon in:".length()).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseSunRasi(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Sun in"))
				returnString.append(rawString.substring("Sun in:".length()).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseAmritkaalam(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Amritkalam"))
				returnString.append(rawString.substring("Amritkalam:".length()).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseVarjyam(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Varjyam"))
				returnString.append(rawString.substring(8).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseDurmuhurtam(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Durmuhurtam"))
				returnString.append(rawString.substring(12).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseAbhijitMuhurtam(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Abhijit"))
				returnString.append(rawString.substring(8).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseGulikaalam(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Gulikai"))
				returnString.append(rawString.substring(8).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseYamagandam(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Yamagandam"))
				returnString.append(rawString.substring(11).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseRahuKaalam(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Rahukalam"))
				returnString.append(rawString.substring(10).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseKarana(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Karana"))
				returnString.append(rawString.substring(7).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseYoga(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Yoga"))
				returnString.append(rawString.substring(5).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseNakshatra(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Nakshtra"))
				returnString.append(rawString.substring(9).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	private static String parseTithi(String[] data) {
		int i = 0;
		StringBuilder returnString = new StringBuilder();
		String rawString;
		while(i  < data.length){
			rawString = data[i].replaceAll("<b>", "").replaceAll("</b>", "");
			if(rawString.startsWith("Tithi"))
				returnString.append(rawString.substring(6).trim() + "#");
			i++;
		}
		if(returnString.length() == 0)
			returnString.append("#");
		return returnString.toString().substring(0, returnString.toString().length() - 1);
	}

	
//	private static Connection getConnection() throws SQLException {
//
//	    Connection conn = null;
//	    Properties connectionProps = new Properties();
//	    connectionProps.put("user", "panchangios");
//	    connectionProps.put("password", "Friends08");
//
//	    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//	    
//		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
//				+ "panchangios.db.9351585.hostedresource.com" + ":" + "3306" + "/panchangios",
//				connectionProps);
//
////	    System.out.println("Connected to database");
//	    return conn;
//	}
}
