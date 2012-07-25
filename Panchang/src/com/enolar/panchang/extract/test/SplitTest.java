package com.enolar.panchang.extract.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import org.junit.Test;

import com.enolar.panchang.vo.DailyPanchang;
import java.sql.PreparedStatement;

public class SplitTest {

	String testString = "Wednesday, 01 February 2012<br/><b>Sunrise/Set:</b> 07:16:58/17:28:38<br/><b>Moonrise:</b> 11:57:01<br/><b>Shaka:</b> 1933 Khara<br/><span class='mini'><a href='http://www.mypanchang.com/uttarynadakshinayana.php' target='_blank'>Ayana:Uttarayana </a><br/><a href='http://www.mypanchang.com/ritus.php' target='_blank'>Ritu:Shishira<br/>Vedic Ritu:Shishira</a></span><br/>Thai<br/><b>  Magha / Shukla Paksha</b><br/><b>Tithi:</b> Navami till 14:58:04<br/><b>Nakshtra:</b> Krittika  till 22:18:02<br/><b>Yoga:</b> Brahma till 30:54:25+ <br/><b>Karana:</b> Kaulava  till 14:58:04<br/><b>Karana:</b> Taitila  till 28:03:35+<br/><b>Rahukalam:</b> 12:22:48-13:39:16<br/><b>Yamagandam:</b> 08:33:25-09:49:53<br/><b>Gulikai:</b> 11:06:21-12:22:48<br/><b>Abhijit:</b> none<br/><b>Durmuhurtam:</b> 12:02:25-12:43:12<br/><b>Varjyam:</b> 08:53:19-10:40:36<br/><b>Amritkalam:</b> 19:37:05-21:24:23<br/><b>Sun in</b>: Makara       <br/><b>Moon in</b>: Vrishabha  at  02:12:52<br/><b>Tamil Yoga:</b> Amrita 22:18:02<br/><b>Tamil Yoga:</b> Marana <br/><b>Anandadi Yoga:</b> Siddha 22:18:02<br/><b>Anandadi Yoga:</b> Subha <br/>(C) <a href='http://www.mypanchang.com' target='_blank'>mypanchang.com</a> Past midnight hours greater than 24.00";

	@Test
	public void testSplit() {
		Pattern pattern = Pattern.compile(Pattern.quote("<br/>"));
		String[] data = pattern.split(testString);
		System.out.println(Arrays.toString(data));
		System.out.println("Array length = " + data.length);
		assertEquals("Date is not equal", "Wednesday, 01 February 2012", data[0]);
		assertEquals("Sunrise/Sunset is not equal", "<b>Sunrise/Set:</b> 07:16:58/17:28:38", data[1]);
		assertEquals("Moonrise is not equal", "<b>Moonrise:</b> 11:57:01", data[2]);
		assertEquals("Shaka is not equal", "<b>Shaka:</b> 1933 Khara", data[3]);
		
		for(int i = 0; i< data.length; i++)
			System.out.println(data[i]);
	}
	
	@Test
	public void testDateConversion() {
		String dateString = "Wednesday, 05 September 2012";
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEEEEE, dd MMMMMMMMM yyyy");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date convertedDate = dateFormat.parse(dateString);
			System.out.println("Converted Date = " + convertedDate);
			System.out.println("Simple Date = " + simpleDateFormat.format(convertedDate));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDatabaseConnection() {
		try {
			Connection con = getConnection();
			String sql = createSql(getTestData(), "Prashanth");
			PreparedStatement pstmt = con.prepareStatement(sql);
			assertEquals(1, pstmt.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateSql() {
		DailyPanchang p = getTestData();
		System.out.println(createSql(p, "Prashanth"));
	}
	
	private DailyPanchang getTestData() {
		DailyPanchang p = new DailyPanchang();
		p.setAbhijitMuhurtam("10:10:10");
		p.setAmritkaalam("04:04:04");
		p.setAnandadiYoga("02:03:04");
		p.setAyana("Uttarayana");
		p.setDurmuhurtam("01:03:05");
		p.setGulikaalam("14:23:23");
		p.setKarana("karana");
		p.setMaasam("maasam");
		p.setMoonRasi("moonrasi");
		p.setMoonRiseTime("moonrisetime");
		p.setNakshatra("nakshatra");
		p.setPaksham("paksham");
		p.setPanchangDate("2012-01-01");
		p.setRahuKaalam("01:01:01");
		p.setRitu("Vasantha");
		p.setSamvatsaram("Samvatsaram");
		p.setSunRasi("Dhanus");
		p.setSunRiseTime("01:02:03");
		p.setSunSetTime("16:12:12");
		p.setTamilYoga("Siddha");
		p.setTithi("Ekadasi");
		p.setVarjyam("Varjyam");
		p.setVedicRitu("Vasnatha");
		p.setYamagandam("04:45:34");
		p.setYoga("Siddha");
		return p;
	}

	private String createSql(DailyPanchang p, String cityName) {
		String insertSql = "INSERT INTO panchangios.PANCHANG " +
				"(CITY_NAME, PANCHANG_DATE, SUNRISE_TIME, SUNSET_TIME, MOONRISE_TIME, SAMVATSARAM, AYANA, RITU, " +
				"VEDIC_RITU, MAASAM, PAKSHAM, TITHI, NAKSHATRAM, YOGA, KARANA, RAHU_KALAM, YAMAGANDAM, GULI_KALAM, " +
				"ABHIJIT_MUHURTAM, DUR_MUHURTAM, VARJYAM, AMRIT_KALAM, SUN_RASI, MOON_RASI, TAMIL_YOGA, ANANDADI_YOGA) " +
				"VALUES ('" + cityName + "', '" + p.getPanchangDate() + "', '" +  p.getSunRiseTime() + "', ' " + p.getSunSetTime() + "', '" +
				p.getMoonRiseTime()	+ "', '" + p.getSamvatsaram() + "', '" + p.getAyana() + "', '" + p.getRitu() + "', " +
				"'" + p.getVedicRitu() + "', '" + p.getMaasam() + "', '" + p.getPaksham() + "', '" + p.getTithi() + "', '" + 
				p.getNakshatra() + "', " + "'" + p.getYoga() + "', '" + p.getKarana() + "', '" + p.getRahuKaalam() + "', '" +
				p.getYamagandam() + "', '" + p.getGulikaalam() + "', " + "'" + p.getAbhijitMuhurtam() + "', '" + 
				p.getDurmuhurtam() + "', '" + p.getVarjyam() + "', '" + p.getAmritkaalam() + "', '" + p.getSunRasi() + "', '" + 
				p.getMoonRasi() + "', '" + p.getTamilYoga() + "', '" + p.getAnandadiYoga() + "');";
		System.out.println(insertSql);
		return insertSql;	}

	public Connection getConnection() throws SQLException {

	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", "panchangios");
	    connectionProps.put("password", "Friends08");

	    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	    
		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ "panchangios.db.9351585.hostedresource.com" + ":" + "3306" + "/panchangios",
				connectionProps);

	    System.out.println("Connected to database");
	    return conn;
	}

}
