package com.RandomGeneratorGenerator.listener;

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

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

@Component
public class CheckBoxTableListener implements TableModelListener {

    private final Table table;
    private final NameRepository nameRepository;
    private final TagRepository tagRepository;
    private final SaveContent saveContent;
    private final ContentRepository contentRepository;

    @Autowired
    public CheckBoxTableListener(Table table, NameRepository nameRepository, TagRepository tagRepository, SaveContent saveContent, ContentRepository contentRepository) {
        this.table = table;
        this.nameRepository = nameRepository;
        this.tagRepository = tagRepository;
        this.saveContent = saveContent;
        this.contentRepository = contentRepository;
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
            String rowValue =  model.getValueAt(row, 0).toString();
            Boolean checked = (Boolean) model.getValueAt(row, column);
            long tagId = column;
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
            String nameToEdit = model.getValueAt(row, 0).toString();
            long nameId = row + 1;
            nameRepository.updateName(nameToEdit, nameId);
        }
    }
}
