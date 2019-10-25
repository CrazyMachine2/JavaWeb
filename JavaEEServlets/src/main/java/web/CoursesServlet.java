package web;

import models.service.CourseServiceModel;
import services.CoursesService;
import services.HtmlService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CoursesServlet extends ViewHttpServlet {
    private final CoursesService coursesService;
   private final HtmlService htmlService;

    @Inject
    public CoursesServlet(CoursesService coursesService, HtmlService htmlService, EntityManager entityManager) {
        this.coursesService = coursesService;
        this.htmlService = htmlService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CourseServiceModel> courses = this.coursesService.getAllCourses();

        String body =
                htmlService.getCreateCourseForm() +
                this.htmlService.getCoursesList(courses);
        this.setHtmlContentType(resp);
        resp.getWriter().println(body);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseName = req.getParameter("name");
        coursesService.createCourse(courseName);
        resp.sendRedirect("/courses");
    }

    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String html = "<ul><li>Java DB</li><li>Java Web</li></ul>";
//
//        resp.getWriter().println(html);
//        resp.setContentType("text/html");
//    }


}



























