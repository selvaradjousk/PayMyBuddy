package com.paymybuddy.webapp.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The Class SecurityController.
 * @author Senthil
 */
@Controller
public class SecurityController {

	/**
	 * Error.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/notAuthorized")
	public String error(final Model model) {
		return "notAuthorized";
	}
}
