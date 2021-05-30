package com.revature.banking.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import com.revature.banking.models.Account;
import com.revature.banking.models.Client;

import org.junit.jupiter.api.Test;

public class AccountsTest {
    @Test
    void testAccountJson() {

        Client client = mock(Client.class);
        Set<Account> accounts = new HashSet<>();
        Account savings = new Account();
        savings.setBalance(500);
        savings.setStatus("APPROVED");
        savings.setName("savings");
        savings.setId(1);
        accounts.add(savings);
        when(client.getAccounts()).thenReturn(accounts);
        Accounts servlet = new Accounts();
        String actualResult = servlet.accountJson(client);
        String expectedResult = "{\"savings\": {\"id\":1,\"balance\":500.0,\"name\":\"savings\",\"status\":\"APPROVED\"}}";
        assertEquals(expectedResult, actualResult);
    }
}
