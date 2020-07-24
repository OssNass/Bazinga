package org.sarc.asthma.database.controllers;


import io.github.ossnass.simplejpa.EntityController;
import org.jinq.orm.stream.JinqStream;
import org.sarc.asthma.database.entities.RegionEntity;

import java.util.List;

public class RegionContoller extends EntityController<RegionEntity, Integer> {
    public RegionContoller() {
        super(RegionEntity.class, Integer.class);
    }

    public RegionEntity getByShortName(String name) {
        JinqStream<RegionEntity> entities = jinq.streamAll(em, RegionEntity.class);
        return entities.where(e -> e.getShortname().equals(name)).getOnlyValue();
    }

    public RegionEntity getByFullName(String name) {
        JinqStream<RegionEntity> entities = jinq.streamAll(em, RegionEntity.class);
        return entities.where(e -> e.getFullname().equals(name)).getOnlyValue();
    }

    public List<RegionEntity> getRegionsByListOfFullNames(List<String> list) {
        JinqStream<RegionEntity> entities = jinq.streamAll(em, RegionEntity.class);
        return entities.where(e -> list.contains(e.getFullname())).toList();
    }
}
