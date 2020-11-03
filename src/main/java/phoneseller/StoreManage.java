package phoneseller;

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

        System.out.println("***** 배송 요청 중 *****");

        if("Payed".equals(process)) {
            this.setProcess("Shipped");
            Shipped shipped = new Shipped();
            BeanUtils.copyProperties(this, shipped);
            shipped.publishAfterCommit();

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




}
