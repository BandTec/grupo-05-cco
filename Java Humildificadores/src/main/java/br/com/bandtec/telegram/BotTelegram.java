package br.com.bandtec.telegram;

import br.com.bandtec.Conexoes.ConexaoBanco;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class BotTelegram {

    public void chamadoTelegram(String nomeComp, String usuario) {
        
        TelegramBot bot = new TelegramBot("1434977947:AAGUpr66CbRu--V5wLwPN2lWnakDuhk6eDE");

        GetUpdatesResponse updatesResponse;
        SendResponse sendResponse;
        BaseResponse baseResponse;

        Integer m = 0;
        String mensagem = String.format("Olá, recebemos um chamado de um componente em alerta.\n"
                + "Componente: %s\n"
                + "Maquina: %s\n"
                + "Ver chamado: https://humildifica.atlassian.net/jira/servicedesk/projects/TES/queues/custom/18", nomeComp, usuario);
        SendResponse response = bot.execute(new SendMessage(-443294498, mensagem));
        
    }

}
