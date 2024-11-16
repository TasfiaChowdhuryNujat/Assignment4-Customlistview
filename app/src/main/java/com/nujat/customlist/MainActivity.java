package com.nujat.customlist;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter adapter;
    List<String> listGroupTitles;
    HashMap<String, List<Item>> listChildData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.expandableListView);
        listGroupTitles = new ArrayList<>();
        listChildData = new HashMap<>();

        // Preparing data
        prepareData();

        // Setting up the adapter
        adapter = new ExpandableListAdapter(this, listGroupTitles, listChildData);
        expandableListView.setAdapter(adapter);

        // Ensure only one group expands at a time
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedGroup != -1 && groupPosition != lastExpandedGroup) {
                    expandableListView.collapseGroup(lastExpandedGroup);
                }
                lastExpandedGroup = groupPosition;
            }
        });
    }

    private void prepareData() {
        // Group titles
        listGroupTitles.add("C++");
        listGroupTitles.add("HTML");
        listGroupTitles.add("Python");
        listGroupTitles.add("CSS");

        // Child data
        List<Item> category1 = new ArrayList<>();
        category1.add(new Item("C++", "C++ is a high-performance, general-purpose programming language developed by Bjarne Stroustrup in 1985. It supports both object-oriented and procedural programming paradigms. Widely used in system/software development, game programming, and embedded systems, it provides fine control over hardware and system resources. C++ features concepts like classes, inheritance, and polymorphism, along with efficient memory management. Its standard library offers extensive functionalities for data structures, algorithms, and I/O operations.", R.drawable.img));

        List<Item> category2 = new ArrayList<>();
        category2.add(new Item("HTML", "HTML (HyperText Markup Language) is the standard markup language for creating web pages and web applications. It structures the content of a webpage using elements such as headings, paragraphs, links, images, and forms. Developed by Tim Berners-Lee in 1991, it forms the foundation of web development. HTML works in conjunction with CSS and JavaScript to define layout and interactivity. Its simplicity and ubiquity make it essential for web-based communication and design.", R.drawable.img_1));

        List<Item> category3 = new ArrayList<>();
        category3.add(new Item("Python", "Python is a versatile, high-level programming language known for its simplicity and readability. Created by Guido van Rossum in 1991, it emphasizes code readability and ease of use, making it popular for beginners and professionals alike. Python supports multiple programming paradigms, including procedural, object-oriented, and functional programming. It's widely used in data science, machine learning, web development, automation, and more.", R.drawable.img_2));

        List<Item> category4 = new ArrayList<>();
        category4.add(new Item("CSS", "CSS (Cascading Style Sheets) is a stylesheet language used for describing the presentation of a document written in HTML or XML. It enables developers to control the layout, colors, fonts, and overall style of web pages. By separating content from design, CSS ensures a clean and efficient development process. It also allows for responsive and adaptive designs across different devices.", R.drawable.img_3));

        listChildData.put(listGroupTitles.get(0), category1);
        listChildData.put(listGroupTitles.get(1), category2);
        listChildData.put(listGroupTitles.get(2), category3);
        listChildData.put(listGroupTitles.get(3), category4);
    }
}