package mvc.core.repositories;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.repositories.AccountRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by alexggg99 on 03.12.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/bussines-config.xml")
public class AccountRepoTest {

    @Autowired
    private AccountRepo repo;

    private Account account;

    @Before
    @Transactional
    @Rollback(false)
    public void setUp(){
        account = new Account();
        account.setName("test");
        account.setId(1L);
        account.setPassword("123");
        repo.createAccount(account);
    }

    @Test
    @Transactional
    public void testFind(){
        assertNotNull(repo.find(account.getId()));
    }
}
