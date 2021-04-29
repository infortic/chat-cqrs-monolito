package com.artifact.query.projection;

import java.time.Instant;
import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import com.artifact.coreapi.events.CriarSalaEvent;
import com.artifact.coreapi.events.EntrarSalaEvent;
import com.artifact.coreapi.events.PostarMensagemEvent;
import com.artifact.coreapi.events.SairSalaEvent;
import com.artifact.query.entity.EntrouNaSala;
import com.artifact.query.entity.MensagensChat;
import com.artifact.query.entity.SaiuDaSala;
import com.artifact.query.entity.SalaChat;
import com.artifact.query.query.MensagemSalaQuery;
import com.artifact.query.query.ParticipantesSalaQuery;
import com.artifact.query.query.SaiuDaSalaQuery;
import com.artifact.query.query.SalaChatQuery;
import com.artifact.query.repository.EntrouNaSalaRepository;
import com.artifact.query.repository.MensagensChatRepository;
import com.artifact.query.repository.SairDaSalaRepository;
import com.artifact.query.repository.SalaChatRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SaiuDaSalaProjection {

	
	private final SairDaSalaRepository SairDaSalaRepository;
	private final QueryUpdateEmitter updateEmitter;
	

	@QueryHandler
	public List<SaiuDaSala> handle(SaiuDaSalaQuery query){
		return SairDaSalaRepository.findAllBySalaIdOrderByTimestamp(query.getSalaId());		
	}
	
	
	@EventHandler
	public void on(SairSalaEvent evt, @Timestamp Instant timestamp){
		SaiuDaSala sairSalaEvent = new SaiuDaSala();
		sairSalaEvent.setTimestamp(timestamp.toEpochMilli());
		sairSalaEvent.setParticipante(evt.getParticipante());
		sairSalaEvent.setSalaId(evt.getSalaId());
		SairDaSalaRepository.save(sairSalaEvent);
		updateEmitter.emit(SaiuDaSalaQuery.class, query -> query.getSalaId().equals(evt.getSalaId()),  sairSalaEvent );
	}
	
}
