package com.example.apipoc.service;

import com.example.apipoc.entity.Widget;
import com.example.apipoc.exception.ResourceNotFoundException;
import com.example.apipoc.repositories.WidgetRepository;
import com.example.apipoc.util.WidgetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Parasuram
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class WidgetServiceImpl implements WidgetService {

    private static TreeSet<Integer> zIndexSet = new TreeSet<>();

    @Autowired
    private WidgetRepository widgetRepository;

    @Override
    public List<Widget> createWidget(Widget widget) {
        widget.setId(WidgetUtil.getNextSequence());
        widget.setCreatedDate(new Date());
        widget.setLastModifiedDate(new Date());
        return widgetRepository.saveAll(addWidget(widget));
    }

    @Override
    public List<Widget> updateWidget(Integer id, Widget widget) {
        Optional<Widget> dbWidget = widgetRepository.findById(id);
        if (dbWidget.isPresent()) {
            return widgetRepository.saveAll(update(id, widget, dbWidget));
        }
        throw new ResourceNotFoundException("Widget Not Found");
    }

    @Override
    public void deleteWidget(Integer id) {
        Optional<Widget> widget = getWidget(id);
        if (widget.isPresent()) {
            widgetRepository.delete(widget.get());
        } else {
            throw new ResourceNotFoundException("Widget Not Found");
        }
    }

    @Override
    public Optional<Widget> getWidget(Integer id) {
        return widgetRepository.findById(id);
    }

    @Override
    public List<Widget> getAllWidgets() {
        return widgetRepository.findAllOrderByZIndexAsc();
    }

    /**
     * update widget by adjusting zIndex
     *
     * @param id
     * @param widget
     * @param dbWidget
     * @return updatedWidgets
     */
    private List<Widget> update(Integer id, Widget widget, Optional<Widget> dbWidget) {
        widget.setId(id);
        widget.setCreatedDate(dbWidget.get().getCreatedDate());
        widget.setLastModifiedDate(new Date());
        if (widget.getzIndex().equals(dbWidget.get().getzIndex())) {
            return Stream.of(widgetRepository.save(widget)).collect(Collectors.toList());
        }
        return widgetRepository.saveAll(addWidget(widget));
    }

    /**
     * create widget by adjusting zIndex
     *
     * @param widget
     * @return createdWidgets
     */
    private List<Widget> addWidget(Widget widget) {
        List<Widget> newWidgets = new ArrayList<>();
        List<Widget> widgets = getAllWidgets();
        if (widget.getzIndex() == null) {
            addWidgetWithNozIndex(widget, newWidgets, widgets);
        } else if (zIndexSet.contains(widget.getzIndex())) {
            addWidgetWithDuplicatezIndex(widget, newWidgets, widgets);
        } else {
            newWidgets.add(widget);
        }
        zIndexSet.add(widget.getzIndex());
        return newWidgets;
    }

    /**
     * add widget with zIndex null
     *
     * @param widget
     * @param newWidgets
     * @param widgets
     */
    private void addWidgetWithNozIndex(Widget widget, List<Widget> newWidgets,
                                       List<Widget> widgets) {
        Integer maxZIndex = 0;
        if (!widgets.isEmpty()) {
            maxZIndex = widgets.stream().map(w -> w.getzIndex()).collect(Collectors.toList())
                    .stream().max(Comparator.comparing(Integer::intValue)).get();
        }
        widget.setzIndex(maxZIndex + 1);
        newWidgets.add(widget);
    }

    /**
     * add widget with existing zIndex
     *
     * @param widget
     * @param newWidgets
     * @param widgets
     */
    private void addWidgetWithDuplicatezIndex(Widget widget, List<Widget> newWidgets,
                                              List<Widget> widgets) {
        newWidgets.add(widget);
        int zIndex = widget.getzIndex();
        while (!zIndexSet.add(zIndex)) {
            int finalZIndex = zIndex;
            Widget newWidget = widgets.stream().filter(x -> x.getzIndex() == finalZIndex)
                    .findFirst().orElse(new Widget());
            zIndex = newWidget.getzIndex() + 1;
            newWidget.setzIndex(zIndex);
            newWidget.setLastModifiedDate(new Date());
            newWidgets.add(newWidget);
            widgets.remove(newWidget);
        }
    }
}
