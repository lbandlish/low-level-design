package com.sw;

import com.sw.models.Expense;
import com.sw.models.SplitType;
import com.sw.models.User;
import com.sw.service.BalanceService;
import com.sw.store.UserDB;

import java.util.*;

/**
 * @author lakshay
 * @since 26/12/21
 */
public class Driver {
    public static void main(String[] args) {

        UserDB userDB = new UserDB(createUsers());
        BalanceService balanceService = new BalanceService(userDB);

//        Scanner in = new Scanner("SHOW\n" +
//                "SHOW u1\n" +
//                "EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL\n" +
//                "SHOW u4\n" +
//                "SHOW u1\n" +
//                "EXPENSE u1 1250 2 u2 u3 EXACT 370 880\n" +
//                "SHOW\n" +
//                "EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20\n" +
//                "SHOW u1\n" +
//                "SHOW\n");
        Scanner in = new Scanner(System.in);

        for (int x = 0; x < 10; x++) {
            String cmd = in.nextLine();
            System.out.println();
            String[] tokens = cmd.split(" ");
            if (tokens[0].equals("SHOW")) {
                if (tokens.length == 1) {
                    balanceService.show();
                } else {
                    balanceService.show(tokens[1]);
                }
            } else if (tokens[0].equals("EXPENSE")) {
                Expense expense = new Expense();
                expense.setPayee(tokens[1]);
                expense.setAmount(Float.parseFloat(tokens[2]));
                int num = Integer.parseInt(tokens[3]);
                Map<String, Float> userVsShare = new HashMap<>();
                int index = 4;
                for (int i = 0; i < num; i++,index++) {
                    userVsShare.put(tokens[index], 0f);
                }

                SplitType splitType = SplitType.valueOf(tokens[index++]);
                expense.setSplit(splitType);
                if (splitType != SplitType.EQUAL) {
                    for (int i = 0; i < num; i++, index++) {
                        userVsShare.put(tokens[index-num-1], Float.parseFloat(tokens[index]));
                    }
                }
                expense.setUserVsShare(userVsShare);
                balanceService.addExpense(expense);
            }
        }
    }

    public static List<User> createUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("u1", "user1", "lakki@example.com", 123));
        users.add(new User("u2", "user2", "pammi@example.com", 123));
        users.add(new User("u3", "user3", "hunar@example.com", 123));
        users.add(new User("u4", "user4", "surya@example.com", 123));
        return users;
    }
}
