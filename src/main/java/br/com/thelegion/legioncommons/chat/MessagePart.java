package br.com.thelegion.legioncommons.chat;


import br.com.thelegion.legioncommons.chat.fanciful.FancyMessage;

public interface MessagePart {

    FancyMessage append(FancyMessage fancyMessage);

}
