package org.zerock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Long>{

	List<Board> findAllByUserIdentity(String identity);
}
