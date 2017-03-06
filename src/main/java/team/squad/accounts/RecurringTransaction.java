package team.squad.accounts;


import javax.persistence.*;

/**
 * @author John A. Squier
 * add your name when you work on this file
 *
 * TODO figure out if getters are needed or not
 */
@Entity
@Table(name = "reccuring_transactions")
public class RecurringTransaction {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;

    private Long amount;


    private Integer frequency;

    public RecurringTransaction() {
        this.frequency = 12;
    }

    public RecurringTransaction(Long amount, Integer frequency) {
        this.amount = amount;
        this.frequency = frequency;
    }


    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }




    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}