package com.example.apipoc.service;

import com.example.apipoc.entity.Widget;

import java.util.List;
import java.util.Optional;

/**
 * @author Parasuram
 */
public interface WidgetService {

    List<Widget> createWidget(Widget widget);

    List<Widget> updateWidget(Integer id, Widget widget);

    void deleteWidget(Integer id);

    Optional<Widget> getWidget(Integer id);

    List<Widget> getAllWidgets();

}
