package com.artifact.query.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class EntrouNaSala implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long timestamp;
	@Id
	private String salaId;
	private String participante;
	private ZonedDateTime momnetoSaida = ZonedDateTime.now();
	
}
