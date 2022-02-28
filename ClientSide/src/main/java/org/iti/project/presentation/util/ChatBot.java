package org.iti.project.presentation.util;

import com.google.code.chatterbotapi.*;

public class ChatBot {
    private ChatterBotFactory chatterBotFactory = new ChatterBotFactory();
    private ChatterBot chatterBot;

    public ChatBot ()  {
        try {
            chatterBot = chatterBotFactory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChatterBotSession createBotSession(){
        ChatterBotSession botSession = chatterBot.createSession();
        return botSession;
    }
}
