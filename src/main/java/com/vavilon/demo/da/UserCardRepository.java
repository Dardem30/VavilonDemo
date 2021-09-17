package com.vavilon.demo.da;

import com.vavilon.demo.bo.userCard.UserCard;
import com.vavilon.demo.da.extension.UserCardRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Long>, UserCardRepositoryExtension {
}
