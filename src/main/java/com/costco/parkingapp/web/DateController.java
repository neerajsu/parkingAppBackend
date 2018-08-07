package com.costco.parkingapp.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/date")
public class DateController {

	@RequestMapping(path = "/local-date-time",method = RequestMethod.GET)
	public LocalDateTime todayLocalDate() {
		return LocalDateTime.now(); // "2018-07-29T05:39:37.195" in JSON
	}

	@RequestMapping(path = "/offset-date-time", method = RequestMethod.GET)
	public OffsetDateTime todayOffsetDateTime() {
		return OffsetDateTime.now(); //"2018-07-29T05:39:15.781-07:00" in JSON
	}
	
	@RequestMapping(path = "/java-date", method = RequestMethod.GET)
	public Date todayJavaDate() {
		return new Date(); //"2018-07-29T12:38:41.643+0000" in JSON
	}
}
