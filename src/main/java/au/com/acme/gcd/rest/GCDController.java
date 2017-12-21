package au.com.acme.gcd.rest;

import au.com.acme.gcd.service.GCDService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.acme.gcd.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "GCD Calculator", description = "Returns the GCD of 2 numbers")
@RestController
public class GCDController {
	private static final Logger log = LoggerFactory.getLogger(GCDController.class);

	@Autowired
	private GCDService service;

	@ApiOperation(value = "Get GCD", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Result.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	@GetMapping("/v1/api/gcd")
	public ResponseEntity<Result> getGCD(@ApiParam("A") @RequestParam(required = true, value = "a") String a,
			@ApiParam("B") @RequestParam(required = true, value = "b") String b) {
		log.info("input values - {} , {}", a, b);
		Result result = service.calculate(a,b);
		return ResponseEntity.ok(result);
	}
}
