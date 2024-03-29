package com.paymybuddy.webapp.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.paymybuddy.webapp.model.User;


/**
 * The Class MyUserDetails.
 * @author Senthil
 */
public class MyUserDetails  implements UserDetails {


	/** The user name. */
	private String userName;

    /** The password. */
    private String password;

    /** The active. */
    private boolean active;

    /** The authorities. */
    private List<GrantedAuthority> authorities;

    // ************************************************************************

    /**
     * Instantiates a new my user details.
     *
     * @param user the user
     */
    public MyUserDetails(final User user) {
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // ************************************************************************

    /**
     * Gets the authorities.
     *
     * @return the authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * Checks if is account non expired.
     *
     * @return true, if is account non expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if is account non locked.
     *
     * @return true, if is account non locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if is credentials non expired.
     *
     * @return true, if is credentials non expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if is enabled.
     *
     * @return true, if is enabled
     */
    @Override
    public boolean isEnabled() {
        return active;
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "MyUserDetailsDTO ["
				+ "userName=" + userName
				+ ", password=" + password
				+ ", active=" + active
				+ ", authorities=" + authorities + "]";
	}
}
