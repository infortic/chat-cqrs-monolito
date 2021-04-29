package com.artifact.query.entity;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaiuDaSala {
	@Id
	private String salaId;
	private String participante;
	private ZonedDateTime momnetoSaida = ZonedDateTime.now();
	private Long timestamp;
	
}
