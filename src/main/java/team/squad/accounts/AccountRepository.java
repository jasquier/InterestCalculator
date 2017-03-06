package team.squad.accounts;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by zipcoder on 3/3/17.
 */
@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {

}
