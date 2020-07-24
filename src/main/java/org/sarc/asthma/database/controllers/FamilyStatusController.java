package org.sarc.asthma.database.controllers;

import io.github.ossnass.simplejpa.EntityController;
import org.jinq.orm.stream.JinqStream;
import org.sarc.asthma.database.entities.FamilyStatusEntity;

public class FamilyStatusController extends EntityController<FamilyStatusEntity, Integer> {

    public FamilyStatusController() {
        super(FamilyStatusEntity.class, Integer.class);
    }

    public FamilyStatusEntity getByName(String name) {
        JinqStream<FamilyStatusEntity> entities = jinq.streamAll(em, FamilyStatusEntity.class);
        return entities.where(e -> e.getName().equals(name)).getOnlyValue();
    }
}
