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

//	@Autowired
	private Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Collection<InventoryItem> findAll() {

		List<InventoryItem> items = repository.findAll();
		logger.info("all:" + items);

		return items;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Collection<InventoryItem> findItem(@RequestParam(value = "item") String itemNumber) {

		Collection<InventoryItem> items = repository.findByItemNumber(itemNumber);
		logger.info("get:" + items);

		return items;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public InventoryItem delete(@RequestParam(value = "id") String id) {

		InventoryItem item = repository.findOne(id);
		logger.info("delete :" + item);

		repository.delete(item);
		return item;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public Collection<InventoryItem> update(@RequestParam(value = "item", required = true) String itemNumber,
			@RequestParam(value = "location") String location, @RequestParam(value = "total") Integer total,
			@RequestParam(value = "booked") Integer booked) {

		Collection<InventoryItem> items = repository.findByItemNumber(itemNumber);
		logger.info("update:" + items);

		if (items.isEmpty())
			items.add(new InventoryItem());

		for (InventoryItem item : items) {
			item.setItemNumber(itemNumber);
			if (total != null)
				item.setTotalQuantity(total);
			if (booked != null)
				item.setBookedQuantity(booked);
			if (location != null)
				item.setLocation(location);
		}

		return repository.save(items);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Collection<InventoryItem> update(InventoryItem item) {
		Collection<InventoryItem> found = repository.findByItemNumber(item.getItemNumber());
		logger.info("update.post:" + found);

		if (!found.isEmpty()) {
			for (InventoryItem inventoryItem : found) {
				inventoryItem.setBookedQuantity(item.getBookedQuantity());
				inventoryItem.setTotalQuantity(item.getTotalQuantity());
				inventoryItem.setLocation(item.getLocation());
			}
		} else
			found.add(item);

		return repository.save(found);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	private void handleHttpClientException(HttpServletResponse response) throws IOException {
		logger.error("exception ignored: " + response.getStatus() + " response:" + response);
	}

}
