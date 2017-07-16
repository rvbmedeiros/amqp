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
package teste.producer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private RabbitTemplate template;

    @Scheduled(fixedRate = 500)
    public void doGet() {
	try {
	    List<Mensagem> mensagens = new ArrayList<>();
	    for (int i = 0; i < 5; i++) {
		Mensagem m = new Mensagem();
		m.setCodigo(Double.doubleToLongBits(Math.random()));
		m.setConteudo("IPSUM LORE");
		m.setHorario(new Date());
		m.setNome("TESTE");
		m.setTeor("AO SEU TEOR TUDO VALE DESDE QUE O VALOR DA VALETUDE VALHA O VALOR VALIDO");
		mensagens.add(m);
	    }
	    this.template.convertAndSend("teste", "teste", mensagens);
	    System.out.println(" [x] Sent '" + mensagens.toString() + "'");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
