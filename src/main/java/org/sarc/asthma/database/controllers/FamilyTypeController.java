package org.sarc.asthma.database.controllers;

import io.github.ossnass.simplejpa.EntityController;
import org.jinq.orm.stream.JinqStream;
import org.sarc.asthma.database.entities.FamilyTypeEntity;

public class FamilyTypeController extends EntityController<FamilyTypeEntity, Integer> {

    public FamilyTypeController() {
        super(FamilyTypeEntity.class, Integer.class);
    }

    public FamilyTypeEntity getByName(String name) {
        JinqStream<FamilyTypeEntity> entities = jinq.streamAll(em, FamilyTypeEntity.class);
        return entities.where(e -> e.getName().equals(name)).getOnlyValue();
    }
}
