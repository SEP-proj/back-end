//package com.septeam.metatraining.post.query.service;
//
//
//import com.septeam.metatraining.post.command.application.dto.PostDTO;
//import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
//import com.septeam.metatraining.post.query.application.service.FindPostService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import java.util.stream.Stream;
//
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class PostReadTests {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    private FindPostService findPostService;
//    private static Stream<Arguments> postInfo() {
//        return Stream.of(
//                Arguments.of(
//                        new PostDTO(
//                                "test_title",
//                                CategoryEnum.CULTURE,
//                                1L,
//                                "test_introduction",
//                                "test_body",
//                                "test_conclusion",
//                                "test_content",
//                                true
//                        )
//                )
//        );
//    }
//
//    @DisplayName("글 전체 조회")
//    @ParameterizedTest
//    @MethodSource("postInfo")
//    void findAllTest(){
////        when(findPostService.findAll()).thenReturn()
//        Assertions.assertDoesNotThrow(() -> findPostService.findAll());
//    }
//
//}
