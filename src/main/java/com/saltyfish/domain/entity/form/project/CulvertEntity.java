package com.saltyfish.domain.entity.form.project;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 涵洞
 */

@Entity
@Table(name = "culvert", schema = "", catalog = "exciting")
public class CulvertEntity extends SuperBean {
    private static final Long serialVersionUID = -948052998025454234L;
    private String watercourseLocation;         //所在河道
    private String culvertModel;                //涵洞类型
    //净断面尺寸
    private BigDecimal sectionHeight;            //高
    private BigDecimal sectionWidth;         //宽

    private BigDecimal length;               //洞身总长
    private String holeModel;           //洞身类型

    private String doorMaterial;         //洞口闸门材质
    private String hoistModel;          //洞口启闭机类型
    private String holeMaterial;             //洞身材质
    private String image;           //照片

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWatercourseLocation() {
        return watercourseLocation;
    }

    public void setWatercourseLocation(String watercourseLocation) {
        this.watercourseLocation = watercourseLocation;
    }

    public String getCulvertModel() {
        return culvertModel;
    }

    public void setCulvertModel(String culvertModel) {
        this.culvertModel = culvertModel;
    }

    public BigDecimal getSectionHeight() {
        return sectionHeight;
    }

    public void setSectionHeight(BigDecimal sectionHeight) {
        this.sectionHeight = sectionHeight;
    }

    public BigDecimal getSectionWidth() {
        return sectionWidth;
    }

    public void setSectionWidth(BigDecimal sectionWidth) {
        this.sectionWidth = sectionWidth;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public String getHoleModel() {
        return holeModel;
    }

    public void setHoleModel(String holeModel) {
        this.holeModel = holeModel;
    }

    public String getDoorMaterial() {
        return doorMaterial;
    }

    public void setDoorMaterial(String doorMaterial) {
        this.doorMaterial = doorMaterial;
    }

    public String getHoistModel() {
        return hoistModel;
    }

    public void setHoistModel(String hoistModel) {
        this.hoistModel = hoistModel;
    }

    public String getHoleMaterial() {
        return holeMaterial;
    }

    public void setHoleMaterial(String holeMaterial) {
        this.holeMaterial = holeMaterial;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}