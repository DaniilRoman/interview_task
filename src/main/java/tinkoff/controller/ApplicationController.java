package tinkoff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinkoff.entity.response.ApplicationResponse;
import tinkoff.service.interfaces.ApplicationService;

@RestController
@RequestMapping("api/v1/application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/latest")
    public ResponseEntity<ApplicationResponse> getLatestByContactId(@RequestParam("contact_id") Integer contactId) {
        return ResponseEntity.ok(new ApplicationResponse(
                applicationService.findLatestByContactId(contactId),
                contactId));
    }


}
