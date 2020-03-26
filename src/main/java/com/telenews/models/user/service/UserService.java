package com.telenews.models.user.service;

import com.telenews.models.role.repository.RoleRepo;
import com.telenews.models.user.repository.UserRepo;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
}
