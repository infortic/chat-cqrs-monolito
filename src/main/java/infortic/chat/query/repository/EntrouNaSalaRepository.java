package infortic.chat.query.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import infortic.chat.query.entity.EntrouNaSala;
import infortic.chat.query.entity.MensagensChat;

@Repository
public interface EntrouNaSalaRepository extends PagingAndSortingRepository<EntrouNaSala, Long> {
List<EntrouNaSala> findAllBySalaIdOrderByTimestamp(String salaId);
	
}
