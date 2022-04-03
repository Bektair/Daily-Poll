package com.example.springpoll.question;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springpoll.entities.Poll;
import com.example.springpoll.entities.Question;

@Repository
public interface QuestionRepository 
	extends JpaRepository<Question, BigInteger> {
 
	
	
}
