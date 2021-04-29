package com.artifact.query.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.artifact.query.entity.MensagensChat;

public interface MensagensChatRepository extends PagingAndSortingRepository<MensagensChat, String> {
List<MensagensChat> findAllBySalaIdOrderByTimestamp(String salaId);
	
}
