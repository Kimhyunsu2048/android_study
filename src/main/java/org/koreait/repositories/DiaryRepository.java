package org.koreait.repositories;

import org.koreait.entities.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("SELECT d FROM Diary d JOIN d.user u WHERE u.userId = :userId")
    List<Diary> getListByUser(@Param("userId") String userId);
}
