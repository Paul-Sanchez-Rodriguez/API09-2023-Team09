package com.example.MsAttorney.web;

import com.example.MsAttorney.domain.dto.AttorneyPublicDto;
import com.example.MsAttorney.domain.dto.AttorneyRequestDto;
import com.example.MsAttorney.domain.dto.AttorneyResponseDto;
import com.example.MsAttorney.service.AttorneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/attorney", produces = "application/json")
@RequiredArgsConstructor
public class AttorneyController {


    private final AttorneyService attorneyService;

    @GetMapping(value = "/v1/InfoAttorney")
    public Flux<AttorneyResponseDto> findAll(){

        //throw new UnsupportedOperationException("Endpoint obsoleto");

        /**URI newEndpoint = URI.create("/attorney/v2/InfoAttorney");
         return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
         .location(newEndpoint)
         .build();**/
        return this.attorneyService.findAll();
    }


    @GetMapping(value = "/v2/InfoAttorney")
    public Flux<AttorneyPublicDto> findAllpublic(){

        return this.attorneyService.findAll()
                .map(attorney -> {
                    AttorneyPublicDto attorneyPublicDto = new AttorneyPublicDto();
                    attorneyPublicDto.setName(attorney.getName());
                    attorneyPublicDto.setFatherlastname(attorney.getFatherlastname());
                    attorneyPublicDto.setMotherlastname((attorney.getMotherlastname()));
                    return attorneyPublicDto;
                });
    }

    @GetMapping(value = "/inactive")
    public Flux<AttorneyResponseDto> findAllInactive(){
        return this.attorneyService.findAllInactive();
    }

    @GetMapping(value = "/{id}")
    public Mono<AttorneyResponseDto> findById(@PathVariable Integer id){
        return this.attorneyService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Mono<AttorneyResponseDto> create(@RequestBody AttorneyRequestDto dto){
        dto.setActive("A");
        return this.attorneyService.create(dto);
    }
    @PutMapping(value = "/{id}")
    public Mono<AttorneyResponseDto> update(@RequestBody AttorneyRequestDto dto, @PathVariable Integer id){
        return this.attorneyService.update(dto,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id){

        return this.attorneyService.delete(id);
    }

}
