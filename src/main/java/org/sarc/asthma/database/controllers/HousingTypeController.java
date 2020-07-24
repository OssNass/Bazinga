package org.sarc.asthma.database.controllers;

import io.github.ossnass.simplejpa.EntityController;
import org.jinq.orm.stream.JinqStream;
import org.sarc.asthma.database.entities.HousingTypeEntity;

public class HousingTypeController extends EntityController<HousingTypeEntity, Integer> {

    public HousingTypeController() {
        super(HousingTypeEntity.class, Integer.class);
    }

    public HousingTypeEntity getByName(String name) {
        JinqStream<HousingTypeEntity> entities = jinq.streamAll(em, HousingTypeEntity.class);
        return entities.where(e -> e.getName().equals(name)).getOnlyValue();
    }
}
