package in.jp.trial.sb.sample.application.service;

import in.jp.trial.sb.sample.application.entity.SampleApplicationEntity;
import in.jp.trial.sb.sample.application.model.SampleApplicationModel;
import in.jp.trial.sb.sample.application.repository.SampleApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRES_NEW)
public class SampleApplicationService {

    private SampleApplicationRepository sampleApplicationRepository;
    public SampleApplicationService (SampleApplicationRepository sampleApplicationRepository) {
        this.sampleApplicationRepository = sampleApplicationRepository;
    }

    public SampleApplicationModel getSampleApplicationModel(int id){
        SampleApplicationModel model = convertEntityToModel(sampleApplicationRepository.findById(id).get());
        return model;
    }

    public SampleApplicationModel createSampleApplicationModel(SampleApplicationModel model){
        model.setId(null);
        SampleApplicationModel persistedModel = convertEntityToModel(sampleApplicationRepository.save(convertModelToEntity(model)));
        return persistedModel;
    }

    public SampleApplicationModel updateSampleApplicationModel(SampleApplicationModel model) {
        boolean modelExist = sampleApplicationRepository.existsById(model.getId());
        SampleApplicationEntity entity = convertModelToEntity(model);
        if(!modelExist){
           entity.setId(null);
        }
        SampleApplicationModel persistedModel = convertEntityToModel(sampleApplicationRepository.save(entity));
        return persistedModel;
    }

    public int deleteSampleApplicationModel(int id) {
        sampleApplicationRepository.deleteById(id);
        return 0;
    }

    private SampleApplicationModel convertEntityToModel(SampleApplicationEntity entity) {
        SampleApplicationModel model = new SampleApplicationModel();
        model.setId(entity.getId());
        model.setDescription(entity.getDescription());
        return model;
    }

    private SampleApplicationEntity convertModelToEntity(SampleApplicationModel model) {
        SampleApplicationEntity entity = new SampleApplicationEntity();
        entity.setId(model.getId());
        entity.setDescription(model.getDescription());
        return entity;
    }
}
