package ch.javaee.simpleMvc.dao;

import ch.javaee.simpleMvc.model.Phones;
import ch.javaee.simpleMvc.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Zeus on 23.12.15.
 */

//@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public UserDAOImpl() {}

    public UserDAOImpl(SessionFactory sessionFactory) {
this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<User> list() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listUser;
    }

    @Override
    @Transactional
    public void saveorupdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }


    @Override
    @Transactional
    public User get(int id) {
        String hql = "from User where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) query.list();

        if (listUser != null && !listUser.isEmpty()) {
            return listUser.get(0);
        }

        return null;
    }

    @Override
    @Transactional
    public Phones getphone(int id){

        Query q = sessionFactory.getCurrentSession().
                createQuery("FROM Phones where phoneid = :id").
                setParameter("id", id);
        List<Phones> phonelist = (List<Phones>) q.list();

        return phonelist.get(0);


    }

    @Override
    @Transactional
    public void delete(int id) {
        User userToDelete = new User();
        userToDelete.setUserid(id);
        sessionFactory.getCurrentSession().delete(userToDelete);
    }

    @Override
    @Transactional
    public User get(String name){

        //String hql = "from User where username" + "=" + name;
        //Query query = sessionFactory.getCurrentSession().createQuery(hql);
        //query.setString(1, name);


        Query q = sessionFactory.getCurrentSession().
                createQuery("FROM User where name = :name").
                setParameter("name", name);
        //q.setParameter(1-1, name);
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) q.list();
        if (listUser != null && !listUser.isEmpty()) {
            return listUser.get(0);
        }

        return null;}

    @Override
    public boolean loginvalid(String validname, String validpassword) {

        Query q = sessionFactory.getCurrentSession().
                createQuery("FROM User where name = :name").
                setParameter("name", validname);

        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) q.list();
        User user = listUser.get(0);
        if (listUser != null && !listUser.isEmpty() && user.getName().equals(validname)
                && user.getPassword().equals(validpassword)){
            return true;

        }
            return false;
    }

    /* Лист телефонов конкретного юзера */
    @Override
    @Transactional
    public List<Phones> listbyid(int id){
        Query q = sessionFactory.getCurrentSession().
                createQuery("FROM Phones where user_userid = :id").
                setParameter("id", id);

        @SuppressWarnings("unchecked")
        List<Phones> phoneList = (List<Phones>) q.list();

        if (phoneList != null && !phoneList.isEmpty()) {
            return phoneList;
        }

        return null;

    }

    @Override
    @Transactional
    public void deletephone(int id) {
        Phones phoneToDelete = new Phones();
        phoneToDelete.setPhoneid(id);
        sessionFactory.getCurrentSession().delete(phoneToDelete);
    }

    @Override
    @Transactional
    public void savephone(int id, String phone, String name, int userid){

        Query q = sessionFactory.getCurrentSession().
                createQuery("UPDATE Phones set phone = :phone, user_userid = :userid, name = :username WHERE phoneid = :phoneid");
        q.setParameter("phone", phone);
        q.setParameter("userid", userid);
        q.setParameter("username", name);
        q.setParameter("phoneid", id);

        q.executeUpdate();



    }

}
