package spring.bank.banking_app.service;

import spring.bank.banking_app.dto.AccountDto;

import java.util.List;


public interface AccountService {
    AccountDto createAccount (AccountDto accountDto);
    AccountDto getAccountById (Long id);
    AccountDto depositAccount(Long id, double amount);
    AccountDto withdrawAccount(Long id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
    public String getAccountHolderWithHighestBalance();
}
