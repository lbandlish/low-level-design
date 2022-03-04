package com.sw.models;

import java.util.Map;

/**
 * @author lakshay
 * @since 26/12/21
 */
public class Expense {
    Float amount;
    SplitType split;
    String payee;
    Map<String, Float> userVsShare;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public SplitType getSplit() {
        return split;
    }

    public void setSplit(SplitType split) {
        this.split = split;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Map<String, Float> getUserVsShare() {
        return userVsShare;
    }

    public void setUserVsShare(Map<String, Float> userVsShare) {
        this.userVsShare = userVsShare;
    }
}
