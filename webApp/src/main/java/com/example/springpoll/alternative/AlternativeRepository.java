package com.example.springpoll.alternative;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springpoll.entities.Alternative;
import com.example.springpoll.entities.Question;

@Repository
public interface AlternativeRepository 
	extends JpaRepository<Alternative, BigInteger> {
 
	
	
}
