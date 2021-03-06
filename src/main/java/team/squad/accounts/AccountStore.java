package team.squad.accounts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John A. Squier
 * add your name when you work in this file.
 */
public class AccountStore {

    private static List<Account> accounts = new ArrayList<>();

    public static void addAccountToStore(Account account) {
        accounts.add(account);
    }

    public static Account getAccountByID(Integer ID) {
        for ( Account a : accounts ) {
            if (a.getID().equals(ID) ) {
                return a;
            }
        }
        return null; // should be an exception
    }
}
