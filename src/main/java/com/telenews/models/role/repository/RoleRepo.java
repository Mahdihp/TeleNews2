package com.telenews.models.role.repository;

import com.telenews.models.role.Role;
import com.telenews.models.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(RoleName name);
}
