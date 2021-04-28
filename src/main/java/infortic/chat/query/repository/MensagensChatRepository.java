package infortic.chat.query.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import infortic.chat.query.entity.MensagensChat;

@Repository
public interface MensagensChatRepository extends PagingAndSortingRepository<MensagensChat, Long> {
List<MensagensChat> findAllBySalaIdOrderByTimestamp(String salaId);
	
}
