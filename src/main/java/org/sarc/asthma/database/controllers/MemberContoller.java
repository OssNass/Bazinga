package org.sarc.asthma.database.controllers;

import io.github.ossnass.simplejpa.EntityController;
import org.sarc.asthma.database.entities.MemberEntity;
import org.sarc.asthma.database.entities.MemberEntityPK;

public class MemberContoller extends EntityController<MemberEntity, MemberEntityPK> {
    public MemberContoller() {
        super(MemberEntity.class, MemberEntityPK.class);
    }
}
