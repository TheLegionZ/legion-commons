package br.com.thelegion.legioncommons.chat.component;


import br.com.thelegion.legioncommons.chat.component.fanciful.FancyMessage;

public interface MessagePart {

    String getText();

    FancyMessage append(FancyMessage fancyMessage);

}
