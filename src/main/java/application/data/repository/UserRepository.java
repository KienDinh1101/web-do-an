package application.data.repository;

import application.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select count(u.id) from tbl_user u")
    long getTotalUsers();

    @Transactional(readOnly = true)
    @Query("select u from tbl_user  u where u.email = :email")
    Iterable<User> findByEmail(@Param("email") String email);

    @Transactional(readOnly = true)
    @Query("select u from tbl_user u where u.userName = :username")
    Iterable<User> findByUsername(@Param("username") String userName);

    @Transactional(readOnly = true)
    @Query("select u from tbl_user  u where u.email = :email and u.passwordReset = :reset")
    Iterable<User> findByEmailAndPassword(@Param("email") String email,@Param("reset") String reset);

    @Transactional(readOnly = true)
    @Query( value = "SELECT r.role_id FROM tbl_user u\n" +
            "INNER JOIN tbl_user_role ur ON  u.user_id = ur.user_id\n" +
            "INNER JOIN tbl_role r ON  r.role_id = ur.role_id\n" +
            "WHERE u.user_id = :", nativeQuery = true)
    List<Integer> findRoleByUser(@Param("userId") Integer userId);

}
