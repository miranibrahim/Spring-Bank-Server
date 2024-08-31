package spring.bank.banking_app.mapper;

import spring.bank.banking_app.dto.AccountDto;
import spring.bank.banking_app.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
//        Account account = new Account(
//                accountDto.getId(),
//                accountDto.getAccountHolderName(),
//                accountDto.getBalance()
//        );
//        return account;
    Account account = new Account();
    if (accountDto.getId() != null) {
        account.setId(accountDto.getId());
    }
    account.setAccountHolderName(accountDto.getAccountHolderName());
    account.setBalance(accountDto.getBalance());
    return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
