package com.medicine.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zaolaotou
 * @since 2019-09-28
 */
@TableName("illness_info")
public class IllnessInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 疾病基础概况
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 发病时间
     */
    @TableField("disease_time")
    private Date diseaseTime;
    /**
     * 开始症状
     */
    @TableField("begin_symptoms")
    private String beginSymptoms;
    /**
     * 诱发原因
     */
    @TableField("induce_cause")
    private String induceCause;
    /**
     * 前期就诊科室
     */
    @TableField("old_department")
    private String oldDepartment;
    /**
     * 曾用抗病毒药物
     */
    @TableField("antiviral_drugs")
    private String antiviralDrugs;
    /**
     * 抗病毒药物使用时间（几天前）剂量mg/天数
     */
    @TableField("antiviral_time")
    private String antiviralTime;
    /**
     * 曾用神经营养药物
     */
    @TableField("neurotrophic_drugs")
    private String neurotrophicDrugs;
    /**
     * 神经营养药物使用时间（几天前）剂量mg/天数
     */
    @TableField("neurotrophic_time")
    private String neurotrophicTime;
    /**
     * 曾用镇痛药物
     */
    @TableField("analgesic_drugs")
    private String analgesicDrugs;
    /**
     * 镇痛药物使用时间（几天前）剂量mg/天数
     */
    @TableField("analgesic_time")
    private String analgesicTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDiseaseTime() {
        return diseaseTime;
    }

    public void setDiseaseTime(Date diseaseTime) {
        this.diseaseTime = diseaseTime;
    }

    public String getBeginSymptoms() {
        return beginSymptoms;
    }

    public void setBeginSymptoms(String beginSymptoms) {
        this.beginSymptoms = beginSymptoms;
    }

    public String getInduceCause() {
        return induceCause;
    }

    public void setInduceCause(String induceCause) {
        this.induceCause = induceCause;
    }

    public String getOldDepartment() {
        return oldDepartment;
    }

    public void setOldDepartment(String oldDepartment) {
        this.oldDepartment = oldDepartment;
    }

    public String getAntiviralDrugs() {
        return antiviralDrugs;
    }

    public void setAntiviralDrugs(String antiviralDrugs) {
        this.antiviralDrugs = antiviralDrugs;
    }

    public String getAntiviralTime() {
        return antiviralTime;
    }

    public void setAntiviralTime(String antiviralTime) {
        this.antiviralTime = antiviralTime;
    }

    public String getNeurotrophicDrugs() {
        return neurotrophicDrugs;
    }

    public void setNeurotrophicDrugs(String neurotrophicDrugs) {
        this.neurotrophicDrugs = neurotrophicDrugs;
    }

    public String getNeurotrophicTime() {
        return neurotrophicTime;
    }

    public void setNeurotrophicTime(String neurotrophicTime) {
        this.neurotrophicTime = neurotrophicTime;
    }

    public String getAnalgesicDrugs() {
        return analgesicDrugs;
    }

    public void setAnalgesicDrugs(String analgesicDrugs) {
        this.analgesicDrugs = analgesicDrugs;
    }

    public String getAnalgesicTime() {
        return analgesicTime;
    }

    public void setAnalgesicTime(String analgesicTime) {
        this.analgesicTime = analgesicTime;
    }

    @Override
    public String toString() {
        return "IllnessInfo{" +
        ", id=" + id +
        ", diseaseTime=" + diseaseTime +
        ", beginSymptoms=" + beginSymptoms +
        ", induceCause=" + induceCause +
        ", oldDepartment=" + oldDepartment +
        ", antiviralDrugs=" + antiviralDrugs +
        ", antiviralTime=" + antiviralTime +
        ", neurotrophicDrugs=" + neurotrophicDrugs +
        ", neurotrophicTime=" + neurotrophicTime +
        ", analgesicDrugs=" + analgesicDrugs +
        ", analgesicTime=" + analgesicTime +
        "}";
    }
}
