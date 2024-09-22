package com.victor.messaging;

import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
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

    public void enviarParaFilaParcial(PagamentoDetalheRequestDTO pagamento) {
        enviarMensagem(filaParcial, pagamento);
    }

    public void enviarParaFilaTotal(PagamentoDetalheRequestDTO pagamento) {
        enviarMensagem(filaTotal, pagamento);
    }

    public void enviarParaFilaExcedente(PagamentoDetalheRequestDTO pagamento) {
        enviarMensagem(filaExcedente, pagamento);
    }

    private void enviarMensagem(String filaUrl, PagamentoDetalheRequestDTO pagamento) {
        try {
            String mensagem = objectMapper.writeValueAsString(pagamento);
            SendMessageRequest sendMsgRequest = criarRequestMessage(filaUrl, mensagem);
            sqsClient.sendMessage(sendMsgRequest);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar mensagem para SQS", e);
        }
    }

    private static SendMessageRequest criarRequestMessage(String filaUrl, String mensagem) {
        return SendMessageRequest.builder()
                .queueUrl(filaUrl)
                .messageBody(mensagem)
                .build();
    }
}

