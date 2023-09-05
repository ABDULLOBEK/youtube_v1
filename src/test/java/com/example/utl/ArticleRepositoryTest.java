package com.example.utl;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest {

//    @Autowired
//    private ArticleRepository articleRepository;
//
//    @Test
//    public void itShouldCreateArticle() {
//        // given
//        ArticleEntity articleEntity = new ArticleEntity();
////        articleEntity.setTitle("test article");
//        articleEntity.setContent("content");
//        articleEntity.setDescription("description");
//        articleEntity.setStatus(ArticleStatus.PUBLISHED);
//        articleEntity.setVisible(true);
//        articleEntity.setImageId(UUID.randomUUID().toString());
//        // when
//        articleRepository.save(articleEntity);
//        // then
//        System.out.println("aaaa");
//        Optional<CategoryEntity> optional = categoryRepository.findById(categoryEntity.getId());
//        Assertions.assertThat(optional.isPresent()).isTrue();
//    }

}