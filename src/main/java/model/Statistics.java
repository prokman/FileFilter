package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Statistics {
    private BigInteger numOfWritеenInt;
    private Integer numOfWriteenFloat;
    private Integer numOfWriteenString;
    private BigInteger intMinValue;
    private Double floatMinValue;
    private BigInteger intMaxValue;
    private Double floatMaxValue;
    private BigInteger intSum;
    private Double floatSum;
    private BigDecimal intAvg;
    private Double floatAvg;
    private Integer shortestStringSize;
    private Integer longestStringSize;

    //BigInteger numOfWritеenInt, Integer numOfWriteenFloat, Integer numOfWriteenString, BigInteger intMinValue, Double floatMinValue, BigInteger intMaxValue, Double floatMaxValue, BigInteger intSum, Double floatSum, BigDecimal intAvg, Double floatAvg, Integer shortestStringSize, Integer longestStringSize
    public Statistics() {
        this.numOfWritеenInt = BigInteger.valueOf(0);
        this.numOfWriteenFloat = 0;
        this.numOfWriteenString = 0;
        this.intMinValue = null;
        this.floatMinValue = null;
        this.intMaxValue = null;
        this.floatMaxValue = null;
        this.intSum = BigInteger.valueOf(0);
        this.floatSum = 0D;
        this.intAvg = BigDecimal.valueOf(0);
        this.floatAvg = 0D;
        this.shortestStringSize = null;
        this.longestStringSize = null;
    }


    public BigInteger getNumOfWritеenInt() {
        return numOfWritеenInt;
    }

    public void setNumOfWritеenInt(BigInteger numOfWritеenInt) {
        this.numOfWritеenInt = numOfWritеenInt;
    }

    public Integer getNumOfWriteenFloat() {
        return numOfWriteenFloat;
    }

    public void setNumOfWriteenFloat(Integer numOfWriteenFloat) {
        this.numOfWriteenFloat = numOfWriteenFloat;
    }

    public Integer getNumOfWriteenString() {
        return numOfWriteenString;
    }

    public void setNumOfWriteenString(Integer numOfWriteenString) {
        this.numOfWriteenString = numOfWriteenString;
    }

    public BigInteger getIntMinValue() {
        return intMinValue;
    }

    public void setIntMinValue(BigInteger intMinValue) {
        this.intMinValue = intMinValue;
    }

    public Double getFloatMinValue() {
        return floatMinValue;
    }

    public void setFloatMinValue(Double floatMinValue) {
        this.floatMinValue = floatMinValue;
    }

    public BigInteger getIntMaxValue() {
        return intMaxValue;
    }

    public void setIntMaxValue(BigInteger intMaxValue) {
        this.intMaxValue = intMaxValue;
    }

    public Double getFloatMaxValue() {
        return floatMaxValue;
    }

    public void setFloatMaxValue(Double floatMaxValue) {
        this.floatMaxValue = floatMaxValue;
    }

    public BigInteger getIntSum() {
        return intSum;
    }

    public void setIntSum(BigInteger intSum) {
        this.intSum = intSum;
    }

    public Double getFloatSum() {
        return floatSum;
    }

    public void setFloatSum(Double floatSum) {
        this.floatSum = floatSum;
    }

    public BigDecimal getIntAvg() {
        return intAvg;
    }

    public void setIntAvg(BigDecimal intAvg) {
        this.intAvg = intAvg;
    }

    public Double getFloatAvg() {
        return floatAvg;
    }

    public void setFloatAvg(Double floatAvg) {
        this.floatAvg = floatAvg;
    }

    public Integer getShortestStringSize() {
        return shortestStringSize;
    }

    public void setShortestStringSize(Integer shortestStringSize) {
        this.shortestStringSize = shortestStringSize;
    }

    public Integer getLongestStringSize() {
        return longestStringSize;
    }

    public void setLongestStringSize(Integer longestStringSize) {
        this.longestStringSize = longestStringSize;
    }
}
