package com.praveennittoor.covidtracker19.helper;

import java.util.HashMap;
import java.util.Map;


public class CountryToCode {

	@SuppressWarnings("serial")
	public static Map<String, String> map = new HashMap<String, String>() {{
        put("UnitedStates", "us");
        put("India", "in");
        put("Argentina", "ar");
        put("Australia", "au");
        put("Belgium", "be");
        put("Austria", "at");
        put("Brazil", "br");
        put("Bulgaria", "bg");
        put("Canada", "ca");
        put("China", "cn");
        put("Cuba", "cu");
        put("Columbia", "co");
        put("CzechRepublic", "cz");
        put("Egypt", "eg");
        put("France", "fr");
        put("Germany", "de");
        put("Greece", "gr");
        put("HongKong", "hk");
        put("Hungary", "hu");
        put("Indonesia", "id");
        put("Ireland", "ie");
        put("Israel", "il");
        put("Italy", "it");
        put("Japan", "jp");
        
        put("Latvia", "lv");
        put("Lithuania", "lt");
        put("Malaysia", "my");
        put("Mexico", "mx");
        put("Morocco", "ma");
        put("Netherlands", "nl");
        put("NewZealand", "nz");
        put("Nigeria", "ng");
        put("Norway", "no");
        put("Philippines", "ph");
        put("Poland", "pl");
        put("Portugal", "pt");
        put("Romania", "ro");
        put("Russia", "ru");
        put("SaudiArabia", "sa");
        put("Serbia", "rs");
        put("Singapore", "sg");
        put("Slovakia", "sk");
        put("Slovenia", "si");
        put("SouthAfrica", "za");
        put("SouthKorea", "kr");
        put("Sweden", "se");
        put("Switzerland", "ch");
        put("Taiwan", "tw");
        
        put("Thailand", "th");
        put("Turkey", "tr");
        put("UAE", "ae");
        put("Ukraine", "ua");
        put("UnitedKingdom", "gb");
        put("Venezuela", "ve");
    }};
    
    public String findCode(String country) {
    	return map.get(country);
    }

}
