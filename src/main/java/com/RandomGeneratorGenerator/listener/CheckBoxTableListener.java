package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Content;
import com.RandomGeneratorGenerator.repository.ContentRepository;
import com.RandomGeneratorGenerator.repository.NameRepository;
import com.RandomGeneratorGenerator.repository.TagRepository;
import com.RandomGeneratorGenerator.service.SaveContent;
import com.RandomGeneratorGenerator.table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

@Component
public class CheckBoxTableListener implements TableModelListener {

    private final Table table;
    private final NameRepository nameRepository;
    private final SaveContent saveContent;
    private final ContentRepository contentRepository;
    private final TagRepository tagRepository;
    private final GUI gui;

    @Autowired
    public CheckBoxTableListener(Table table, NameRepository nameRepository, SaveContent saveContent, ContentRepository contentRepository, TagRepository tagRepository, GUI gui) {
        this.table = table;
        this.nameRepository = nameRepository;
        this.saveContent = saveContent;
        this.contentRepository = contentRepository;
        this.tagRepository = tagRepository;
        this.gui = gui;
    }


    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        table.getTable().getModel().addTableModelListener(this);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        if (column > 0) {
            String columnValue = model.getColumnName(column);
            String rowValue =  model.getValueAt(row, 0).toString();
            Boolean checked = (Boolean) model.getValueAt(row, column);
            long tagId = tagRepository.getTagByName(columnValue);
            long nameId = nameRepository.getNameIdByName(rowValue);

            if (checked) {
                Content content = new Content();
                content.setTag_id(tagId);
                content.setName_id(nameId);
                saveContent.newContent(content);
            } else {
                contentRepository.deleteFromContent(tagId, nameId);
            }
        }
        if (column == 0) {
            int nameSelected = table.getTable().getSelectedRow();
            int modelRow = table.getTable().convertRowIndexToModel(nameSelected);
            String nameToEdit = model.getValueAt(modelRow, 0).toString();
            if (nameRepository.getNames().contains(nameToEdit)) {
                JOptionPane.showMessageDialog(new JFrame(), "Name already exists!");
            } else {
                long nameId = nameRepository.getNameIdByName(gui.getSelectedName());
                nameRepository.updateName(nameToEdit, nameId);
            }
        }
    }
}
