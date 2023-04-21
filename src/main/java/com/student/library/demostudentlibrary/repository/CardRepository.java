package com.student.library.demostudentlibrary.repository;

import com.student.library.demostudentlibrary.model.Card;
import com.student.library.demostudentlibrary.model.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CardRepository extends JpaRepository<Card,Integer> {
    @Modifying
    @Query("update Card c set c.cardStatus=:deactivated where c.id in (select card from Student s where s.name=:name)")
    void deactivateCard(String name, CardStatus deactivated);
}
