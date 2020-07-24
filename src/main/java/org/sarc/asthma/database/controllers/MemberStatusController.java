package org.sarc.asthma.database.controllers;

import io.github.ossnass.simplejpa.EntityController;
import org.jinq.orm.stream.JinqStream;
import org.sarc.asthma.database.entities.MemberStatusEntity;

public class MemberStatusController extends EntityController<MemberStatusEntity, Integer> {
    public MemberStatusController() {
        super(MemberStatusEntity.class, Integer.class);
    }

    public MemberStatusEntity getByName(String name) {
        JinqStream<MemberStatusEntity> entities = jinq.streamAll(em, MemberStatusEntity.class);
        return entities.where(e -> e.getName().equals(name)).getOnlyValue();
    }
}
