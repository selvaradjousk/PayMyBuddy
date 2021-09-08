package com.paymybuddy.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


/**
 * The Class PayMyBuddyApplication.
 * 
 * @author Senthil
 */

@EnableEncryptableProperties
@SpringBootApplication
public class PayMyBuddyApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}
}
