package challenge;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepository extends MongoRepository<Recipe, String>, RecipeRepositoryCustom {

    List<Recipe> findByIngredientsOrderByTitleAsc(String ingredient);

    List<Recipe> findByTitleContainingOrDescriptionContainingAllIgnoreCaseOrderByTitleAsc(String s, String search);
}
