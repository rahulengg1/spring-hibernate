package com.rahul.association;

import com.rahul.association.manytomany.entities.Programmer;
import com.rahul.association.manytomany.entities.Project;
import com.rahul.association.manytomany.entities.repo.ProgrammerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AssociationManyToManyTests {


    @Autowired
    ProgrammerRepository programmerRepository;


    @Test
    @Transactional
    public void testManyToManyAssociation() {
        Programmer programmer = new Programmer();
        programmer.setName("test");
        programmer.setSalary(100);

        HashSet<Project> projects = new HashSet<>();
        Project project1 = new Project();
        project1.setName("test project 1");

        Project project2 = new Project();
        project2.setName("test project 2");

        projects.add(project1);
        projects.add(project2);

        programmer.setProjects(projects);

        programmerRepository.save(programmer);

        //Get all Programmer from database. As there will be only one record in db,  programmers.get(0) will use to take first record.
        List<Programmer> programmers = programmerRepository.findAll();
        assertEquals(1, programmers.size());
        assertEquals("test", programmers.get(0).getName());
        assertEquals(100, programmers.get(0).getSalary());

        //Get all Project associated to a Programmer
        Set<Project> projectSet = programmers.get(0).getProjects();
        //check number of Project associated with Programmer, it must be 2 as we set 2 project with a programmer
        assertEquals(2, projectSet.size());


        projectSet.forEach(project ->
        {
            assertTrue(project.getName().contains("test project 1") || project.getName().contains("test project 2"));;
        });

    }


}
