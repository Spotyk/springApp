package ua.knucea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.knucea.domain.model.jwt.JwtRequest;
import ua.knucea.domain.model.jwt.JwtResponse;
import ua.knucea.service.UserService;
import ua.knucea.util.JwtTokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(JwtRequest authenticationRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
//        Cookie authorizationCookie = new Cookie("Authorization", token);

        //response.addHeader("Authorization", token);
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            List<Cookie> authCookie = Stream.of(cookies)
                    .filter(cookie -> cookie.getName().equals("Authorization"))
                    .collect(Collectors.toList());
            if (!authCookie.isEmpty()){
                return ResponseEntity.ok("Exists");
            }
        }
        response.addCookie(new Cookie("Authorization", "Bearer".concat(token)));
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
