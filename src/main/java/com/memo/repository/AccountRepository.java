package com.memo.repository;

import com.memo.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findById(String userid);
    public Account findByIdAndPassword(String userid, String password);
}
