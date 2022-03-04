package com.sw.service;

import com.sw.models.Expense;
import com.sw.models.SplitType;
import com.sw.store.UserDB;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author lakshay
 * @since 26/12/21
 */
public class BalanceService {
    Map<String, Map<String, Float>> balances;
    private final UserDB userDB;
    private final ExpenseValidationService expenseValidationService;

    public BalanceService(UserDB userDB) {
        balances = new HashMap<>();
        this.userDB = userDB;
        expenseValidationService = new ExpenseValidationService();
        initializeBalances();
    }

    private void initializeBalances() {
        List<String> userIds = userDB.getUserIds();
        userIds.forEach(userId -> balances.put(userId, new HashMap<>()));
    }

    public void addExpense(Expense expense) {
        if (expenseValidationService.validate(expense)) {
            addValidatedExpense(expense);
        }
    }

    private void addValidatedExpense(Expense expense) {
        Map<String, Float> userVsShare = expense.getUserVsShare();
        Iterator<Map.Entry<String, Float>> iterator = userVsShare.entrySet().iterator();
        String firstUser = iterator.next().getKey();
        float firstUserShare = expense.getAmount();

        while (iterator.hasNext()) {
            Map.Entry<String, Float> entry = iterator.next();
            float share = entry.getValue();

            if (expense.getSplit() == SplitType.EQUAL) {
                share = getFloat(expense.getAmount() / userVsShare.size());
            } else if (expense.getSplit() == SplitType.PERCENT) {
                share = getFloat(share * expense.getAmount() / 100);
            }

            addExpenseForUser(expense.getPayee(), entry.getKey(), share);
            firstUserShare -= share;
        }

        addExpenseForUser(expense.getPayee(), firstUser, firstUserShare);
    }

    private void addExpenseForUser(String payee, String lender, float share) {
        Map<String, Float> payeeBalance = balances.get(payee);
        Map<String, Float> lenderBalance = balances.get(lender);
        payeeBalance.put(lender, payeeBalance.getOrDefault(lender, 0f) - share);
        lenderBalance.put(payee, lenderBalance.getOrDefault(payee, 0f) + share);
    }

    private static float getFloat(Float num) {
        return Float.parseFloat(String.format("%.2f", num));
    }

    public void show() {
        userDB.getUserIds().forEach(userId -> show(userId, false));
    }

    public void show(String userId) {
        show(userId, true);
    }

    private void show(String userId, boolean showOwed) {
        Map<String, Float> userBalance = balances.get(userId);
        for (Map.Entry<String, Float> entry : userBalance.entrySet()) {
            String otherUserId = entry.getKey();
            float share = entry.getValue();
            String userName = userDB.getUser(userId).getName();
            String otherUserName = userDB.getUser(otherUserId).getName();

            if (showOwed && share < 0f) {
                System.out.println(otherUserName + " owes " + userName + ": " + (-1 * share));
            } else if (share > 0f) {
                System.out.println(userName + " owes " + otherUserName + ": " + share);
            }
        }
    }
}
