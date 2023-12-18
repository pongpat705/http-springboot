package th.co.priorsolution.springboot.novice.component;

import org.springframework.stereotype.Component;
import th.co.priorsolution.springboot.novice.entity.ActorEntity;
import th.co.priorsolution.springboot.novice.model.ActorResponseModel;

@Component
public class ActorModelTransformComponent {
    public ActorResponseModel transFormEntityToModel(ActorEntity actorEntity) {
        ActorResponseModel responseModel = new ActorResponseModel();
        responseModel.setName(actorEntity.getFirstName());
        responseModel.setLastName(actorEntity.getLastName());
        return responseModel;
    }
}
