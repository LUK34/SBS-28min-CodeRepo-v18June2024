package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Users;

public interface UsersRepo extends JpaRepository<Users, Long> {

}
