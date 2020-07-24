package org.sarc.asthma.database.controllers;

import io.github.ossnass.simplejpa.EntityController;
import org.jinq.orm.stream.JinqStream;
import org.sarc.asthma.database.entities.RelationshipEntity;

public class RelationshipController extends EntityController<RelationshipEntity, Integer> {
    public RelationshipController() {
        super(RelationshipEntity.class, Integer.class);
    }

    public RelationshipEntity getByName(String name) {
        JinqStream<RelationshipEntity> entities = jinq.streamAll(em, RelationshipEntity.class);
        return entities.where(e -> e.getName().equals(name)).getOnlyValue();
    }
}
