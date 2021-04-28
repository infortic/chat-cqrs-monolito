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
public class MensagensPostadasProjection {

	
	private final MensagensChatRepository mensagensChaRepository;	
	private final QueryUpdateEmitter updateEmitter;
	

	@QueryHandler
	public List<MensagensChat> handle(MensagemSalaQuery query){
		return mensagensChaRepository.findAllBySalaIdOrderByTimestamp(query.getSalaId());		
	}
	
		
	@EventHandler
	public void on(PostarMensagemEvent evt, @Timestamp Instant timestamp){
		MensagensChat mensagensChat = new MensagensChat();
		mensagensChat.setTimestamp(timestamp.toEpochMilli());
		mensagensChat.setMensagem(evt.getMensagem());
		mensagensChat.setParticipante(evt.getParticipante());
		mensagensChat.setSalaId(evt.getSalaId());
		mensagensChaRepository.save(mensagensChat);
		updateEmitter.emit(MensagemSalaQuery.class, query -> query.getSalaId().equals(evt.getSalaId()),  mensagensChat );
	}
	
	
}
