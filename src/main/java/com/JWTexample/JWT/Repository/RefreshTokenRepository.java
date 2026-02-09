package com.JWTexample.JWT.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JWTexample.JWT.Entity.RefreshToken;
import com.JWTexample.JWT.Entity.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}

