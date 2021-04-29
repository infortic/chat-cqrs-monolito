package com.artifact.query.entity;

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
public class MensagensChat {

	private Long timestamp;
	@Id
	private String salaId;
	private String mensagem;
	private String participante;
	
	
}
