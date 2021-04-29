package com.artifact.query.projection;

import java.time.Instant;
import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import com.artifact.coreapi.events.CriarSalaEvent;
import com.artifact.coreapi.events.SairSalaEvent;
import com.artifact.query.entity.SaiuDaSala;
import com.artifact.query.entity.SalaChat;
import com.artifact.query.query.SaiuDaSalaQuery;
import com.artifact.query.query.SalaChatQuery;
import com.artifact.query.repository.SairDaSalaRepository;
import com.artifact.query.repository.SalaChatRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SalasCriadas {
	
	private final SalaChatRepository salaChatRepository;
	private final QueryUpdateEmitter updateEmitter;
	

	@QueryHandler
	public Iterable<SalaChat> handle(SalaChatQuery query){
		return salaChatRepository.findAll();
	}
	
	
	@EventHandler
	public void on(CriarSalaEvent evt, @Timestamp Instant timestamp){
		SalaChat salaChat = new SalaChat();
		salaChat.setTimestamp(timestamp.toEpochMilli());
		salaChat.setSalaId(evt.getSalaId());
		salaChat.setNomeSala(evt.getNome());
		salaChatRepository.save(salaChat);
		updateEmitter.emit(SalaChatQuery.class, query -> query.getVerdadeiro() == true,  salaChat );
	}
	

}
