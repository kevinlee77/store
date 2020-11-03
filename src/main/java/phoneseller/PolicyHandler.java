package phoneseller;

import phoneseller.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    StoreManageRepository storeManageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCompleted_OrderReceive(@Payload PayCompleted payCompleted){

        if(payCompleted.isMe()){
            System.out.println("##### listener OrderReceive : " + payCompleted.toJson());

            System.out.println("store_policy_paycompleted 주문수락");
            StoreManage storeManage = new StoreManage();
            storeManage.setOrderId(payCompleted.getId());
            storeManageRepository.save(storeManage);
        }
    }

}
