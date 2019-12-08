package org.zerock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	User findByIdentity(String identity);
	User findByPassword(String password);

}
