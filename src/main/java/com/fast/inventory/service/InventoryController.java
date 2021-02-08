/**
 * 
 */
package com.fast.inventory.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fast.inventory.domain.InventoryItem;
import com.fast.inventory.repos.StockRepository;

/**
 * @author lgobi
 *
 */
@RestController
public class InventoryController {
	@Autowired
	private StockRepository repository;

	// @Autowired
	private Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Collection<InventoryItem> findAll() {

		List<InventoryItem> items = repository.findAll();
		logger.debug("get items:" + items);

		return items;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public InventoryItem delete(@RequestParam(value = "id") String id) {

		InventoryItem item = repository.findOne(id);
		logger.debug("deleted:" + item);

		repository.delete(item);
		return item;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public InventoryItem update(@RequestParam(value = "id") String id,
			@RequestParam(value = "location") String location, @RequestParam(value = "total") Integer total,
			@RequestParam(value = "booked", defaultValue = "0") Integer booked) {

		InventoryItem item = repository.findOne(id);
		logger.debug("found:" + item);

		if (item == null)
			item = new InventoryItem();

		item.setTotalQuantity(total);
		item.setBookedQuantity(booked);
		item.setLocation(location);

		return repository.save(item);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	private void handleHttpClientException(HttpServletResponse response) throws IOException {
		logger.error("exception ignored: " + response.getStatus() + " response:" + response);
	}

}
