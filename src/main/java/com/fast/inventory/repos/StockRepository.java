/**
 * 
 */
package com.fast.inventory.repos;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fast.inventory.domain.InventoryItem;

/**
 * @author lgobi
 *
 *         {@link http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#repositories.query-methods}
 *         (@link
 *         http://docs.spring.io/autorepo/docs/spring-data-mongodb/1.4.2.RELEASE
 *         /reference/html/mongo.repositories.html}
 */
public interface StockRepository extends MongoRepository<InventoryItem, String> {

	Collection<InventoryItem> findById(String itemId);
}
