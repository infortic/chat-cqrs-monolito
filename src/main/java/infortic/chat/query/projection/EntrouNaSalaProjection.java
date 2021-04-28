package infortic.chat.query.projection;

import java.time.Instant;
import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;
import infortic.chat.coreapi.events.CriarSalaEvent;
import infortic.chat.coreapi.events.EntrarSalaEvent;
import infortic.chat.coreapi.events.PostarMensagemEvent;
import infortic.chat.coreapi.events.SairSalaEvent;
import infortic.chat.query.entity.EntrouNaSala;
import infortic.chat.query.entity.MensagensChat;
import infortic.chat.query.entity.SaiuDaSala;
import infortic.chat.query.entity.SalaChat;
import infortic.chat.query.query.MensagemSalaQuery;
import infortic.chat.query.query.ParticipantesSalaQuery;
import infortic.chat.query.query.SaiuDaSalaQuery;
import infortic.chat.query.query.SalaChatQuery;
import infortic.chat.query.repository.EntrouNaSalaRepository;
import infortic.chat.query.repository.MensagensChatRepository;
import infortic.chat.query.repository.SairDaSalaRepository;
import infortic.chat.query.repository.SalaChatRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntrouNaSalaProjection {

	private final EntrouNaSalaRepository entrarSalaRepository;	
	private final QueryUpdateEmitter updateEmitter;
	

	@QueryHandler
	public List<EntrouNaSala> handle(ParticipantesSalaQuery query){
		return entrarSalaRepository.findAllBySalaIdOrderByTimestamp(query.getSalaId());		
	}
		
	
	@EventHandler
	public void on(EntrarSalaEvent evt, @Timestamp Instant timestamp){
		EntrouNaSala entrouNaSala = new EntrouNaSala();
		entrouNaSala.setTimestamp(timestamp.toEpochMilli());
		entrouNaSala.setParticipante(evt.getParticipante());
		entrouNaSala.setSalaId(evt.getSalaId());
		entrarSalaRepository.save(entrouNaSala);
		updateEmitter.emit(ParticipantesSalaQuery.class, query -> query.getSalaId().equals(evt.getSalaId()),  entrouNaSala );
	}
	
	
	
}
