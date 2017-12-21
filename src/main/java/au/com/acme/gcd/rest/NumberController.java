package au.com.acme.gcd.rest;

import au.com.acme.gcd.model.Message;
import au.com.acme.gcd.model.Result;
import au.com.acme.gcd.service.MessagingService;
import au.com.acme.gcd.service.PersistenceService;
import au.com.acme.gcd.utils.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Number Service", description = "Read from JMS queue /database")
@RestController
public class NumberController {

    private static final Logger LOG = LoggerFactory.getLogger(NumberController.class);

    @Autowired
    private MessagingService messagingService;

    @Autowired
    private PersistenceService persistenceService;


    @ApiOperation(value = "Push a number to jms queue", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Result.class),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/v1/api/push")
    public ResponseEntity<Result> push(@ApiParam("m") @RequestBody(required = true) Message m) {
        LOG.info("input values - {}", m);
        Validator.validateAndConvert(m.getNumber());
        long id = messagingService.send(m);
        Result res = new Result();
        res.setResponse("Saved with id =" + id);
        return ResponseEntity.ok(res);
    }


    @ApiOperation(value = "Pull a number from jms queue", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Message.class),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/v1/api/pull")
    public ResponseEntity<Message> pull() {
        Message message = messagingService.pull();
        return ResponseEntity.ok(message);
    }


    @ApiOperation(value = "Fetch a number from database based on id", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Message.class),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
    @GetMapping("/v1/api/read")
    public ResponseEntity<Message> read(@ApiParam(name = "id") @RequestParam(name = "id",required = true) long id) {
        au.com.acme.gcd.model.persistence.Number number = persistenceService.getInput(id);
        Message m = new Message();
        m.setNumber(number.getN());
        return ResponseEntity.ok(m);
    }
}
