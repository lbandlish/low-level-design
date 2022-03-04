package com.sw.service;

import com.sw.models.Expense;
import com.sw.models.SplitType;

import java.util.Map;

/**
 * @author lakshay
 * @since 26/12/21
 */
public class ExpenseValidationService {
    public boolean validate(Expense expense) {
        if (expense.getSplit() == SplitType.EQUAL) {
            return true;
        }

        boolean valid = false;

        float totalShare = 0;
        for (Float value : expense.getUserVsShare().values()) {
            totalShare += value;
        }

        if (expense.getSplit() == SplitType.EXACT) {
            valid = (totalShare == expense.getAmount());
        } else if (expense.getSplit() == SplitType.PERCENT) {
            valid = (totalShare == 100.0);
        }
        return valid; // to force addition of validation for future splitTypes.
    }
}
