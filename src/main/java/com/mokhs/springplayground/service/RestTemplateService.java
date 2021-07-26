package com.mokhs.springplayground.service;

import com.mokhs.springplayground.ItemResponse;
import com.mokhs.springplayground.ItemUpdateCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    public ItemResponse getItem() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/v1/item")
                .encode() // query params encode
                .build()
                .toUri();

        log.info(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

//        String result = restTemplate.getForObject(uri, String.class);
        ResponseEntity<ItemResponse> result = restTemplate.getForEntity(uri, ItemResponse.class);

        log.info("{} {}",result.getBody(), result.getStatusCode());


        return result.getBody();
    }

    public ItemResponse updateItem(Long id, ItemUpdateCommand dto) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/v1/item/{itemId}")
                .encode()
                .build()
                .expand(id)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ItemResponse> result = restTemplate.postForEntity(uri, dto, ItemResponse.class);

        log.info("{} {}", result.getStatusCode(), result.getBody());

        return result.getBody();

    }
}
