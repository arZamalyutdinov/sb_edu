package models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
@Table(name = "components")
public class Component implements Serializable {
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

        public pk() {
        }

        @ManyToOne(fetch = FetchType.EAGER)
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            pk pk = (pk) o;
            return getId().equals(pk.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId());
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
