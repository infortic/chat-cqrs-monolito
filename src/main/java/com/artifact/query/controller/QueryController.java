package com.artifact.query.controller;

import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.artifact.query.entity.EntrouNaSala;
import com.artifact.query.entity.MensagensChat;
import com.artifact.query.entity.SaiuDaSala;
import com.artifact.query.entity.SalaChat;
import com.artifact.query.query.MensagemSalaQuery;
import com.artifact.query.query.ParticipantesSalaQuery;
import com.artifact.query.query.SaiuDaSalaQuery;
import com.artifact.query.query.SalaChatQuery;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class QueryController {

	private final QueryGateway queryGateway; 
	
	@GetMapping("/sala/{salaId}/mensagems")
	public ResponseEntity<?> verMensagensChat(@PathVariable String salaId){
		return new ResponseEntity<>(queryGateway.query(new MensagemSalaQuery(salaId), new MultipleInstancesResponseType<MensagensChat>(MensagensChat.class)), HttpStatus.OK);
	}
	@GetMapping("/sala/{salaId}/participantes")
	public ResponseEntity<?> verParticipantes(@PathVariable String salaId){
		return new ResponseEntity<>(queryGateway.query(new ParticipantesSalaQuery(salaId), new MultipleInstancesResponseType<EntrouNaSala>(EntrouNaSala.class)), HttpStatus.OK);
	}
	@GetMapping("/sala")
	public ResponseEntity<?> verSalasCriadas(){
		return new ResponseEntity<>(queryGateway.query(new SalaChatQuery(true), new MultipleInstancesResponseType<SalaChat>(SalaChat.class)).join(), HttpStatus.OK);
	}

	@GetMapping("/sala/{salaId}/saiu")
	public ResponseEntity<?> quemSaiuDaSala(@PathVariable String salaId){
		return new ResponseEntity<>(queryGateway.query(new SaiuDaSalaQuery(salaId), new MultipleInstancesResponseType<SaiuDaSala>(SaiuDaSala.class)), HttpStatus.OK);
	}

}
