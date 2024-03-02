package com.shoollessons.Shool.lessons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoollessons.school.lessons.SchoolLessonsApplication;
import com.schoollessons.school.lessons.user.UserController;
import com.schoollessons.school.lessons.user.UserService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;




@WebMvcTest(UserController.class)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
@ContextConfiguration(classes=SchoolLessonsApplication.class)

public class SchoolLessonsApplicationDocumentationUserControllerTests {
    @Autowired
    private ObjectMapper mapper;
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void testCreateUser() throws Exception {
        User u = User.builder()
                .id(1L)
                .firstName("UserName")
                .lastName("UseSurname")
                .email("UserEmail")
                .build();

        when(userService.createUser(any(User.class))).thenReturn(u);

        mockMvc.perform(
                        get("/api/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(u))
                )
                .andExpect(status().isOk())
                .andDo(document("create-user",
                        requestFields(
                                fieldWithPath("id").description("The id of the user"),
                                fieldWithPath("firstName").description("The first name of the user"),
                                fieldWithPath("lastName").description("The surname of the user"),
                                fieldWithPath("email").description("The email of the user")
                        )
                ));
    }

    @Test
    public void testFindAllUsers() throws Exception {
        List<User> u = new ArrayList<>();

        when(userService.findAllUsers()).thenReturn(u);

        mockMvc.perform(
                        get("/api/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("find-users"));
    }

    @Test
    public void testFindUserById() throws Exception {
        User u = User.builder()
                .id(1L)
                .firstName("UserName")
                .lastName("UserSurname")
                .email("UserEmail")
                .build();

        when(userService.findUserById(1L)).thenReturn(u);

        mockMvc.perform(
                        get("/api/customers/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("find-user"));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        User u = User.builder()
                .id(1L)
                .firstName("UserName")
                .lastName("UserSurname")
                .email("UserEmail")
                .build();

        when(userService.findUserById(1L)).thenReturn(u);

        mockMvc.perform(
                        delete("/api/customers/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("delete-user"));
    }

    @Test
    public void testUpdateUserById() throws Exception {
        User u = User.builder()
                .id(1L)
                .firstName("UserName")
                .lastName("UserSurname")
                .email("UserEmail")
                .build();

        User updatedUser = User.builder()
                .id(1L)
                .firstName("UpdatedName")
                .lastName("UpdatedSurname")
                .email("UpdatedEmail")
                .build();

        when(userService.findUserById(1L)).thenReturn(u);
        when(userService.updateUserById(any(Long.class), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(
                        put("/api/customers/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updatedUser))
                )
                .andExpect(status().isOk())
                .andDo(document("update-user"));
    }


}
