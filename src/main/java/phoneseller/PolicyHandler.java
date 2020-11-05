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

        // 카프카 리스너를 통해 결제 완료 상태일 때 -> 대리점에서 주문 수락(배송) 이벤트 처리 진행
        if(payCompleted.isMe()){
            System.out.println("store_policy_wheneverPayCompleted_OrderReceive_onPostPersist");

            StoreManage storeManage = new StoreManage();
            storeManage.setOrderId(payCompleted.getOrderId());
            storeManage.setProcess("Payed");
            storeManageRepository.save(storeManage);

            System.out.println("##### listener OrderReceive : " + payCompleted.toJson());
        }

    }

}
