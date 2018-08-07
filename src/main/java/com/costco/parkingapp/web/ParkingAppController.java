package com.costco.parkingapp.web;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.costco.parkingapp.common.CommonConstants;
import com.costco.parkingapp.domain.ParkingSpot;
import com.costco.parkingapp.exception.APIException;
import com.costco.parkingapp.exception.ErrorInfo;
import com.costco.parkingapp.repo.ParkingSpotRepository;
import com.costco.parkingapp.service.MessagingService;
import com.costco.parkingapp.service.ParkingSpotService;

@RestController
@RequestMapping(path = "parkingspot/")
public class ParkingAppController {

	private static final Logger LOG = LogManager.getLogger(ParkingAppController.class.getName());

	@Autowired
	ParkingSpotRepository parkingSpotsRecovery;
	
	@Autowired
	ParkingSpotService parkingSpotService;
	
	@Autowired
	MessagingService messagingService;
	
	@Autowired
	ParkingSpotRepository parkingSpotRepository;

	protected ParkingAppController() {
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/sendsms/{phoneNumber}")
	@ResponseStatus(HttpStatus.CREATED)
	public void sendSms(@PathVariable String phoneNumber){
		messagingService.sendSms("573-202-4197", "Hello world");
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createParkingSpot(@RequestBody @Validated ParkingSpotDto parkingSpotDto) {
		parkingSpotService.createParkingSpot(parkingSpotDto.getParkingSpot(), parkingSpotDto.getParkingSpotOwner(),
				parkingSpotDto.getReleasedOn(), parkingSpotDto.getRevokeOn(), parkingSpotDto.getParkingStatus());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<ParkingSpotDto> getAllParkingSpots(Pageable pageable) {
		Page<ParkingSpot> parkingSpotPage = parkingSpotRepository.findAll(pageable);
		List<ParkingSpotDto> parkingSpotDtoList = parkingSpotPage.getContent().stream()
				.map(parkingSpot -> toDto(parkingSpot)).collect(Collectors.toList());
		return new PageImpl<ParkingSpotDto>(parkingSpotDtoList, pageable, parkingSpotPage.getTotalPages());
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{spotNo}")
	public ParkingSpotDto getParkingSpot(@PathVariable String spotNo) {
		ParkingSpot parkingSpot = parkingSpotRepository.findBySpotNo(spotNo);
		if(parkingSpot == null) {
			throw new APIException(HttpStatus.NOT_FOUND, "Parking Spot with spotNo " +spotNo+" does not exist");
		} else {
			return toDto(parkingSpot);
		}
	}
	
	/*TODO : check ask for requirements on how to validate without user/roles so this method can be uncommented.
	Until then it is commented since its a security issue.*/
//	@RequestMapping(method = RequestMethod.DELETE, path = "/{spotNo}")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void delete(@PathVariable(value = "spotNo") String spotNo) {
//		ParkingSpot parkingSpot = parkingSpotRepository.findBySpotNo(spotNo);
//		if(parkingSpot == null) {
//			throw new APIException(HttpStatus.NOT_FOUND, "Parking Spot with spotNo: " +spotNo+" does not exist");
//		} else {
//			parkingSpotRepository.delete(parkingSpot);
//		}
//	}

	private ParkingSpotDto toDto(ParkingSpot parkingSpot) {
		return new ParkingSpotDto(parkingSpot.getSpotNo(), parkingSpot.getSpotOwner(), parkingSpot.getReleasedOn(),
				parkingSpot.getRevokeOn(),
				parkingSpotService.getParkingStatusFromStatusCode(parkingSpot.getStatusCode()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> exceptionHandlerForValidation(MethodArgumentNotValidException exception) {
		
		LOG.error("MethodArgumentNotValidException occured ::" ,exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, "Validation of request body failed", HttpStatus.BAD_REQUEST.getReasonPhrase()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorInfo> exceptionHandlerForApiException(APIException exception) {
		
		LOG.error("APIException occured ::" ,exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, exception.getMessage(), exception.getStatus().getReasonPhrase()),
				exception.getStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
		
		LOG.error("Exception occured ::" ,exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
