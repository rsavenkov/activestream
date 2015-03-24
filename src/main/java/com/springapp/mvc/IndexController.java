package com.springapp.mvc;

import com.springapp.mvc.wsdl.AuthResponse;
import com.springapp.mvc.wsdl.GetCityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

	@Autowired
	private ServiceClient client;

	@RequestMapping("/")
	public String get() {
		return "index";
	}

	@RequestMapping("getCities")
	@ResponseBody
	public Map<String, Object> getCities() {
		try {
			AuthResponse authResult = client.authorization();
			String session = authResult.getSession();
			final GetCityResponse result = client.getCities(session);
			return new HashMap() {{ put("result", result.getCity());}};
		} catch (final Exception e) {
			return new HashMap() {{ put("error", e.getLocalizedMessage());}};
		}
	}

}