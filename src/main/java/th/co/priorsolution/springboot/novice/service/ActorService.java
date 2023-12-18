package th.co.priorsolution.springboot.novice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import th.co.priorsolution.springboot.novice.component.ActorModelTransformComponent;
import th.co.priorsolution.springboot.novice.component.security.model.AuthenticatedUsers;
import th.co.priorsolution.springboot.novice.entity.ActorEntity;
import th.co.priorsolution.springboot.novice.logging.model.ComplexLog;
import th.co.priorsolution.springboot.novice.model.ActorResponseModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.repository.ActorRepository;

import java.util.Optional;

@Service
@Slf4j
public class ActorService {

    private ActorRepository actorRepository;
    private ActorModelTransformComponent actorModelTransformComponent;

    public ActorService(ActorRepository actorRepository, ActorModelTransformComponent actorModelTransformComponent) {
        this.actorRepository = actorRepository;
        this.actorModelTransformComponent = actorModelTransformComponent;
    }

    public ResponseModel<ActorResponseModel> getActorById(String number) {
         ResponseModel<ActorResponseModel> result = new ResponseModel<>();
        result.setStatus(404);
        result.setDescription("employee not found");

        AuthenticatedUsers x = (AuthenticatedUsers) SecurityContextHolder.getContext().getAuthentication();


        log.info("getActorById {}", number
                , ComplexLog.builder().key(x.getName()).build().toMap()
        );

        try{
            Long id = Long.parseLong(number);
            Optional<ActorEntity> optionalActor = this.actorRepository.findById(id);

            if(optionalActor.isPresent()){
                ActorEntity actorEntity = optionalActor.get();
                ActorResponseModel data = this.actorModelTransformComponent.transFormEntityToModel(actorEntity);
                result.setData(data);
                result.setStatus(200);
                result.setDescription("");
                log.info("getActorById {} response 200", number, ComplexLog.builder().key(x.getName()).data(data).build().toMap()
                );
            }
        } catch (Exception e) {
            log.info("getActorById error {}",e.getMessage(), ComplexLog.builder().key(x.getName()).build().toMap());
            result.setStatus(500);
            result.setDescription(e.getMessage());
        }

        return result;
    }
}
