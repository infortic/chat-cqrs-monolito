package com.artifact.query.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.artifact.query.entity.MensagensChat;
import com.artifact.query.entity.SaiuDaSala;

public interface SairDaSalaRepository extends PagingAndSortingRepository<SaiuDaSala, String> {
List<SaiuDaSala> findAllBySalaIdOrderByTimestamp(String salaId);
	
}
