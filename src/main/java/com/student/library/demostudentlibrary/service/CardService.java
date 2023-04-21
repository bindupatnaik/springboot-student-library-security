package com.student.library.demostudentlibrary.service;

import com.student.library.demostudentlibrary.model.Card;
import com.student.library.demostudentlibrary.model.CardStatus;
import com.student.library.demostudentlibrary.model.Student;
import com.student.library.demostudentlibrary.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private static Logger logger = LoggerFactory.getLogger(CardService.class);
    @Autowired
    CardRepository cardRepository;

    public Card createCard(Student student) {
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);
        cardRepository.save(card);
        logger.info("card created successfully {} for student", card);
        return card;
    }

    public void deactivateCard(String name, CardStatus deactivated) {
        cardRepository.deactivateCard(name, deactivated);
    }
}
