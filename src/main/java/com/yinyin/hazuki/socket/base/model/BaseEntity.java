package com.yinyin.hazuki.socket.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinyin.hazuki.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
//MappedSuperclass 用在父类上面。当这个类肯定是父类时，加此标注。
// 如果改成@Entity，则继承后，多个类继承，只会生成一个表，而不是多个继承，生成多个表
@MappedSuperclass
public class BaseEntity extends Base implements Serializable {

    public final static String PREFIX = "hazuki";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
