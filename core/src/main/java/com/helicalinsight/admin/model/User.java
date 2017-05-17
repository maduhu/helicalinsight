/**
 *    Copyright (C) 2013-2017 Helical IT Solutions (http://www.helicalinsight.com) - All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.helicalinsight.admin.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * This is persistent class for user which maps it's properties with h_user
 * table and persist them to a database, this class's object or instance in
 * stored in user table as per rule this class should have the default
 * constructor, as well as getter and setter method's for it's properties.
 * Annotation Entity mark this class as Entity Bean and annotation Table allows
 * you to specify the details of the table that will be used to persist the
 * entity in the database.
 *
 * @author Muqtar Ahmed
 */

@Entity
@Table(name = "h_users")
public class User implements Serializable {

    /**
     * this is static and final filed of serial version id automatically
     * generated by eclipse
     */

    private static final long serialVersionUID = 1L;

    /**
     * each entity bean have the primary key and annotate with Id generated
     * values generate the automatically determined the most appropriate primary
     * key with strategy
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * details of the column user name to which field's or properties is mapped
     */

    private String username;

    /**
     * details of the column password to which field's or properties is mapped
     */

    private String password;

    /**
     * details of the column email address to which field's or properties is
     * mapped
     */

    private String emailAddress;


    /**
     * details of the column enabled to which field's or properties is mapped
     * for checking whether user is enabled or disabled
     */

    private boolean enabled;

    /**
     * OnToMany mapping between user and profile table, joins the profile table
     * with user table profile object is loaded together with user's fields as
     * fetch type is eager, when user is removed it's associate profile's also
     * get deleted.
     */

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Profile> profile;

    /**
     * ManyToMany mapping between user table and role table, and association are
     * mapped in intermediate user_role table when role is assigned to user.
     * user, role mapping entry will be stored in user_role table list of role
     * objects
     */

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id",
            referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",
            referencedColumnName = "id")})
    private List<Role> roles;

    private Boolean isExternallyAuthenticated;

    /**
     * default constructor
     */
    public User() {

    }

    /**
     * over loaded constructor with 5 arguments
     *
     * @param id       user Id
     * @param username user name
     * @param password password
     * @param roles    user roles
     * @param enabled  whether user is enabled or disabled
     */

    public User(int id, String username, String password, List<Role> roles, boolean enabled) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getIsExternallyAuthenticated() {
        if (isExternallyAuthenticated == null) {
            return false;
        } else {
            return true;
        }
    }

    public void setIsExternallyAuthenticated(Boolean isExternallyAuthenticated) {
        this.isExternallyAuthenticated = isExternallyAuthenticated;
    }

    /**
     * getter method for user id which is primary key for user table
     *
     * @return generated user id
     */

    public int getId() {
        return id;
    }

    /**
     * setter method for user id which is primary key for user table
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter method for user name
     *
     * @return user name
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter method for password
     *
     * @return password
     */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter method for list of role objects
     *
     * @return list of role objects
     */

    public List<Role> getRoles() {
        return roles;
    }

    /**
     * setter method for list of role objects
     */

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * check boolean whether true or false
     *
     * @return boolean
     */

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * set boolean whether true or false
     *
     * @param enabled boolean value
     */

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * getter method for list of profile objects
     *
     * @return list of profile objects
     */

    public List<Profile> getProfile() {
        return profile;
    }

    /**
     * setter method for list of profile objects
     *
     * @param profile list of profile objects
     */

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }


    /**
     * getter method for email address
     *
     * @return email address
     */

    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * setter method for email address
     *
     * @param emailAddress email address
     */

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


}