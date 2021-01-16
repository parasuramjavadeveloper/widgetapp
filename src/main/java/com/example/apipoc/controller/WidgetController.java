package com.example.apipoc.controller;

import com.example.apipoc.entity.Widget;
import com.example.apipoc.exception.ResourceNotFoundException;
import com.example.apipoc.service.WidgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author Parasuram
 */
@Slf4j
@RestController
@RequestMapping("/api/widget")
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    @PostMapping
    public ResponseEntity<List<Widget>> createWidget(@RequestBody @NotNull(message = "Input cannot be null")
                                                             Widget widget) {
        log.debug("Widget creation request : {}", widget);
        final List<Widget> widgets = widgetService.createWidget(widget);
        log.debug("Created Widget {}", widget);
        return new ResponseEntity<>(widgets, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<List<Widget>> updateWidget(@PathVariable(value = "id") @NotNull(message = "Id cannot be null")
                                                             Integer id, @RequestBody Widget widget) {
        log.debug("Widget Update request : {}", widget);
        widget.setId(id);
        final List<Widget> widgets = widgetService.updateWidget(id, widget);
        log.debug("Updated Widget {}", widget);
        return new ResponseEntity<>(widgets, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWidget(@PathVariable(value = "id") @NotNull(message = "Id cannot be null")
                                                       Integer id) {
        log.debug("Widget delete with id {}");
        widgetService.deleteWidget(id);
        return new ResponseEntity<>("Widget Successfully Deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Widget> getWidget(@PathVariable(value = "id") @NotNull(message = "Id cannot be null")
                                                    Integer id) {
        log.debug("Widget get with id {}");
        Optional<Widget> widget = widgetService.getWidget(id);
        if (widget.isPresent()) {
            log.info("Widget found : {}", widget);
            return new ResponseEntity<>(widget.get(), HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Widget Not Found");
    }

    @GetMapping
    public ResponseEntity<List<Widget>> getWidgets() {
        List<Widget> widgets = widgetService.getAllWidgets();
        log.debug("Widgets found : {}", widgets);
        if (widgets.isEmpty()) {
            throw new ResourceNotFoundException("No Widgets found");
        }
        return new ResponseEntity<>(widgets, HttpStatus.OK);
    }

}
