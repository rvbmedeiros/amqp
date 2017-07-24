/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.finaxis.controller.producer.titulo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Service
@RequestMapping(value = "api/titulos")
@ResponseBody
public class TituloService {

	@Autowired
	private RabbitTemplate template;

	@ApiOperation(value = "Get Person", response = Titulo.class, httpMethod = "GET", authorizations = { @Authorization(value = "teste") })
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Person not found", response = String.class) })
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> recuperarTitulo(@PathVariable("id") String id) {
		Titulo m = new Titulo();
		m.setCodigo("1234678");
		m.setNome("BOLETO");
		return new ResponseEntity<Titulo>(m, HttpStatus.OK);
	}

	// @Scheduled(fixedRate = 10000)
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> criarTitulo() {
		try {
			Titulo m = new Titulo();
			m.setCodigo("1234678");
			m.setNome("BOLETO");

			this.template.convertAndSend("teste", "teste", m);
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add("X-MENSAGEM-ID", m.getCodigo().toString());

			return new ResponseEntity(headers, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
