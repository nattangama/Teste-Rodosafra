package com.javaeeeee.resources;

import com.javaeeeee.Student;
import com.javaeeeee.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/show")
@Produces(MediaType.TEXT_HTML)
public class UserResource {

    static UserDAO dao;
    public UserResource(UserDAO dao2)throws IOException {
        dao = dao2;
    }

    @GET
    public void showResource()throws IOException {
        List<Student> result = dao.findAll();
        for(Student s: result){
            System.out.println(s.getStudentID() + " - " + s.getStudentName());
        }
    }
}
