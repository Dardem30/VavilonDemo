package com.vavilon.demo.da;

import com.vavilon.demo.bo.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByLogin(final String login);
    AppUser findByEmail(final String email);
    boolean existsByLogin(final String login);
    boolean existsByEmail(final String email);
}
