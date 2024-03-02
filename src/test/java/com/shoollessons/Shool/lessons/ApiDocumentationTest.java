package com.shoollessons.Shool.lessons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoollessons.school.lessons.book.Book;
import com.shoollessons.school.lessons.book.BookController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
public class ApiDocumentationTest {
    @Autowired
    private ObjectMapper mapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void testVytvoryKnihu () throws Exception {
        Book k = new Book();
        k.setAuthorLastname("AAA");
        k.setTitle("NNN");
        mockMvc.perform(
                post("api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(k))
        )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        requestFields(
                                fieldWithPath("autor").description("Autor knihy"),
                                fieldWithPath("nazov").description("Nazov knihy")
                        )))
                .andReturn();

    }
}
