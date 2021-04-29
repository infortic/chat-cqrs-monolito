package com.artifact.coreapi.commads;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarSalaCommad {
	@TargetAggregateIdentifier
	private String salaId;
	private String nome;
	
	
}
