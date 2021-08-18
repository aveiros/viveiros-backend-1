package com.viveiros.app.dal;

import com.viveiros.app.dal.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User Dao to access user information
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    /**
     * Find user by email
     *
     * @param email the email
     * @return a {@link User} when found, null otherwise
     */
    User findByEmail(String email);
}
