package com.epam.esm.repository.api;

import com.epam.esm.domain.entity.User;
import com.epam.esm.domain.payload.UserFilterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing {@link User} entities.
 *
 * @author Oleksandr Koreshev
 * @since 1.0
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * Returns a list of users filtered by the
     * specified {@link UserFilterDto}.
     *
     * @param filter the filter to apply to the query, may be {@code null}.
     * @param pageable the pagination information, may be {@code null}.
     * @return a list of users filtered by the specified {@link UserFilterDto}.
     */
    @Query(value = "SELECT u FROM User u " +
            "WHERE " +
            "(:#{#filter.login} is null or u.login = :#{#filter.login}) AND " +
            "(:#{#filter.email} is null or u.email = :#{#filter.email}) AND " +
            "(:#{#filter.role} is null or u.role = :#{#filter.role}) AND " +
            "LOWER(u.login) LIKE LOWER(CONCAT('%', :#{#filter.loginContaining}, '%')) AND " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :#{#filter.emailContaining}, '%')) AND " +
            "LOWER(u.role) LIKE LOWER(CONCAT('%', :#{#filter.roleContaining}, '%'))")
    List<User> findAll(@Param("filter") UserFilterDto filter, Pageable pageable);
}
