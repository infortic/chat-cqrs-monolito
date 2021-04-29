package com.artifact.query.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.artifact.query.entity.EntrouNaSala;


public interface EntrouNaSalaRepository extends PagingAndSortingRepository<EntrouNaSala, String> {
	
List<EntrouNaSala> findAllBySalaIdOrderByTimestamp(String salaId);
	
}
