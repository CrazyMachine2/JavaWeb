package services;

import models.service.CourseServiceModel;

import java.util.List;
import java.util.function.Function;

public class HtmlServiceImpl implements HtmlService {

    @Override
    public String getCoursesList(List<CourseServiceModel> courses) {
        return this.getList(courses, this::toCourseItem);
    }

    @Override
    public String getCreateCourseForm() {
        return "<form method='post' action=/courses>" +
                "   <label>" +
                "       Name: " +
                "       <input name='name' type=text />" +
                "   </label>" +
                "   <button>Create</button>" +
                "</form>";
    }

    private String toCourseItem(CourseServiceModel course) {
        return String.format("<li>%s</li>", course.getName());
    }

    private <T> String getList(List<T> collection, Function<T, String> itemFunc) {
        StringBuilder sb = new StringBuilder();
        collection.forEach(item -> {
            String html = itemFunc.apply(item);
            sb.append(html);
        });
        return String.format("<ul>%s</ul>", sb.toString());
    }
}
