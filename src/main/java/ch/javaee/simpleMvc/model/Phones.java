package ch.javaee.simpleMvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Zeus on 24.12.15.
 */

@Entity
@Table(name = "Phones")
public class Phones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phoneid")
    private int phoneid;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(cascade=CascadeType.ALL)
    private User user;

    public int getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(int phoneid) {
        this.phoneid = phoneid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
