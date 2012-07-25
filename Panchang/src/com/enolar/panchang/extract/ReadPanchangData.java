package com.enolar.panchang.extract;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.enolar.panchang.vo.DailyPanchang;

public class ReadPanchangData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String cities[] = { "Concord-CA" };
		for(int i = 0; i < cities.length; i++)
			readPanchangData(cities[i]);
	}

	private static void readPanchangData(String cityName) {
		String resourcesDirectory = "Resources";
		String fileName = cityName + "-2012panchang.csv";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(resourcesDirectory + "/" + fileName));
			String line = "";
			DailyPanchang  p = null;
			while ((line = reader.readLine()) != null) {
				String voData[] = line.split("|", 0);
				p = new DailyPanchang();
				p.setPanchangDate(voData[0]);
				p.setSunRiseTime(voData[1]);
				p.setSunSetTime(voData[2]);
				p.setMoonRiseTime(voData[3]);
				p.setSamvatsaram(voData[4]);
				p.setAyana(voData[5]);
				p.setRitu(voData[6]);
				p.setVedicRitu(voData[7]);
				p.setMaasam(voData[8]);
				p.setPaksham(voData[9]);
				p.setTithi(voData[10]);
				p.setNakshatra(voData[11]);
				p.setYoga(voData[12]);
				p.setKarana(voData[13]);
				p.setRahuKaalam(voData[14]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
