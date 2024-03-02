package com.shoollessons.Shool.lessons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoollessons.school.lessons.SchoolLessonsApplication;
import com.schoollessons.school.lessons.book.Book;
import com.schoollessons.school.lessons.book.BookController;
import com.schoollessons.school.lessons.book.BookServices;
import com.schoollessons.school.lessons.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
@ContextConfiguration(classes=SchoolLessonsApplication.class)

public class SchoolLessonsApplicationDocumentationTests {
    @Autowired
    private ObjectMapper mapper;
    private MockMvc mockMvc;

    @MockBean
    private BookServices bookServices;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void testCreateBook () throws Exception {
        Book k = Book.builder()
                .id(1L)
                .authorFirstname("AuthorFirstName")
                .authorLastname("AuthorLastName")
                .title("Title")
                .isbn("ISBN")
                .count(1)
                .build();
        k.setAuthorLastname("AAA");
        k.setTitle("NNN");

        when(bookServices.createBook(any(Book.class))).thenReturn(k);

        mockMvc.perform(
                        post("/api/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(k))
                )
                .andExpect(status().isOk())
                .andDo(document("create-book",
                        requestFields(
                                fieldWithPath("id").description("The id of the book"),
                                fieldWithPath("authorFirstname").description("The first name of the author"),
                                fieldWithPath("authorLastname").description("The last name of the author"),
                                fieldWithPath("title").description("The title of the book"),
                                fieldWithPath("isbn").description("The ISBN of the book"),
                                fieldWithPath("count").description("The count of the book")
                        )
                ));
    }}
