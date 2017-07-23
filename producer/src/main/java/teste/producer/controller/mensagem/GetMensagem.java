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
package teste.producer.controller.mensagem;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping(value = "mensagem", method = RequestMethod.GET)
public class GetMensagem {

    @GetMapping(value = "{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> recuperarMensagem(@PathVariable("id") String id) {
	Mensagem m = new Mensagem();
	m.setCodigo(Long.parseLong(id));
	m.setConteudo("IPSUM LORE");
	m.setHorario(new Date());
	m.setNome("TESTE");
	m.setTeor("AO SEU TEOR TUDO VALE DESDE QUE O VALOR DA VALETUDE VALHA O VALOR VALIDO");
	return new ResponseEntity<Mensagem>(m, HttpStatus.OK);
    }

}
