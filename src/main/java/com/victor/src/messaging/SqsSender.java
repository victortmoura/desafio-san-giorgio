package com.victor.src.messaging;

import com.victor.src.model.dto.PagamentoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SqsSender {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    @Value("${sqs.queue.parcial}")
    private String filaParcial;

    @Value("${sqs.queue.total}")
    private String filaTotal;

    @Value("${sqs.queue.excedente}")
    private String filaExcedente;

    public void enviarParaFilaParcial(PagamentoDTO pagamento) {
        enviarMensagem(filaParcial, pagamento);
    }

    public void enviarParaFilaTotal(PagamentoDTO pagamento) {
        enviarMensagem(filaTotal, pagamento);
    }

    public void enviarParaFilaExcedente(PagamentoDTO pagamento) {
        enviarMensagem(filaExcedente, pagamento);
    }

    private void enviarMensagem(String filaUrl, PagamentoDTO pagamento) {
        try {
            String mensagem = objectMapper.writeValueAsString(pagamento);
            SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                    .queueUrl(filaUrl)
                    .messageBody(mensagem)
                    .build();
            sqsClient.sendMessage(sendMsgRequest);
        } catch (Exception e) {
            // Trate a exceção adequadamente (log, retry, etc.)
            throw new RuntimeException("Erro ao enviar mensagem para SQS", e);
        }
    }
}

