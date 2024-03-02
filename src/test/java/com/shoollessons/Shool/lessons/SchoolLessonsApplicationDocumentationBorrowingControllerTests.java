package com.shoollessons.Shool.lessons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoollessons.school.lessons.SchoolLessonsApplication;
import com.schoollessons.school.lessons.borrowings.Borrowing;
import com.schoollessons.school.lessons.borrowings.BorrowingController;
import com.schoollessons.school.lessons.borrowings.BorrowingServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BorrowingController.class)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
@ContextConfiguration(classes=SchoolLessonsApplication.class)
public class SchoolLessonsApplicationDocumentationBorrowingControllerTests {
    @Autowired
    private ObjectMapper mapper;
    private MockMvc mockMvc;

    @MockBean
    private BorrowingServices borrowingServices;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void testGetBorrowings() throws Exception {
        List<
                Borrowing> borrowings = new ArrayList<>();
        when(borrowingServices.findAllBorrowings()).thenReturn(borrowings);

        mockMvc.perform(get("/api/borrowings"))
                .andExpect(status().isOk())
                .andDo(document("get-borrowings"));
    }

    @Test
    public void testGetBorrowingById() throws Exception {
        Borrowing borrowing = new Borrowing();
        borrowing.setId(1L);
        when(borrowingServices.findBorrowingByID(1L)).thenReturn(borrowing);

        mockMvc.perform(get("/api/borrowings/1"))
                .andExpect(status().isOk())
                .andDo(document("get-borrowing-by-id"));
    }

    @Test
    public void testDeleteBorrowingById() throws Exception {
        Borrowing borrowing = new Borrowing();
        borrowing.setId(1L);
        when(borrowingServices.deleteBorrowingById(1L)).thenReturn(borrowing);

        mockMvc.perform(delete("/api/borrowings/1"))
                .andExpect(status().isOk())
                .andDo(document("delete-borrowing-by-id"));
    }

}

