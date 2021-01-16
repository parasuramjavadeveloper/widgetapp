package com.example.apipoc.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Parasuram
 */
@Entity
@Table(name = "Widget")
public class Widget implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer xAxis;
    private Integer yAxis;
    private Integer zIndex;
    private Integer width;
    private Integer height;
    private Date createdDate;
    private Date lastModifiedDate;

    public Widget() {
    }

    public Widget(Integer xAxis, Integer yAxis, Integer zIndex,
                  Integer width, Integer height,
                  Date createdDate, Date lastModifiedDate) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getxAxis() {
        return xAxis;
    }

    public void setxAxis(Integer xAxis) {
        this.xAxis = xAxis;
    }

    public Integer getyAxis() {
        return yAxis;
    }

    public void setyAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Widget)) return false;
        Widget widget = (Widget) o;
        return Objects.equals(getId(), widget.getId()) &&
                Objects.equals(getxAxis(), widget.getxAxis()) &&
                Objects.equals(getyAxis(), widget.getyAxis()) &&
                Objects.equals(getzIndex(), widget.getzIndex()) &&
                Objects.equals(getWidth(), widget.getWidth()) &&
                Objects.equals(getHeight(), widget.getHeight()) &&
                Objects.equals(getCreatedDate(), widget.getCreatedDate()) &&
                Objects.equals(getLastModifiedDate(), widget.getLastModifiedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getxAxis(), getyAxis(), getzIndex(), getWidth(), getHeight(), getCreatedDate(), getLastModifiedDate());
    }
}
