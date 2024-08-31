package spring.bank.banking_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.bank.banking_app.dto.AccountDto;
import spring.bank.banking_app.service.AccountService;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount( @RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getAccountById(@PathVariable Long id) {
        try {
            AccountDto accountDto = accountService.getAccountById(id);
            return ResponseEntity.ok(accountDto.toString());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
    }
    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAccount(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.depositAccount(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAccount(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdrawAccount(id, amount);
        return ResponseEntity.ok(accountDto);
    }
}
