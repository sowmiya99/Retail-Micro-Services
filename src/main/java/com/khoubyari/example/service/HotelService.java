package com.khoubyari.example.service;

import com.khoubyari.example.domain.Hotel;
import com.khoubyari.example.dao.jpa.HotelRepository;

import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class HotelService {

    //private static final Logger log = LoggerFactory.getLogger(HotelService.class);
	private static Logger logger = Logger.getLogger("retailone");

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public HotelService() {
    }

    public Hotel createHotel(Hotel hotel) {
    	logger.info(":::::Inside Hotel Service to create hotel:::::");
        return hotelRepository.save(hotel);
    }

    public Hotel getHotel(long id) {
    	logger.info(":::::Inside Hotel Service to get hotels:::::");
        return hotelRepository.findOne(id);
    }

    public void updateHotel(Hotel hotel) {
    	logger.info(":::::Inside Hotel Service to update hotel:::::");
        hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.delete(id);
    }

    //http://goo.gl/7fxvVf
    public Page<Hotel> getAllHotels(Integer page, Integer size) {
    	logger.info(":::::Inside Hotel Service to get all hotel:::::");
        Page pageOfHotels = hotelRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("Khoubyari.HotelService.getAll.largePayload");
        }
        return pageOfHotels;
    }
}
