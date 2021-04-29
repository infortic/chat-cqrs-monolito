package com.artifact.query.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.artifact.query.entity.MensagensChat;
import com.artifact.query.entity.SalaChat;


public interface SalaChatRepository extends PagingAndSortingRepository<SalaChat, String> {
List<SalaChat> findAllBySalaIdOrderByTimestamp(String salaId);
	
}
