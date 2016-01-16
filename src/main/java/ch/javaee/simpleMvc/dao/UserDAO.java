package ch.javaee.simpleMvc.dao;

import ch.javaee.simpleMvc.model.Phones;
import ch.javaee.simpleMvc.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Zeus on 23.12.15.
 */
public interface UserDAO {
    @Transactional
    public List<User> list();
@Transactional
    public void saveorupdate(User user);

    @Transactional
    User get(int id);

    @Transactional
    Phones getphone(int id);

    @Transactional
    void delete(int id);

    @Transactional
    User get(String name);

    @Transactional
    boolean loginvalid(String validname, String validpassword);

    /* Лист телефонов конкретного юзера */
    @Transactional
    List<Phones> listbyid(int id);

    @Transactional
    void deletephone(int id);

    @Transactional
    void savephone(int id, String phone, String username, int userid);
}
