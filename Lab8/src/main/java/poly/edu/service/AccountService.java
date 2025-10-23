package poly.edu.service;

import java.util.List;
import poly.edu.entity.Account;

public interface AccountService {

    Account findById(String username);

    List<Account> findAll();

    Account save(Account account);

    void deleteById(String username);
}
