package com.test.shortner.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.shortner.services.ShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class ShortnerController {

    @Autowired
    ShortnerService shortnerService;

    @PostMapping("shorten")
    public ResponseEntity<?> shortenURL(@RequestBody String longURL ) throws NoSuchAlgorithmException, IOException {

        String shortURL = shortnerService.shortify(longURL);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode shortURLNode = mapper.createObjectNode();
        shortURLNode.put("shortURL",shortURL);
        ResponseEntity<JsonNode> response = new ResponseEntity<JsonNode>(shortURLNode, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{shortURL}")
    public ResponseEntity<?> getLongURL(@PathVariable String shortURL ){

        String longURL = shortnerService.longify(shortURL);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode shortURLNode = mapper.createObjectNode();
        shortURLNode.put("longURL",longURL);
        ResponseEntity<JsonNode> response = new ResponseEntity<JsonNode>(shortURLNode, HttpStatus.OK);
        return response;
    }
}
