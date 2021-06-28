package com.vavilon.demo.da;

import com.vavilon.demo.bo.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByUser_UserIdAndAndToken(Long userId, String token);
}
