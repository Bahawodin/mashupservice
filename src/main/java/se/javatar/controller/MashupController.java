package se.javatar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.javatar.domain.MashupRequest;
import se.javatar.service.MashupService;

/**
 * @author Ajmal Bahawodin {@literal <mailto:ajmal@javatar.se/>}
 */
@RestController
public class MashupController {

    @Autowired
    MashupService mashupService;

    @RequestMapping("/mashup/{mbid}")
    public MashupRequest getMashupRequest(@PathVariable String mbid) {
        System.out.println(mbid);
        return mashupService.getRequest(mbid);
    }
}
