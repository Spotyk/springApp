package ua.knucea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knucea.domain.entity.Role;
import ua.knucea.domain.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRoles(Role role);

    User findByEmail(String email);

    User findPasswordById(Long id);

}
