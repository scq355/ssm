package com.qs.p2p.redis.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by scq on 2018-01-25 15:12:35
 */
public interface BaseRepository<T> extends CrudRepository<T, String> {

}