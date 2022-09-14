package com.duongtai.sydiary.repositories;

import com.duongtai.sydiary.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistTokenRepository extends JpaRepository<Token, Long> {

}
