package com.sw;

import com.sw.models.Expense;
import com.sw.service.BalanceService;
import com.sw.store.UserDB;
import org.junit.Test;

/**
 * @author lakshay
 * @since 26/12/21
 */
public class SplitwiseTest {

    @Test
    public void test() {
        UserDB userDB = new UserDB(Driver.createUsers());
        BalanceService balanceService = new BalanceService(userDB);

        balanceService.addExpense(new Expense());
    }
}
