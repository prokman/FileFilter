package service;

import model.Statistics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class StatService {
    Statistics statistics = new Statistics();


    public StatService() {
    }

    public void addStat(Integer intVal, String bigIntLine, Double floatVal, Integer stringLength, Long rowCount) {
        if (intVal!=null) {
            statistics.setNumOfWritеenInt(statistics.getNumOfWritеenInt().add(BigInteger.valueOf(1)));
            if (statistics.getIntMinValue()==null) {
                statistics.setIntMinValue(BigInteger.valueOf(intVal));
            } else if (BigInteger.valueOf(intVal).compareTo(statistics.getIntMinValue())<0) {
                statistics.setIntMinValue(BigInteger.valueOf(intVal));
            }

            if (statistics.getIntMaxValue()==null) {
                statistics.setIntMaxValue(BigInteger.valueOf(intVal));
            } else if (BigInteger.valueOf(intVal).compareTo(statistics.getIntMaxValue())>0) {
                statistics.setIntMaxValue(BigInteger.valueOf(intVal));
            }

            statistics.setIntSum(statistics.getIntSum().add(BigInteger.valueOf(intVal)));
            if (rowCount!=0) {
                BigDecimal BigDecSum = new BigDecimal(statistics.getIntSum());
                BigDecimal BigDecRowCount = new BigDecimal(statistics.getNumOfWritеenInt());
                statistics.setIntAvg(BigDecSum.divide(BigDecRowCount, 2, RoundingMode.HALF_UP));
            } else {statistics.setIntAvg(BigDecimal.valueOf(0));}

        } else if (bigIntLine!=null&&bigIntLine.matches("-?\\d+")) {
            BigInteger bigIntVal = new BigInteger(bigIntLine);
            statistics.setNumOfWritеenInt(statistics.getNumOfWritеenInt().add(BigInteger.valueOf(1)));

            if (statistics.getIntMinValue()==null) {
                statistics.setIntMinValue(bigIntVal);
            } else if (bigIntVal.compareTo(statistics.getIntMinValue())<0) {
                statistics.setIntMinValue(bigIntVal);
            }

            if (statistics.getIntMaxValue()==null) {
                statistics.setIntMaxValue(bigIntVal);
            } else if (bigIntVal.compareTo(statistics.getIntMaxValue())>0) {
                statistics.setIntMaxValue(bigIntVal);
            }
            statistics.setIntSum(statistics.getIntSum().add(bigIntVal));
            if (rowCount!=0) {
                BigDecimal BigDecSum = new BigDecimal(statistics.getIntSum());
                BigDecimal BigDecRowCount = new BigDecimal(statistics.getNumOfWritеenInt());
                statistics.setIntAvg(BigDecSum.divide(BigDecRowCount, 2, RoundingMode.HALF_UP));
            } else {statistics.setIntAvg(BigDecimal.valueOf(0));}

        } else if (floatVal!=null) {
            statistics.setNumOfWriteenFloat(statistics.getNumOfWriteenFloat()+1);
            if (statistics.getFloatMinValue()==null) {
                statistics.setFloatMinValue(floatVal);
            } else if (floatVal<statistics.getFloatMinValue()) statistics.setFloatMinValue(floatVal);

            if (statistics.getFloatMaxValue()==null) {
                statistics.setFloatMaxValue(floatVal);
            } else if (floatVal>statistics.getFloatMaxValue()) statistics.setFloatMaxValue(floatVal);

            statistics.setFloatSum(statistics.getFloatSum()+floatVal);

            if (rowCount!=0) {
                statistics.setFloatAvg(statistics.getFloatSum()/statistics.getNumOfWriteenFloat());
            } else {
                statistics.setFloatAvg(0D);
            }
        } else if (stringLength!=0) {
            statistics.setNumOfWriteenString(statistics.getNumOfWriteenString()+1);
            if (statistics.getShortestStringSize()==null) {
                statistics.setShortestStringSize(stringLength);
            } else if (stringLength<statistics.getShortestStringSize()) statistics.setShortestStringSize(stringLength);

            if (statistics.getLongestStringSize()==null) {
                statistics.setLongestStringSize(stringLength);
            } else if (stringLength>statistics.getLongestStringSize()) statistics.setLongestStringSize(stringLength);
        }
    }

    public String showShrotStat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------КРАТКАЯ СТАТИСТИКА---------");
        stringBuilder.append("\n");
        stringBuilder.append("Количество записанных целых чисел: ");
        stringBuilder.append(statistics.getNumOfWritеenInt());
        stringBuilder.append("\n");
        stringBuilder.append("Количество записанных вещественных чисел: ");
        stringBuilder.append(statistics.getNumOfWriteenFloat());
        stringBuilder.append("\n");
        stringBuilder.append("Количество записанных строк: ");
        stringBuilder.append(statistics.getNumOfWriteenString());
        return stringBuilder.toString();
    }

    public String showFullStat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------ПОЛНАЯ СТАТИСТИКА---------");
        stringBuilder.append("\n");
        stringBuilder.append("Количество записанных целых чисел: ");
        stringBuilder.append(statistics.getNumOfWritеenInt());
        stringBuilder.append("\n");
        stringBuilder.append("Количество записанных вещественных чисел: ");
        stringBuilder.append(statistics.getNumOfWriteenFloat());
        stringBuilder.append("\n");
        stringBuilder.append("Количество записанных строк: ");
        stringBuilder.append(statistics.getNumOfWriteenString());
        stringBuilder.append("\n");
        stringBuilder.append("\n");

        stringBuilder.append("Минимальное значение целых чисел: ");
        stringBuilder.append(statistics.getIntMinValue());
        stringBuilder.append("\n");
        stringBuilder.append("Максимальное значение целых чисел: ");
        stringBuilder.append(statistics.getIntMaxValue());
        stringBuilder.append("\n");
        stringBuilder.append("Сумма целых чисел: ");
        stringBuilder.append(statistics.getIntSum());
        stringBuilder.append("\n");
        stringBuilder.append("Среднее целых чисел: ");
        stringBuilder.append(statistics.getIntAvg());
        stringBuilder.append("\n");
        stringBuilder.append("\n");

        stringBuilder.append("Минимальное значение вещественных чисел: ");
        stringBuilder.append(statistics.getFloatMinValue());
        stringBuilder.append("\n");
        stringBuilder.append("Максимальное значение вещественных чисел: ");
        stringBuilder.append(statistics.getFloatMaxValue());
        stringBuilder.append("\n");
        stringBuilder.append("Сумма вещественных чисел: ");
        stringBuilder.append(statistics.getFloatSum());
        stringBuilder.append("\n");
        stringBuilder.append("Среднее вещественных чисел: ");
        stringBuilder.append(statistics.getFloatAvg());
        stringBuilder.append("\n");
        stringBuilder.append("\n");

        stringBuilder.append("Размер самой короткой строки: ");
        stringBuilder.append(statistics.getShortestStringSize());
        stringBuilder.append("\n");
        stringBuilder.append("Размер самой длинной строки: ");
        stringBuilder.append(statistics.getLongestStringSize());

        return stringBuilder.toString();
    }
}
