package com.example.apipoc.service;

import com.example.apipoc.entity.Widget;
import org.junit.Ignore;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;

import java.util.Date;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class WidgetServiceSampleTest {

    @InjectMocks
    private WidgetServiceImpl widgetService;

    @ParameterizedTest
    @MethodSource("addWidgets")
    @Ignore
    public void testAddWidget(Widget widget, Widget createdWidget){
        assertThat(createdWidget, is(equalTo(widgetService.createWidget(widget))));
    }

    static Stream<Arguments> addWidgets() {
        return Stream.of(
                Arguments.of(
                        new Widget(3, 2, 1, 3, 5, new Date(), new Date()),
                        getWidget(new Widget(3, 2, 1, 3, 5, new Date(), new Date()), 1)),
                Arguments.of(
                        new Widget(5, 5, 2, 10, 5, new Date(), new Date()),
                        getWidget(new Widget(5, 5, 2, 10, 5, new Date(), new Date()), 2))
        );
    }

    static Widget getWidget(Widget widget, Integer id) {
        widget.setId(id);
        return widget;
    }
}
