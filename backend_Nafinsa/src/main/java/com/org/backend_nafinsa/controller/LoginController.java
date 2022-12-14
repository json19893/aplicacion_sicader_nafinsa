package com.org.backend_nafinsa.controller;

import com.org.backend_nafinsa.dto.UsuarioToken;
import com.org.backend_nafinsa.util.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/sicader/acceso")
public class LoginController {

    @PostMapping("/login")
    public UsuarioToken  login(
            @RequestParam("user") String username,
            @RequestParam("password") String pwd) {

        String token = getJWTToken(username);
        UsuarioToken user = new UsuarioToken();
        user.setUsuario(username);
        user.setToken(token);
        return user;

    }

    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("NafinsaSicaderJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_TIME_EXP))
                .signWith(SignatureAlgorithm.HS512,
                         Constants.TOKEN_SECRET.getBytes()).compact();

        return "Bearer " + token;
    }
}
