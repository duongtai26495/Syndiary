package com.duongtai.sydiary.repositories;

import com.duongtai.sydiary.entities.Diary;
import com.duongtai.sydiary.entities.Role;
import com.duongtai.sydiary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
