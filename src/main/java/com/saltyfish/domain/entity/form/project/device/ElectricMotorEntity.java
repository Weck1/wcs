package com.saltyfish.domain.entity.form.project.device;

import com.saltyfish.domain.entity.base.SuperBean;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 电动机
 */
@Entity
@Table(name = "electric_motor", schema = "", catalog = "exciting")
public class ElectricMotorEntity extends SuperBean {
    private static final Long serialVersionUID = -1987258246729432244L;
    @NotEmpty
    private Integer projectId;      //工程id
    @NotEmpty
    private String projectCode;
    private String model;           //型号
    private Integer count;          //台数
    private BigDecimal power;            //功率
    private Timestamp factoryTime;      //出厂时间

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public Timestamp getFactoryTime() {
        return factoryTime;
    }

    public void setFactoryTime(Timestamp factoryTime) {
        this.factoryTime = factoryTime;
    }
}