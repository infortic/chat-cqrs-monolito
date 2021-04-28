package infortic.chat.query.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import infortic.chat.query.entity.MensagensChat;
import infortic.chat.query.entity.SalaChat;

@Repository
public interface SalaChatRepository extends PagingAndSortingRepository<SalaChat, Long> {
List<SalaChat> findAllBySalaIdOrderByTimestamp(String salaId);
	
}
