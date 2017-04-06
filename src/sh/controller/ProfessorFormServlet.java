package sh.controller;

import sh.model.Group;
import sh.model.Professor;
import sh.service.ProfessorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ProfessorFormServlet extends HttpServlet {
    ProfessorService service = ProfessorService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            Professor professor = service.getProfessorById(parseLong(id));
            if (professor == null) {
                request.setAttribute("professor", new Group());
                request.getRequestDispatcher("resource-not-found.html").forward(request, response);
            } else {
                request.setAttribute("professor", professor);
                request.setAttribute("action", "edit");
                request.getRequestDispatcher("WEB-INF/jsp/professor-form.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("professor", new Professor());
            request.setAttribute("action", "create");
            request.getRequestDispatcher("WEB-INF/jsp/professor-form.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double avgMark = parseDouble(request.getParameter("avgMark"));
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String fatherName = request.getParameter("fatherName");
        Date birthDate = null;
        try {
            String birth = request.getParameter("birthDate");
            java.util.Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
            birthDate = new Date(parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int id = parseInt(request.getParameter("id"));

        Professor professor = new Professor(id, firstName, secondName, fatherName, birthDate, avgMark);
        Professor save = service.save(professor);

        request.setAttribute("message", "All right");
        request.setAttribute("professor", save);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("WEB-INF/jsp/professor-form.jsp").forward(request, response);
    }
}
