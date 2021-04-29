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
