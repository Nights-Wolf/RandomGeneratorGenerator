package com.RandomGeneratorGenerator.table;

import com.RandomGeneratorGenerator.repository.ContentRepository;
import com.RandomGeneratorGenerator.repository.NameRepository;
import com.RandomGeneratorGenerator.repository.TagRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

@Component
@Getter
public class Table {

    public JTable table;
    private final NameRepository nameRepository;
    private final TagRepository tagRepository;
    private final ContentRepository contentRepository;

    public Table(NameRepository nameRepository, TagRepository tagRepository, ContentRepository contentRepository) {
        this.nameRepository = nameRepository;
        this.tagRepository = tagRepository;
        this.contentRepository = contentRepository;

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Content");
        model.addColumn("Category");

        ArrayList<String> tags = tagRepository.getTags();
        ArrayList<String> names = nameRepository.getNames();

        for (String tag : tags) {
            String [] tagsToAdd = {tag, "Tag"};
            model.addRow(tagsToAdd);
        }

    for (String name : names) {
        String[] namesToAdd = {name, nameRepository.getCategoryByName(name)};
        model.addRow(namesToAdd);
    }

        table = new JTable(model);
        table.setBackground(new Color(224,131,0));
        table.setForeground(Color.BLACK);

        JScrollPane scroller = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
