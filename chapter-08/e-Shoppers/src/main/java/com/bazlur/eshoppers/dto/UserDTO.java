package com.bazlur.eshoppers.dto;

import com.bazlur.eshoppers.util.PasswordEqual;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@PasswordEqual(
        first = "password",
        second = "passwordConfirmed",
        message = "password and confirmed password do not match"
)
public class UserDTO
{
    @NotEmpty
    @Size(min = 1, max = 32)
    private String firstName;
    @NotEmpty
    @Size(min = 1, max = 32)
    private String lastName;
    @NotEmpty
    @Size(min = 6, max = 16)
    private String password;
    @NotEmpty
    @Size(min = 6, max = 16)
    private String passwordConfirmed;
    @NotEmpty
    @Size(min = 4, max = 64)
    private String email;
    @NotEmpty
    @Size(min = 4, max = 32)
    private String username;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPasswordConfirmed()
    {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(String passwordConfirmed)
    {
        this.passwordConfirmed = passwordConfirmed;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
