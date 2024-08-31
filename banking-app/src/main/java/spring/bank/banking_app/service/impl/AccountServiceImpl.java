package spring.bank.banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.bank.banking_app.dto.AccountDto;
import spring.bank.banking_app.entity.Account;
import spring.bank.banking_app.mapper.AccountMapper;
import spring.bank.banking_app.repository.AccountRepository;
import spring.bank.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id).
                orElseThrow(()-> new RuntimeException("Account not exists."));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto depositAccount(Long id, double amount) {
        Account account = accountRepository
                .findById(id).
                orElseThrow(()-> new RuntimeException("Account not exists."));

        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawAccount(Long id, double amount) {
        Account account = accountRepository
                .findById(id).
                orElseThrow(()-> new RuntimeException("Account not exists."));

        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }


}
