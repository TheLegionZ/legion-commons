package br.com.thelegion.legioncommons.chat.component;


import br.com.thelegion.legioncommons.chat.component.fanciful.FancyMessage;

public interface MessagePart {

    FancyMessage append(FancyMessage fancyMessage);

}
