package spring.bank.banking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bank.banking_app.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
