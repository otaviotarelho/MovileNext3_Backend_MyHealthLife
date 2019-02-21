package edu.otaviotarelho.users;

import edu.otaviotarelho.users.ObjectMock.UserMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsersApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk());
    }

    @Test
    void insertUser() throws Exception {
        mockMvc.perform(post("/user").requestAttr("user", new UserMock().buildUser())).andExpect(status().isCreated());
    }

    @Test
    void updateUser() throws Exception{
        mockMvc.perform(put("/user")).andExpect(status().isCreated());
    }

    @Test
    void deleteUser() throws Exception{
        mockMvc.perform(delete("/user")).andExpect(status().isNoContent());
    }
}

