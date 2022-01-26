package ru.sibdigital.lexpro_migr.service;

import ru.sibdigital.lexpro_migr.model.lexpro.TpRkkMailingDestination;
import ru.sibdigital.lexpro_migr.model.zakon.MessagesEntity;

public class ImportMessagesService extends ImportService<MessagesEntity, TpRkkMailingDestination>{
    public ImportMessagesService() {
        super("messages");
    }


}
