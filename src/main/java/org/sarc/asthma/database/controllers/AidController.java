package org.sarc.asthma.database.controllers;

import io.github.ossnass.simplejpa.EntityController;
import org.jinq.orm.stream.JinqStream;
import org.sarc.asthma.database.entities.AidEntity;

public class AidController extends EntityController<AidEntity, Integer> {
    public AidController() {
        super(AidEntity.class, Integer.class);
    }

    public AidEntity getByName(String name) {
        JinqStream<AidEntity> entities = jinq.streamAll(em, AidEntity.class);
        return entities.where(e -> e.getName().equals(name)).getOnlyValue();
    }
}
