package com.huseyin.expensetracker.repositories;

import com.huseyin.expensetracker.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}