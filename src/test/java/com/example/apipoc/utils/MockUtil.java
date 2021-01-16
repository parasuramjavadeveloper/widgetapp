package com.example.apipoc.utils;

import com.example.apipoc.entity.Widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Parasuram
 */
public class MockUtil {

    public static List<Widget> widgets() {
        return Stream.of(getWidget(10,5,1,50,30, 1),
                getWidget(12, 8, 2, 15, 20, 2),
                getWidget(5, 5, 3, 10, 10, 3))
                .collect(Collectors.toList());
    }

    public static Widget mockWidget() {
        return getWidget(3, 2, 2, 3, 5,1);
    }

    public static Widget getWidget(Integer xAxiz, Integer yAxis, Integer zIndex,
                             Integer width, Integer height, Integer id) {
        Widget widget = new Widget(xAxiz, yAxis, zIndex, width, height, new Date(), new Date());
        widget.setId(id);
        return widget;
    }

}
