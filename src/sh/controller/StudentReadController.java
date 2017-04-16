package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.StudentDao;
import sh.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class StudentReadController extends HttpServlet {

    private final StudentDao dao = DaoFactory.createStudentDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            if (id != null) {
                Student student = dao.findOne(parseLong(id));
                if (student == null) {
                    request.setAttribute("student", new Student());
                    request.getRequestDispatcher("resource-not-found.html").forward(request, response);
                } else {
                    request.setAttribute("student", student);
                    request.setAttribute("action", "edit");
                    request.getRequestDispatcher("/WEB-INF/jsp/student-form.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("student", new Student());
                request.setAttribute("action", "saveOrUpdate");
                request.getRequestDispatcher("/WEB-INF/jsp/student-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
