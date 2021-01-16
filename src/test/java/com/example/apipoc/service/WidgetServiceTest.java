package com.example.apipoc.service;

import com.example.apipoc.entity.Widget;
import com.example.apipoc.exception.ResourceNotFoundException;
import com.example.apipoc.repositories.WidgetRepository;
import com.example.apipoc.utils.MockUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

/**
 * @author Parasuram
 */
@RunWith(MockitoJUnitRunner.class)
public class WidgetServiceTest {

    @InjectMocks
    private WidgetServiceImpl widgetService;

    @Mock
    private WidgetRepository widgetRepository;

    @Test
    public void testCreateWidget() {
        when(widgetRepository.saveAll(Mockito.any())).thenReturn(createWidgets());
        when(widgetRepository.findAllOrderByZIndexAsc()).thenReturn(MockUtil.widgets());
        when(widgetService.getAllWidgets()).thenReturn(MockUtil.widgets());
        Assert.assertTrue(!widgetService.createWidget(MockUtil.mockWidget()).isEmpty());
    }

    @Test
    public void testCreateWidgetIfZIndexIsNull() {
        when(widgetRepository.saveAll(Mockito.any())).thenReturn(createWidgets());
        when(widgetRepository.findAllOrderByZIndexAsc()).thenReturn(MockUtil.widgets());
        when(widgetService.getAllWidgets()).thenReturn(MockUtil.widgets());
        Widget widget = MockUtil.mockWidget();
        widget.setzIndex(null);
        Assert.assertTrue(!widgetService.createWidget(widget).isEmpty());
    }

    @Test
    @Ignore
    public void testCreateWidgetWithExistingZIndex() {
        when(widgetRepository.saveAll(Mockito.any())).thenReturn(createWidgets());
        when(widgetRepository.findAllOrderByZIndexAsc()).thenReturn(MockUtil.widgets());
        when(widgetService.getAllWidgets()).thenReturn(MockUtil.widgets());
        Widget widget = MockUtil.mockWidget();
        MockUtil.widgets().add(widget);
        MockUtil.widgets().stream().forEach(widget1 -> {
            List<Widget> created = widgetService.createWidget(widget);
            Assert.assertNotNull(created);
        });

    }

    @Test
    public void testUpdateWidget() {
        when(widgetRepository.saveAll(Mockito.any())).thenReturn(createWidgets());
        Widget widget = MockUtil.mockWidget();
        when(widgetRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(MockUtil.mockWidget()));
        List<Widget> updatedWidgets = widgetService.updateWidget(3, widget);
        Assert.assertNotNull(updatedWidgets);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateWidgetIfWidgetNotExists() {
        when(widgetRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
        Widget widget = MockUtil.mockWidget();
        widgetService.updateWidget(7, widget);
        verify(widgetService, times(1)).updateWidget(7, widget);

    }

    @Test
    public void testDeleteWidget() {
        when(widgetRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(MockUtil.mockWidget()));
        widgetService.deleteWidget(3);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteWidgetIfWidgetNotExists() {
        when(widgetRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
        widgetService.deleteWidget(9);
        verify(widgetService, times(1)).deleteWidget(9);
    }


    private List<Widget> createWidgets() {
        return Stream.of(MockUtil.getWidget(3,2,2,3,5, 1),
                MockUtil.getWidget(3, 2, 3, 3, 5, 2))
                .collect(Collectors.toList());
    }
}
