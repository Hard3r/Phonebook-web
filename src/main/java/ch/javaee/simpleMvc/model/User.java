package ch.javaee.simpleMvc.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Zeus on 23.12.15.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private int userid;

    @NotNull
    @Size(min = 3, max = 10)
    @Column(name = "name")
    private String name;

    @NotNull
    @Length(min = 3)
    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @ElementCollection(targetClass=Phones.class)
    private Set<Phones> phonesSet;

//proverka

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Phones> getPhonesSet() {
        return phonesSet;
    }

    public void setPhonesSet(Set<Phones> phonesSet) {
        this.phonesSet = phonesSet;
    }
}
