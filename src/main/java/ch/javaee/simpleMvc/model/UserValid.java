package ch.javaee.simpleMvc.model;

import javax.validation.constraints.NotNull;

/**
 * Created by Zeus on 24.12.15.
 */
public class UserValid {

    private String name;


    private String password;

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
}
