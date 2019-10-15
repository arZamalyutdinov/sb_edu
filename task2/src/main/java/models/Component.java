package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "components")
public class Component implements Serializable {
//    @Id
    @EmbeddedId
    private pk id;

    @Column
    private String name;

    @Column
    private Boolean status;

    @AllArgsConstructor
    @Embeddable
    public static class pk implements Serializable {
        @Column
        private Integer id;

        public pk() {}
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "deviceid")
        private Device device;

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setDevice(Device device) {
            this.device = device;
        }
    }

    public void setDevice(Device device) {
        this.id.setDevice(device);
    }

    public Component(Integer id, String name, Boolean status) {
        this.id = new pk();
        this.id.setId(id);
        this.name = name;
        this.status = status;
    }
}
