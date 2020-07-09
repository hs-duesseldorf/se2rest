package org.hsd.inflab.se2server.repository;

import java.io.Serializable;

import org.hsd.inflab.se2server.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface BaseRepository<T extends AbstractEntity> extends JpaRepository<T, Serializable> {

}