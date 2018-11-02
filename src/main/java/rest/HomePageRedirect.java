package rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@RequestMapping
public class HomePageRedirect {
	
	@RequestMapping("/")
	public void home(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.sendRedirect("login.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
