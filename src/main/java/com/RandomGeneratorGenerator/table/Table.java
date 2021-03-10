package com.RandomGeneratorGenerator.table;

import com.RandomGeneratorGenerator.repository.ContentRepository;
import com.RandomGeneratorGenerator.repository.NameRepository;
import com.RandomGeneratorGenerator.repository.TagRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

@Component
@Getter
public class Table {

    private final JTable table;
    private final NameRepository nameRepository;
    private final TagRepository tagRepository;
    private final ContentRepository contentRepository;

    @Autowired
    public Table(NameRepository nameRepository, TagRepository tagRepository, ContentRepository contentRepository) {
        this.nameRepository = nameRepository;
        this.tagRepository = tagRepository;
        this.contentRepository = contentRepository;

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return String.class;
                }
                return Boolean.class;
            }
        };
        model.addColumn("Content");

        ArrayList<String> tags = tagRepository.getTags();
        ArrayList<String> names = nameRepository.getNames();

        for (String tag : tags) {
            model.addColumn(tag);
        }

    for (String name : names) {
        String[] namesToAdd = {name};
        model.addRow(namesToAdd);
    }

    for (int i = 0; i < names.size(); i++) {
        Object value = model.getValueAt(i, 0);
        try {
            for (String tag : tags) {
                long nameId = nameRepository.getNameIdByName(value.toString());
                long tagId = tagRepository.getTagByName(tag);
                ArrayList<Long> tagIdFromContent = contentRepository.getSingleTagByNameId(nameId);
                for (Long aLong : tagIdFromContent) {
                    if (tagId == aLong) {
                        model.setValueAt(true, i, model.findColumn(tag));
                    }
                }
            }
        } catch (Exception e) {

        }
    }

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setBackground(new Color(224,131,0));
        table.setForeground(Color.BLACK);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setBackground(new Color(224,131,0));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setGridColor(Color.BLACK);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centerRenderer);

        table.setAutoCreateRowSorter(true);

    }
}

//somelierzy, gastronomia - restauratorzy, winiarze, producenci szkła, konsumenci domowi
//materiał - koszt, koszt wytworzenia, mały rynek zbytu, ciężkie warunki pracy, krytyka konsumentów
//krytyka konsumentów - design, kampania marketingowa, ankieta konsumencka, nawiązanie współpracy z winnicami, poprawa warunków pracy