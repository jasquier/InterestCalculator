package team.squad.accounts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author John A. Squier
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	//This is for testing Database insertion
	@Bean
	public CommandLineRunner demo(AccountRepository repository) {
		return (args) -> {
			// save a couple of customers

			Account  account = new Account(980l,960l,"checking",2.0,40l,100l,true);

			PastTransaction pastTransaction1 = new PastTransaction(90l,Calendar.getInstance().getTime());
			PastTransaction pastTransaction2 = new PastTransaction(90l,Calendar.getInstance().getTime());
			PastTransaction pastTransaction3 = new PastTransaction(90l,Calendar.getInstance().getTime());
			List<PastTransaction> accountHistory = new ArrayList<PastTransaction>();
			accountHistory.add(pastTransaction1);
			accountHistory.add(pastTransaction2);
			accountHistory.add(pastTransaction3);
			account.setAccountHistory(accountHistory);

			RecurringTransaction recurringTransaction = new RecurringTransaction();

			List<RecurringTransaction> recurringTransactions = new ArrayList<RecurringTransaction>();
			recurringTransaction.setAmount(98l);
			recurringTransaction.setFrequency(6);
			recurringTransactions.add(recurringTransaction);
			account.setRecurringTransactions(recurringTransactions);
			repository.save(account);

		};
	}

}
