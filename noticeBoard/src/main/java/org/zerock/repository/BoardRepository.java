package org.zerock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Long>{

}
