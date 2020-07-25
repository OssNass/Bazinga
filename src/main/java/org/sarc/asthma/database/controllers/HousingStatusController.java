package org.sarc.asthma.database.controllers;

import io.github.ossnass.simplejpa.EntityController;
import org.jinq.orm.stream.JinqStream;
import org.sarc.asthma.database.entities.HousingStatusEntity;

public class HousingStatusController extends EntityController<HousingStatusEntity, Integer> {

    public HousingStatusController() {
        super(HousingStatusEntity.class, Integer.class);
    }

    public HousingStatusEntity getByName(String name) {
        JinqStream<HousingStatusEntity> entities = jinq.streamAll(em, HousingStatusEntity.class);
        return entities.where(e -> e.getName().equals(name)).getOnlyValue();
    }
}
