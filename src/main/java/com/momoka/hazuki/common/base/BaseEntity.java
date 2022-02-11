package com.momoka.hazuki.common.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@MappedSuperclass
@SQLDelete(sql = "update demo set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class BaseEntity extends Base implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @CreationTimestamp
    public Date createTime = new Date();

    public Boolean deleted = false;

    @Override
    public Map<String, Object> map() {
        return super.map();
    }

    public Map<String, Object> detailMap() {
        return super.map();
    }
}
