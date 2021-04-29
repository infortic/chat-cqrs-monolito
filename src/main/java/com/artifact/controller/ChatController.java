package com.artifact.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.artifact.coreapi.commads.CriarSalaCommad;
import com.artifact.coreapi.commads.EntrarSalaCommad;
import com.artifact.coreapi.commads.PostarMensagemCommad;
import com.artifact.coreapi.commads.SairSalaCommad;
import com.artifact.coreapi.events.SairSalaEvent;
import com.artifact.dto.CriarSalaDTO;
import com.artifact.dto.EntrarSalaDTO;
import com.artifact.dto.MensagemDTO;
import com.artifact.dto.SairSalaDTO;

@RestController
public class ChatController {

	private final CommandGateway commandGateway;

	public ChatController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	@PostMapping("/sala")
	public ResponseEntity<?> criarSalaDeChat(@RequestBody @Valid CriarSalaDTO payLoad){
		Assert.notNull(payLoad.getNome(),"Nome Inválido");
		String salaId = payLoad.getSalaId() == null ? UUID.randomUUID().toString() : payLoad.getSalaId();	
		commandGateway.sendAndWait(new CriarSalaCommad(salaId, payLoad.getNome()));
		return new ResponseEntity<>("Sala: "+payLoad.getNome()+" foi criada, com o eventId = "+payLoad.getSalaId(), HttpStatus.OK);
	}
	
	@PostMapping("/sala/entrar")
	public ResponseEntity<?> entrarSalaChet(@RequestBody @Valid EntrarSalaDTO payLoad){
		Assert.notNull(payLoad.getPartipante(),"Nome Inválido");
		return new ResponseEntity<>(commandGateway.send(new EntrarSalaCommad(payLoad.getSalaId(), payLoad.getPartipante())).join(),HttpStatus.OK);		
	}
	
	@PostMapping("/sala/sair")
	public ResponseEntity<?> sairSalaChet(@RequestBody @Valid SairSalaDTO payLoad){
		Assert.notNull(payLoad.getPartipante(),"Nome Inválido");
		return new ResponseEntity<>(commandGateway.send(new SairSalaCommad(payLoad.getSalaId(), payLoad.getPartipante())).join(),HttpStatus.OK);		
	}
	
	@PostMapping("/sala/postar-mensagem")
	public ResponseEntity<?> postarMensagem(@RequestBody @Valid MensagemDTO payLoad){
		Assert.notNull(payLoad.getNome(),"Nome Inválido");
		return new ResponseEntity<>(commandGateway.send(new PostarMensagemCommad(payLoad.getSalaId(), payLoad.getNome(), payLoad.getMensagem())).join(),HttpStatus.OK);		
	}
}
