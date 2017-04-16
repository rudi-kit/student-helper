package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.ProfessorDao;
import sh.model.Group;
import sh.model.Professor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class ProfessorReadController extends HttpServlet {

    final ProfessorDao dao = DaoFactory.createProfessorDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            if (id != null) {
                Professor professor = dao.findOne(parseLong(id));
                if (professor == null) {
                    request.setAttribute("professor", new Group());
                    request.getRequestDispatcher("resource-not-found.html").forward(request, response);
                } else {
                    request.setAttribute("professor", professor);
                    request.setAttribute("action", "edit");
                    request.getRequestDispatcher("/WEB-INF/jsp/professor-form.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("professor", new Professor());
                request.setAttribute("action", "saveOrUpdate");
                request.getRequestDispatcher("/WEB-INF/jsp/professor-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }


    }
}
