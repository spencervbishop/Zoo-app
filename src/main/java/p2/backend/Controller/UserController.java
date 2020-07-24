package p2.backend.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import p2.backend.Beans.Employee;
import p2.backend.Service.EmployeeService;
import p2.backend.security.SecurityConstants;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@RequestMapping("/users")
public class UserController {
	private EmployeeService employeeService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private Logger log;

	@Autowired
	public UserController(EmployeeService employeeService ,
						  BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.employeeService = employeeService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/info")
	public @ResponseBody ResponseEntity<String> info(@RequestBody Employee user){
		Employee emp = employeeService.getByID(user.getEmployeeId());
		return new ResponseEntity<String>(emp.toString(),HttpStatus.OK);
	}

	@PostMapping("/sign-up")
	public void signUp(@RequestBody Employee user) {
		System.out.println(user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		employeeService.saveEmployee(user);

	}

	@PostMapping("/signin")
	public @ResponseBody ResponseEntity<String> signIn(@RequestBody Employee user) {
		//System.out.println(user.toString());
		Employee emp = employeeService.byUsername(user.getUsername());
		if (bCryptPasswordEncoder.matches(user.getPassword(), emp.getPassword())) {
			try {
				String role = "manager";
				Algorithm algorithmHS = Algorithm.HMAC512(SecurityConstants.SECRET);
				String token = JWT.create()
						.withAudience(role) //"Audience" is the means by which users are organized into groups
						.withIssuer("Zootropolis") //Should be the name of the site or service.
						.withSubject(String.valueOf(emp.getEmployeeId())) //Subject is the user, typically by name or ID. Here, it is name.
						.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
						.sign(algorithmHS);
				return new ResponseEntity<String>(token, HttpStatus.OK);

			} catch (UnsupportedEncodingException e) {
				log.error(e.getMessage());
			}

		}
		return new ResponseEntity<String>("There was an issue with authentication.", HttpStatus.FORBIDDEN);
	}

}
