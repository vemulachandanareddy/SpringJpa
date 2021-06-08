package com.cognizant.springlearn;
import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryNotFoundException;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);
	private static CountryService countryService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		LOGGER.info("Inside main");
		getAllCountriesTest();
		testAddCountry();
		testUpdateCountry();
		testDeleteCountry();
	}

	private static void getAllCountriesTest() {
		LOGGER.info("Start");
		Country con;
		try {
			con = countryService.findCountryByCode("GF");
			LOGGER.debug("Country:{}", con);
			LOGGER.info("End");
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void testAddCountry() {
		Country con = new Country();
		con.setCode("IN");
		con.setName("India");
		countryService.addCountry(con);
		
		Country con_check;
		try {
			con_check = countryService.findCountryByCode("IN");
			LOGGER.debug("Country:{}", con_check);
			LOGGER.info("End");
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void testUpdateCountry() {
		countryService.updateCountry("IN", "INDONESIA");
		Country con_check;
		try {
			con_check = countryService.findCountryByCode("IN");
			LOGGER.debug("Country:{}", con_check);
			LOGGER.info("End");
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void testDeleteCountry() {
		countryService.deleteCountry("IN");
		Country con_check;
		try {
			con_check = countryService.findCountryByCode("IN");
			LOGGER.debug("Country:{}", con_check);
			LOGGER.info("End");
		} catch (CountryNotFoundException e) {
			System.out.println("Country has been deleted successfully");
		}
	}

}
