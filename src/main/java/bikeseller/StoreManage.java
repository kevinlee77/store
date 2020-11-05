package bikeseller;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="StoreManage_table")
public class StoreManage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String address;
    private String item;
    private String process;

    @PostPersist
    public void onPostPersist(){
        System.out.println("***** 배송 요청 *****");

        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 결제 완료 상태일 때 배송 처리
        // (PolicyHandler에서 Listener를 통해 이벤트 상태 인지하여 StoreRepository.save(storemanage)를 통해 호출)
        if("Payed".equals(process)) {
            setProcess("Shipped");
            Shipped shipped = new Shipped();
            BeanUtils.copyProperties(this, shipped);
            shipped.publish();

            System.out.println(toString());
            System.out.println("***** 배송 시작 *****");
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }


    @Override
    public String toString() {
        return "StoreManage{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", address='" + address + '\'' +
                ", item='" + item + '\'' +
                ", process='" + process + '\'' +
                '}';
    }
}
