package com.yinyin.hazuki.socket._base.model;

import com.yinyin.hazuki.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class BaseEntity extends Base implements Serializable {

    public final static String PREFIX = "hazuki_";

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    public long sort = System.currentTimeMillis();

    //创建时间
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT)
    @CreationTimestamp
    public Date createTime = new Date();

    //更新时间
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT)
    @UpdateTimestamp
    public Date updateTime = new Date();

    @JsonIgnore
    public Boolean deleted = false;

}
