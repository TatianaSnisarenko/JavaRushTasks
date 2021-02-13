package com.javarush.task.task22.task2206;

import java.util.Date;

/* 
Форматирование даты
%td - A two-digit day of the month. Valid values are 01 to 31
%tm - A two-digit month. Valid values are 01 to 13. The special value of 13 is required to support lunar calendar.
%ty - Output the last two digits of the year and adds leading zero if necessary. 2011 will output 11
%tH - Format time as two-digit hour of the day for the 24-hour clock. The valid values are 00 to 23. 00 is used for midnight.
%tM - A two-digit minute within an hour. Valid values are 00 to 59.
%tS - A two-digit second. Valid values are 00 to 60.
*/
public class Solution {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(String.format(getFormattedString(), date, date, date, date, date, date));
        //должен быть вывод аналогичный следующему
        //31:10:13 15:59:59
    }

    public static String getFormattedString() {
        return "%td:%tm:%ty %tH:%tM:%tS";
    }
}
