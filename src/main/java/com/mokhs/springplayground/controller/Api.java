package com.mokhs.springplayground.controller;

import com.mokhs.springplayground.ItemResponse;
import com.mokhs.springplayground.ItemUpdateCommand;
import com.mokhs.springplayground.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class Api {

    private final RestTemplateService restTemplateService;

    private String itemName = "item";

    @GetMapping("/item")
    public ItemResponse getItem() {

        return new ItemResponse(this.itemName);
    }


    @GetMapping
    public ItemResponse getItemFromApi() {

        return restTemplateService.getItem();
    }


    @PostMapping("/item/{itemId}")
    public ItemResponse updateItem(@PathVariable Long itemId,
                                   @RequestBody ItemUpdateCommand dto) {
        log.info("itmeId : {}", itemId);
        return new ItemResponse(dto.getName());
    }

    @PostMapping("/{itemId}")
    public ItemResponse updateItemFromApi(@PathVariable Long itemId,
                                          @RequestBody ItemUpdateCommand dto) {

        return restTemplateService.updateItem(itemId, dto);
    }


}
