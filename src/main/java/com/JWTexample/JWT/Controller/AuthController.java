package com.JWTexample.JWT.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JWTexample.JWT.Dto.AuthRequest;
import com.JWTexample.JWT.Dto.RefreshTokenRequest;
import com.JWTexample.JWT.Dto.RegisterRequest;
import com.JWTexample.JWT.Entity.RefreshToken;
import com.JWTexample.JWT.Entity.User;
import com.JWTexample.JWT.Repository.UserRepository;
import com.JWTexample.JWT.Service.RefreshTokenService;
import com.JWTexample.JWT.Utility.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    
    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

   /* @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        String token = jwtUtil.generateToken(request.getUsername());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println(authentication.getName());
    	System.out.println(authentication.getAuthorities());
    	System.out.println(authentication.isAuthenticated());

        return ResponseEntity.ok(Map.of("token", token));
    }*/
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        String accessToken = jwtUtil.generateToken(request.getUsername());
        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(request.getUsername());

        return ResponseEntity.ok(Map.of(
        		"message","Login Successfull!",
                "accessToken", accessToken,
                "refreshToken", refreshToken.getToken()
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {

        RefreshToken refreshToken =
                refreshTokenService.verifyExpiration(request.getRefreshToken());

        String newAccessToken =
                jwtUtil.generateToken(refreshToken.getUser().getUsername());

        return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken
        ));
    }
    
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody RefreshTokenRequest request) {
		refreshTokenService.logout(request.getRefreshToken());
		return ResponseEntity.ok("Logged out");
	}

}

