package com.jiaop.sugar.bean;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by jiaop on 2016/6/11.
 */
public class Goods extends SugarRecord implements Serializable {
    /**
     * 货品编号
     */
    //强制按照你的规定的名字来创建表中对应的字段名字，所以这里的skuId在Goods表中的字段名就不是默认的sku_id了，而是你自己给的sku_ID
    @Column(name = "sku_ID", unique = true)
    private String skuId;
    /**
     * 商品编号
     */
    private String spuId;
    /**
     * 规格
     */
    @Ignore//强调这个属性在表中不要创建对应的字段
    private String specValue;
    /**
     * 货品名称
     */
    private String name;
    /**
     * 货号
     */
    private String bn;
    /**
     * 成本价,进价
     */
    private BigDecimal cost;
    /**
     * 售价
     */
    private BigDecimal price;
    /**
     * 子类
     */
    private Operator operator;
    public Operator getOperator() {
        return operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
