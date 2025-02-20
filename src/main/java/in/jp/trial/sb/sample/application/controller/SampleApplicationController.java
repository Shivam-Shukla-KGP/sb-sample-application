package in.jp.trial.sb.sample.application.controller;

import in.jp.trial.sb.sample.application.model.SampleApplicationModel;
import in.jp.trial.sb.sample.application.service.SampleApplicationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("sampleApplicationController")
public class SampleApplicationController {

    private SampleApplicationService sampleApplicationService;

    public SampleApplicationController(SampleApplicationService sampleApplicationService){
        this.sampleApplicationService = sampleApplicationService;
    }

    @GetMapping
    public ResponseEntity<SampleApplicationModel> sampleApplicationGetMap(@RequestParam("id") int id){
        SampleApplicationModel model = this.sampleApplicationService.getSampleApplicationModel(id);
        ResponseEntity<SampleApplicationModel> responseEntity =
                ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body(model);
        return responseEntity;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity sampleApplicationPostMap(@RequestBody SampleApplicationModel model){
        SampleApplicationModel returnModel = this.sampleApplicationService.createSampleApplicationModel(model);
        ResponseEntity response = ResponseEntity.ok()
                        .body(returnModel);
        return response;
    }

    @PutMapping
    public ResponseEntity sampleApplicationPutMap(@RequestBody SampleApplicationModel model){
        SampleApplicationModel persistedModel = this.sampleApplicationService.updateSampleApplicationModel(model);
        URI location = null;
        if(persistedModel.getId() != model.getId()) {
            location = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("?id={id}")
                    .buildAndExpand(persistedModel.getId())
                    .toUri();
        }
        ResponseEntity response = persistedModel.getId() == model.getId() ?
                ResponseEntity.noContent().build() : ResponseEntity.created(location).build();
        return response;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sampleApplicationDeleteMap(@RequestParam("id") int id){
        this.sampleApplicationService.deleteSampleApplicationModel(id);
    }

}
