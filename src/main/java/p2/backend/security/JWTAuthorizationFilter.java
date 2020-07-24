package p2.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import static p2.backend.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private Logger logger;

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
									HttpServletResponse res,
									FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			DecodedJWT jwt = JWT.decode(token.replace(TOKEN_PREFIX, ""));
			Algorithm algorithm = null;
			try {
				algorithm = Algorithm.HMAC512(SECRET);
			} catch (UnsupportedEncodingException e) {
			    logger.error(e.getMessage());
			}
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(jwt.getIssuer())
					.withSubject(jwt.getSubject())
					.build();
			DecodedJWT user = verifier.verify(jwt.getToken());

/*-------Old code using io.jsonwebtoken.Jwts library. Works, but only want to use one library.----*/
//			String user = Jwts.parser()
//					.setSigningKey(SECRET.getBytes())
//					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//					.getBody()
//					.getSubject();

			if (user.getSubject() != null) {
				return new UsernamePasswordAuthenticationToken(user.getSubject(), null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}
