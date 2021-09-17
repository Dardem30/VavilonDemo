package com.vavilon.demo.da.extension;

import com.vavilon.demo.bo.userCard.UserCardLight;

import java.util.List;

public interface UserCardRepositoryExtension {
    List<UserCardLight> list(Long userId);
}
